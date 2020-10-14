package com.dslproject.ast.executions;

import com.dslproject.ast.DslVisitor;
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
    public <T> T accept(DslVisitor<T> v) {
        return null;
    }
}
