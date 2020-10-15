package com.dslproject.libs;

import ch.qos.logback.core.boolex.EvaluationException;
import com.dslproject.ast.Program;
import com.dslproject.ast.Statement;
import com.dslproject.ast.declarations.Declaration;
import com.dslproject.ast.declarations.DslList;
import com.dslproject.ast.declarations.Function;
import com.dslproject.ast.declarations.Variable;
import com.dslproject.ast.executions.*;
import com.dslproject.exceptions.ValidatorException;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;

@RequiredArgsConstructor

public class DslValidator implements DslVisitor<Void> {
    private final Program ast;

    public  static DslValidator getValidator(Program ast) {
        return new DslValidator(ast);
    }

    public boolean validateProgram(){
        ast.accept(null, this);
        return true;
    }

    public boolean validateSimul(PlaySimul playSimul) {
        HashSet<Integer> tempoList = new HashSet<>(playSimul.getTempoList());
        HashSet<Integer> beatList = new HashSet<>();
        for (Declaration declaration : playSimul.getDeclarations()) {
            beatList.add(declaration.getBeats());
        }
        if (tempoList.size() > 1) {
            throw new ValidatorException("Declarations do not all have the same tempo");
        }
        if (beatList.size() > 1) {
            throw new ValidatorException("The declarations are not all the same length");
        }
        return true;
    }

    @Override
    public void visit(Void context, DslList dslList) {
        // Do nothing
    }

    @Override
    public void visit(Void context, Function function) {
        // Do nothing
    }

    @Override
    public void visit(Void context, Variable variable) {
        // Do nothing
    }

    @Override
    public void visit(Void context, Loop loop) {
        // Do nothing
    }

    @Override
    public void visit(Void context, PlaySimul playSimul) {
        validateSimul(playSimul);
    }

    @Override
    public void visit(Void context, PlaySync playSync) {
        // Do nothing
    }

    @Override
    public void visit(Void context, Program program) {
        for (Statement statement : program.getStatements()) {
            statement.accept(context, this);
        }
    }

    @Override
    public void visit(Void context, Rhythm rhythm) {
        // Do nothing
    }
}
