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
    final private int TOTAL_CHANNELS = 3;
    final private int DEFAULT_CHANNELS = 0;
    private final Program ast;

    public static DslEvaluator getEvaluator(Program ast) {
        return new DslEvaluator(ast);
    }

    /**
     * Evaluate the ast program
     *
     * @throws EvaluationException
     */
    public void evaluateProgram() throws EvaluationException {

        Music music = new Music();

        // go through the statement and evaluate each statement
        List<Statement> statements = ast.getStatements();
        for (Statement s: statements) {
            evaluateStatement(music, s);
        }

        music.playMusic();
    }

    /**
     * Evaluate the statement
     *
     * @throws EvaluationException
     */
    public void evaluateStatement(Music music, Statement s) throws EvaluationException {
        if(s.getClass().equals(PlaySync.class)){
            List<Declaration> declarations = ((PlaySync) s).getDeclarations();
            evaluateSync(music, declarations);
        } else if(s.getClass().equals(PlaySimul.class)){
            List<Declaration> declarations = ((PlaySimul) s).getDeclarations();
            evaluateSimul(music, declarations);
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
    private void evaluateSync(Music music, List<Declaration> declarations) throws EvaluationException {

        // add all the music variable that needs to be played synchronously
        for (Declaration d: declarations) {
            addDeclarationsToMusic(music, d, DEFAULT_CHANNELS, false);
            for(int i = 1; i < TOTAL_CHANNELS; i ++){
                addDeclarationsToMusic(music, d, i, true);
            }
        }
    }

    /**
     * Evaluate to play the music simultaneously
     *
     * @param declarations
     * @throws EvaluationException
     */
    private void evaluateSimul(Music music, List<Declaration> declarations) throws EvaluationException {

        int size = declarations.size();
        // add all the music variable that needs to be played simultaneously
        if(size > TOTAL_CHANNELS){
            throw new EvaluationException("Failed to evaluate: there are too many layers " +
                    "set to be played simultaneously. \n" +
                    "Current support number of layers is " + TOTAL_CHANNELS);
        }

        for(int i = 0; i < TOTAL_CHANNELS; i ++){
            log.debug("Channel:  " + i);
            Declaration d;
            if(i < size){
                d = declarations.get(i);
                addDeclarationsToMusic(music, d, i, false);
            } else {
                d = declarations.get(0);
                addDeclarationsToMusic(music, d, i, true);
            }
        }
    }

    /**
     * Add the music variables to be played synchronously later
     *
     * @param music
     * @param d
     * @param channel
     * @param  rest      true if we want to play all note as rest notes
     * @throws EvaluationException
     */
    private void addDeclarationsToMusic(Music music, Declaration d, int channel, boolean rest) throws EvaluationException {
        if (d.getClass().equals(Variable.class)) {
            // play a single music variable
            music.addMusicVar((Variable) d, channel, rest);
        }else if (d.getClass().equals(DslList.class)) {
            // play a list of music variables
            List<Declaration> subDeclarations = ((DslList) d).getDeclarations();
            music.addMusicVarList((List<Variable>)(List<?>) subDeclarations, channel, rest);
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
                evaluateStatement(music, e);
            }
        }
    }

}
