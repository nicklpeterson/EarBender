package com.dslproject.ast.executions;

import com.dslproject.libs.DslVisitor;
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
    public int getBeats() {
        return executions.stream().mapToInt(Execution::getBeats).sum();
    }

    @Override
    public <T> void accept(T context, DslVisitor<T> v) {
        v.visit(context, this);
    }
}
