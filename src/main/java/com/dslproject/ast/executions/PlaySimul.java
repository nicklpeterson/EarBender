package com.dslproject.ast.executions;

import com.dslproject.libs.DslVisitor;
import com.dslproject.ast.declarations.Declaration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PlaySimul extends Execution {
    List<Declaration> declarations;

    @Override
    public Integer getBeats() {
        return declarations.get(0).getBeats();
    }

    @Override
    public boolean validateStructure() {
        for (Declaration declaration : declarations) {
            declaration.validateStructure();
        }
        return true;
    }

    @Override
    public <T, C> T accept(C context, DslVisitor<T, C> v) {
        return v.visit(context, this);
    }
}
