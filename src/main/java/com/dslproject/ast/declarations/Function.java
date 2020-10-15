package com.dslproject.ast.declarations;

import com.dslproject.libs.DslVisitor;
import com.dslproject.ast.executions.Execution;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Function extends Declaration {
    private List<Execution> executions;

    public Function(String name, List<Execution> executions) {
        super(name);
        this.executions = executions;
    }

    @Override
    public int getBeats() {
        return executions.stream().mapToInt(x -> getBeats()).sum();
    }

    @Override
    public <T> void accept(T context, DslVisitor<T> v) {
        v.visit(context, this);
    }
}
