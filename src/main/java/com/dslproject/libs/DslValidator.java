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
public class DslValidator implements DslVisitor<Boolean, ValidatorContext> {
    private final Program ast;

    public  static DslValidator getValidator(Program ast) {
        return new DslValidator(ast);
    }

    public boolean validateProgram(){
        return ast.accept(new ValidatorContext(false, false), this);
    }

    public void validateSimul(PlaySimul playSimul) {
        HashSet<Integer> beatList = new HashSet<>();
        for (Declaration declaration : playSimul.getDeclarations()) {
            // declaration.validateStructure();
            beatList.add(declaration.getBeats());
        }
        if (beatList.size() > 1) {
            throw new ValidatorException("The declarations in PLAY SIMUL are not all the same length.");
        }
    }

    public boolean validateSync(PlaySync playSync) {
        for (Declaration declaration : playSync.getDeclarations()) {
            declaration.validateStructure();
        }
        return true;
    }

    @Override
    public Boolean visit(ValidatorContext context, DslList dslList) {
        for (Declaration declaration : dslList.getDeclarations()) {
            declaration.accept(null, this);
        }
        return true;
    }

    @Override
    public Boolean visit(ValidatorContext context, Function function) {
        for (Execution execution : function.getExecutions()) {
            execution.accept(new ValidatorContext(context.hasSimulParent(), true), this);
        }
        return true;
    }

    @Override
    public Boolean visit(ValidatorContext context, Variable variable) {
        if (variable.getTempo() <= 0) {
            throw new ValidatorException( variable.getName() + " has a tempo less than or equal to zero. Tempo must be greater than zero.");
        }
        return true;
    }

    @Override
    public Boolean visit(ValidatorContext context, Loop loop) {
        for (Execution execution : loop.getExecutions()) {
            execution.accept(context, this);
        }
        return true;
    }

    @Override
    public Boolean visit(ValidatorContext context, PlaySimul playSimul) {
        validateSimul(playSimul);
        if (context.hasFunctionParent() && context.hasSimulParent()) {
            throw new ValidatorException("Nesting PLAY SIMUL commands in simultaneous functions is not allowed because it can cause undefined behavior.");
        }
        for (Declaration declaration : playSimul.getDeclarations()) {
            declaration.accept(new ValidatorContext(true, context.hasFunctionParent()), this);
        }
        return true;
    }

    @Override
    public Boolean visit(ValidatorContext context, PlaySync playSync) {
        for (Declaration declaration : playSync.getDeclarations()) {
            declaration.accept(context, this);
        }
        return true;
    }

    @Override
    public Boolean visit(ValidatorContext context, Program program) {
        return program.getStatements().stream().allMatch(x -> x.accept(context, this));
    }

    @Override
    public Boolean visit(ValidatorContext context, Rhythm rhythm) {
        if (context.hasFunctionParent()) {
            throw new ValidatorException("Rhythm cannot be declared inside a function");
        }
        return true;
    }
}
