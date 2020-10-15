package com.dslproject.libs;

import com.dslproject.ast.Program;
import com.dslproject.ast.declarations.DslList;
import com.dslproject.ast.declarations.Function;
import com.dslproject.ast.declarations.Variable;
import com.dslproject.ast.executions.Loop;
import com.dslproject.ast.executions.PlaySimul;
import com.dslproject.ast.executions.PlaySync;
import com.dslproject.ast.executions.Rhythm;

public interface DslVisitor<T> {

    void visit(T context, DslList dslList);
    void visit(T context, Function function);
    void visit(T context, Variable variable);
    void visit(T context, Loop loop);
    void visit(T context, PlaySimul playSimul);
    void visit(T context, PlaySync playSync);
    void visit(T context, Program program);
    void visit(T context, Rhythm rhythm);
}
