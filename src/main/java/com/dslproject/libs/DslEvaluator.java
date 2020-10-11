package com.dslproject.libs;

import ch.qos.logback.core.boolex.EvaluationException;
import com.dslproject.ast.Program;
import com.dslproject.ast.Statement;
import com.dslproject.ast.declarations.Declaration;
import com.dslproject.ast.declarations.DslList;
import com.dslproject.ast.declarations.Function;
import com.dslproject.ast.declarations.Variable;
import com.dslproject.ast.executions.*;
import com.dslproject.music.Music;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@RequiredArgsConstructor
public class DslEvaluator{

    final private Logger log = LoggerFactory.getLogger(DslEvaluator.class);
    private final Program ast;
    private List<Statement> statements;


    public static DslEvaluator getEvaluator(Program ast) {
        return new DslEvaluator(ast);
    }

    /**
     * Evaluate the ast program
     *
     * @throws EvaluationException
     */
    public void evaluateProgram() throws EvaluationException {

        // go through the statement and evaluate each statement
        statements = ast.getStatements();
        for (Statement s: statements) {
            evaluateStatement(s);
        }
    }

    /**
     * Evaluate the statement
     *
     * @throws EvaluationException
     */
    public void evaluateStatement(Statement s) throws EvaluationException {
        if(s.getClass().equals(PlaySync.class)){
            List<Declaration> declarations = ((PlaySync) s).getDeclarations();
            evaluateSync(declarations);
        } else if(s.getClass().equals(PlaySimul.class)){
            List<Declaration> declarations = ((PlaySimul) s).getDeclarations();
            evaluateSimul(declarations);
        } else if(s.getClass().equals(Rhythm.class)){
            /** TODO: implement rhythm */
        }
    }

    /**
     * Evaluate to play the music synchronously
     *
     * @param declarations
     * @throws EvaluationException
     */
    private void evaluateSync(List<Declaration> declarations) throws EvaluationException {
        Music music = new Music();

        // add all the music variable that needs to be played synchronously
        int channel = 0;
        for (Declaration d: declarations) {
            addDeclarationsToMusic(music, d, channel);
        }
        music.playMusic();
    }

    /**
     * Evaluate to play the music simultaneously
     *
     * @param declarations
     * @throws EvaluationException
     */
    private void evaluateSimul(List<Declaration> declarations) throws EvaluationException {
        Music music = new Music();

        // add all the music variable that needs to be played simultaneously
        int channel = 0;
        for (Declaration d: declarations) {
            if(channel > 15){
                throw new EvaluationException("Failed to evaluate: there are too many music " +
                        "set to be played simultaneously.");
            }
            addDeclarationsToMusic(music, d, channel);
            channel++;
        }
        music.playMusic();
    }

    /**
     * Add the music variables to be played later
     *
     * @param music
     * @param d
     * @param channel
     * @throws EvaluationException
     */
    private void addDeclarationsToMusic(Music music, Declaration d, int channel) throws EvaluationException {
        if (d.getClass().equals(Variable.class)) {
            // play a single music variable
            music.addMusicVar((Variable) d, channel);
        }else if (d.getClass().equals(DslList.class)) {
            // play a list of music variables
            List<Declaration> subDeclarations = ((DslList) d).getDeclarations();
            music.addMusicVarList((List<Variable>)(List<?>) subDeclarations, channel);
        }else if (d.getClass().equals(Function.class)) {
            List<Execution> executions = ((Function) d).getExecutions();
            evaluateFunction(music, executions);
        }
    }

    /**
     * Evaluate the function statement
     *
     * @param music
     * @param executions
     * @throws EvaluationException
     */
    private void evaluateFunction(Music music, List<Execution> executions) throws EvaluationException {
        // we allow loops in the function
        for (Execution e: executions) {
            if(e.getClass().equals(Loop.class)){
                int loopTimes = ((Loop) e).getTimes();
                List<Execution> newExecutions = ((Loop) e).getExecutions();
                for (int i=0; i < loopTimes; i++) {
                    // use recursion to evaluate the loop statement
                    evaluateFunction(music, newExecutions);
                }
            } else {
                evaluateStatement(e);
            }
        }
    }

}
