package com.dslproject.ast.executions;

import com.dslproject.libs.DslVisitor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Rhythm extends Execution {
    private List<String> layers;

    @Override
    public Integer getBeats() {
        return 0;
    }

    @Override
    public boolean validateStructure() {
        return true;
    }

    @Override
    public <T, C> T accept(C context, DslVisitor<T, C> v) {
        return v.visit(context, this);
    }
}
