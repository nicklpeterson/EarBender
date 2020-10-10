package com.dslproject.ast.executions;

import com.dslproject.ast.DslVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Loop extends Execution {
    private List<Execution> executions;

    private int times;

    @Override
    public <T> T accept(DslVisitor<T> v) {
        return null;
    }
}
