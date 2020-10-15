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

    void visit(T Context, DslList dslList);
    void visit(T Context, Function function);
    void visit(T Context, Variable variable);
    void visit(T Context, Loop loop);
    void visit(T Context, PlaySimul playSimul);
    void visit(T Context, PlaySync playSync);
    void visit(T Context, Program program);
    void visit(T Context, Rhythm rhythm);
}
