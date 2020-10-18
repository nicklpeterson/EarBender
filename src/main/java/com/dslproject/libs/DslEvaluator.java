package com.dslproject.libs;

import ch.qos.logback.core.boolex.EvaluationException;
import com.dslproject.ast.Program;
import com.dslproject.ast.Statement;
import com.dslproject.ast.declarations.Declaration;
import com.dslproject.ast.declarations.DslList;
import com.dslproject.ast.declarations.Function;
import com.dslproject.ast.declarations.Variable;
import com.dslproject.ast.executions.*;
import com.dslproject.exceptions.EvaluatorException;
import com.dslproject.music.Music;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RequiredArgsConstructor
public class DslEvaluator implements DslVisitor<Void, EvaluatorContext>{

    final private Logger log = LoggerFactory.getLogger(DslEvaluator.class);
    final private Music music = new Music();

    final private int TOTAL_CHANNELS = 7;
    final private int DEFAULT_CHANNEL = 0;
    final private int BEATS_PER_RHYTHM_LAYER = 128 * 2;
    final private Program ast;
    private int totalBeats = 0;

    public static DslEvaluator getEvaluator(Program ast) {
        return new DslEvaluator(ast);
    }

    /**
     * Evaluate the ast program
     *
     * @throws EvaluationException
     */
    public void evaluateProgram() throws EvaluatorException {
        // go through the statement and evaluate each statement
        for (Statement statement : ast.getStatements()) {
            this.totalBeats += statement.getBeats();
        }
        if (this.totalBeats <= 0) {
            throw new EvaluatorException("The song must contain at least one note.");
        }
        ast.accept(new EvaluatorContext(DEFAULT_CHANNEL, false, false), this);
        this.music.playMusic();
    }

    @Override
    public Void visit(EvaluatorContext context, DslList dslList) {
        for (Declaration declaration : dslList.getDeclarations()) {
            declaration.accept(context, this);
        }
        return null;
    }

    @Override
    public Void visit(EvaluatorContext context, Function function) {
        if (!context.isRest()) {
            for (Execution execution : function.getExecutions()) {
                execution.accept(context, this);
            }
        }
        return null;
    }

    @Override
    public Void visit(EvaluatorContext context, Variable variable) {
        this.music.addMusicVar(variable, context.getChannel(), context.isRest());
        return null;
    }

    @Override
    public Void visit(EvaluatorContext context, Loop loop) {
        for (int i = 0; i < loop.getTimes() && !context.isRest(); i++) {
            for (Execution execution : loop.getExecutions()) {
                execution.accept(new EvaluatorContext(DEFAULT_CHANNEL, false, context.isSimulChild()), this);
            }
        }
        return null;
    }

    @Override
    public Void visit(EvaluatorContext context, PlaySimul playSimul) {
        List<Declaration> declarationList = playSimul.getDeclarations();
        for (int i = DEFAULT_CHANNEL; i < TOTAL_CHANNELS; i++) {
            if (i < declarationList.size()) {
                declarationList.get(i).accept(new EvaluatorContext(i, false, true), this);
            } else {
                declarationList.get(0).accept(new EvaluatorContext(i, true, true), this);
            }
        }
        return null;
    }

    @Override
    public Void visit(EvaluatorContext context, PlaySync playSync) {
        for (Declaration declaration : playSync.getDeclarations()) {
            declaration.accept(new EvaluatorContext(context.getChannel(), false, context.isSimulChild()), this);
            for (int i = DEFAULT_CHANNEL + 1; i < TOTAL_CHANNELS && !context.isSimulChild(); i++) {
                declaration.accept(new EvaluatorContext(i, true, context.isSimulChild()), this);
            }
        }
        return null;
    }

    @Override
    public Void visit(EvaluatorContext context, Program program) {
        for (Statement statement : program.getStatements()) {
            statement.accept(context, this);
        }
        return null;
    }

    @Override
    public Void visit(EvaluatorContext context, Rhythm rhythm) {
        for (String layer : rhythm.getLayers()) {
            music.addRhythmLayer(layer);
        }
        double length = (double) this.totalBeats / (double) this.BEATS_PER_RHYTHM_LAYER;
        music.setRhythmLength(length);
        return null;
    }
}
