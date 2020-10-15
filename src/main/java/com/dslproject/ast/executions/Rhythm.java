package com.dslproject.ast.executions;

import com.dslproject.libs.DslVisitor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Rhythm extends Execution {
    private String layer;
    private int times;

    @Override
    public int getBeats() {
        return 0;
    }

    @Override
    public <T> void accept(T context, DslVisitor<T> v) {
        v.visit(context, this);
    }
}
