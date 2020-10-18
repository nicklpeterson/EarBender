package com.dslproject.ast;

import com.dslproject.ast.executions.Execution;
import com.dslproject.libs.DslVisitor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class Program extends Node {
    private List<Execution> statements = new ArrayList<>();

    public List<Execution> getStatements() { // default access: all classes in the ast package can access
        return this.statements; // note - we return an alias here! cf. Language Principles lecture, be wary of this: we are trusting the caller not to make unintended changes to this list. An alternative would be to clone the list.
    }
    public Program(List<Execution> statements) {
        this.statements = statements;
    }

    @Override
    public <T, C> T accept(C context, DslVisitor<T, C> v) {
        return v.visit(context, this);
    }
}