package com.dslproject.ast.declarations;

import com.dslproject.ast.DslVisitor;
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
    public <T> T accept(DslVisitor<T> v) {
        return null;
    }

}
