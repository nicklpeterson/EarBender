package com.dslproject.ast.executions;

import com.dslproject.ast.declarations.Declaration;
import com.dslproject.exceptions.ValidatorException;
import com.dslproject.libs.DslVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Loop extends Execution {
    private List<Execution> executions;

    private int times;

    @Override
    public Integer getBeats() {
        int beats = 0;
        for (Execution execution : executions) {
            beats += execution.getBeats();
        }
        return times * beats;
    }

    @Override
    public boolean validateStructure() {

        for (Execution execution : executions) {
            execution.validateStructure();
        }
        if (executions.size() == 0) {
            throw new ValidatorException("the loop cannot be empty");
        }
        return true;
    }

    @Override
    public <T, C> T accept(C context, DslVisitor<T, C> v) {
        return v.visit(context, this);
    }
}
