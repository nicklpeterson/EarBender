package com.dslproject.libs;

import com.dslproject.ast.Program;
import com.dslproject.ast.declarations.DslList;
import com.dslproject.ast.declarations.Function;
import com.dslproject.ast.declarations.Variable;
import com.dslproject.ast.executions.Loop;
import com.dslproject.ast.executions.PlaySimul;
import com.dslproject.ast.executions.PlaySync;
import com.dslproject.ast.executions.Rhythm;

public interface DslVisitor<T, C> {

    T visit(C context, DslList dslList);
    T visit(C context, Function function);
    T visit(C context, Variable variable);
    T visit(C context, Loop loop);
    T visit(C context, PlaySimul playSimul);
    T visit(C context, PlaySync playSync);
    T visit(C context, Program program);
    T visit(C context, Rhythm rhythm);
}
