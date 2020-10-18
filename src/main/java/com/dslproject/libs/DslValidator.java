package com.dslproject.libs;

import com.dslproject.ast.Program;
import com.dslproject.ast.declarations.Declaration;
import com.dslproject.ast.declarations.DslList;
import com.dslproject.ast.declarations.Function;
import com.dslproject.ast.declarations.Variable;
import com.dslproject.ast.executions.*;
import com.dslproject.exceptions.ValidatorException;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;

/*
This class is just validating the PLAY SIMUL command.
the parser and tokenizer should catch any other errors, but
we may want to add more validation to be safe.
 */
@RequiredArgsConstructor
public class DslValidator implements DslVisitor<Boolean, Void> {
    private final Program ast;

    public  static DslValidator getValidator(Program ast) {
        return new DslValidator(ast);
    }

    public boolean validateProgram(){
        return ast.accept(null, this);
    }

    public boolean validateSimul(PlaySimul playSimul) {
        HashSet<Integer> beatList = new HashSet<>();
        for (Declaration declaration : playSimul.getDeclarations()) {
            declaration.validateStructure();
            beatList.add(declaration.getBeats());
        }
        if (beatList.size() > 1) {
            throw new ValidatorException("The declarations in PLAY SIMUL are not all the same length.");
        }
        return true;
    }
    public boolean validateSync(PlaySync playSync) {
        for (Declaration declaration : playSync.getDeclarations()) {
            declaration.validateStructure();
        }
        return true;
    }


    @Override
    public Boolean visit(Void context, DslList dslList) {
        for (Declaration declaration : dslList.getDeclarations()) {
            declaration.accept(null, this);
        }
        return true;
    }

    @Override
    public Boolean visit(Void context, Function function) {
        for (Execution execution : function.getExecutions()) {
            execution.accept(null, this);
        }
        return true;
    }

    @Override
    public Boolean visit(Void context, Variable variable) {
        if (variable.getTempo() <= 0) {
            throw new ValidatorException( variable.getName() + " has a tempo less than or equal to zero. Tempo must be greater than zero.");
        }
        return true;
    }

    @Override
    public Boolean visit(Void context, Loop loop) {
        for (Execution execution : loop.getExecutions()) {
            execution.accept(null, this);
        }
        return true;
    }

    @Override
    public Boolean visit(Void context, PlaySimul playSimul) {
        validateSimul(playSimul);
        for (Declaration declaration : playSimul.getDeclarations()) {
            declaration.accept(null, this);
        }
        return true;
    }

    @Override
    public Boolean visit(Void context, PlaySync playSync) {
        for (Declaration declaration : playSync.getDeclarations()) {
            validateSync(playSync);
            declaration.accept(null, this);
        }

        return true;
    }

    @Override
    public Boolean visit(Void context, Program program) {
        return program.getStatements().stream().allMatch(x -> x.accept(null, this));
    }

    @Override
    public Boolean visit(Void context, Rhythm rhythm) {
        return true;
    }
}
