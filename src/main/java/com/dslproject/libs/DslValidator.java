package com.dslproject.libs;

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
import java.util.List;

/*
This class is just validating the PLAY SIMUL command.
the parser and tokenizer should catch any other errors, but
we may want to add more validation to be safe.
 */
@RequiredArgsConstructor
public class DslValidator implements DslVisitor<Boolean, ValidatorContext> {
    private final Program ast;

    public  static DslValidator getValidator(Program ast) {
        return new DslValidator(ast);
    }

    public boolean validateProgram(){
        return ast.accept(new ValidatorContext(false), this);
    }

    public boolean validateSimul(PlaySimul playSimul) {
        HashSet<Integer> tempoList = new HashSet<>(playSimul.getTempoList());
        HashSet<Integer> beatList = new HashSet<>();
        for (Declaration declaration : playSimul.getDeclarations()) {
            declaration.validateVariable();
            beatList.add(declaration.getBeats());
        }
        if (tempoList.size() > 1) {
            throw new ValidatorException("Declarations do not all have the same tempo.");
        }
        if (beatList.size() > 1) {
            throw new ValidatorException("The declarations are not all the same length.");
        }
        return true;
    }
    public boolean validateSync(PlaySync playSync) {
        for (Declaration declaration : playSync.getDeclarations()) {
            declaration.validateVariable();
        }
        return true;
    }


    @Override
    public Boolean visit(ValidatorContext context, DslList dslList) {
        return true;
    }

    @Override
    public Boolean visit(ValidatorContext context, Function function) {
        return true;
    }

    @Override
    public Boolean visit(ValidatorContext context, Variable variable) {
        return true;
    }

    @Override
    public Boolean visit(ValidatorContext context, Loop loop) {
        return true;
    }

    @Override
    public Boolean visit(ValidatorContext context, PlaySimul playSimul) {
        validateSimul(playSimul);
        for (Declaration declaration : playSimul.getDeclarations()) {
            declaration.accept(new ValidatorContext(true), this);
        }
        return true;
    }

    @Override
    public Boolean visit(ValidatorContext context, PlaySync playSync) {
        return true;
    }

    @Override
    public Boolean visit(ValidatorContext context, Program program) {
        return program.getStatements().stream().allMatch(x -> x.accept(context, this));
    }

    @Override
    public Boolean visit(ValidatorContext context, Rhythm rhythm) {
        return true;
    }
}
