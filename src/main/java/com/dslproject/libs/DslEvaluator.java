package com.dslproject.libs;

import com.dslproject.ast.DslVisitor;
import com.dslproject.ast.Program;
import com.dslproject.ast.Statement;
import com.dslproject.ast.declarations.Declaration;
import com.dslproject.ast.declarations.DslList;
import com.dslproject.ast.declarations.Function;
import com.dslproject.ast.declarations.Variable;
import com.dslproject.ast.executions.PlaySimul;
import com.dslproject.ast.executions.PlaySync;
import com.dslproject.ast.executions.Rhythm;
import com.dslproject.music.Music;
import jdk.jshell.EvalException;
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

    public void evaluateProgram() throws EvalException {
        statements = ast.getStatements();

        for (Statement s: statements) {
            if(s.getClass().equals(PlaySync.class)){
                evaluateSync(((PlaySync) s).getDeclarations());
            } else if(s.getClass().equals(PlaySimul.class)){
                evaluateSimul(((PlaySimul) s).getDeclarations());
            } else if(s.getClass().equals(Rhythm.class)){
                /** TODO: implement rhythm */
            }
        }

    }

    private void evaluateSync(List<Declaration> declarations){
        Music music = new Music();

        // add all the music variable that needs to be played synchronously
        int channel = 0;
        for (Declaration d: declarations) {
            addDeclarationsToMusic(music, d, channel);
        }
        music.playMusic();
    }

    private void evaluateSimul(List<Declaration> declarations){
        Music music = new Music();

        // add all the music variable that needs to be played simultaneously
        int channel = 0;
        for (Declaration d: declarations) {
            addDeclarationsToMusic(music, d, channel);
            channel++;
        }
        music.playMusic();
    }

    private void addDeclarationsToMusic(Music music, Declaration d, int channel){
        if (d.getClass().equals(Variable.class)) {
            // play a single music variable
            music.addMusicVar((Variable) d, channel);
        }else if (d.getClass().equals(DslList.class)) {
            // play a list of music variables
            List<Declaration> subDeclarations = ((DslList) d).getDeclarations();
            music.addMusicVarList((List<Variable>)(List<?>) subDeclarations, channel);
        }else if (d.getClass().equals(Function.class)) {
            /** TODO: implement play function */
        }
    }
}
