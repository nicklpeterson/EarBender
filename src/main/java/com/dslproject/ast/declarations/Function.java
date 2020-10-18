package com.dslproject.ast.declarations;

import com.dslproject.ast.executions.*;
import com.dslproject.exceptions.ValidatorException;
import com.dslproject.libs.DslVisitor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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
    public Integer getBeats() {
        int beats = 0;
        for (Execution execution : executions) {
            beats += execution.getBeats();
        }
        return beats;
    }

    @Override
    public boolean validateStructure() {
        if (executions.size() == 0) {
            throw new ValidatorException("the function cannot be empty");
        }
        for (Execution execution : executions) {
            if(execution.getClass().equals(Rhythm.class)) {
                throw new ValidatorException("rhythm cannot be within function");
            };
            execution.validateStructure();
        }
        return true;
    }

    @Override
    public <T, C> T accept(C context, DslVisitor<T, C> v) {
        return v.visit(context, this);
    }
}
