package com.dslproject.ast.executions;

import com.dslproject.libs.DslVisitor;
import com.dslproject.ast.declarations.Declaration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PlaySimul extends Execution {
    List<Declaration> declarations;

    @Override
    public int getBeats() {
        return declarations.stream().mapToInt(x -> getBeats()).sum();
    }

    @Override
    public <T> void accept(T context, DslVisitor<T> v) {
        v.visit(context, this);
    }
}
