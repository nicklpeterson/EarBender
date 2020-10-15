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
    public int getBeats() {
        int beats = 0;
        for (Declaration declaration : declarations) {
            beats += declaration.getBeats();
        }
        return beats;
    }

    @Override
    public List<Integer> getTempoList() {
        List<Integer> tempoList = new ArrayList<>();
        for (Declaration declaration : declarations) {
            tempoList.addAll(declaration.getTempoList());
        }
        return tempoList;
    }

    @Override
    public <T> void accept(T context, DslVisitor<T> v) {
        v.visit(context, this);
    }
}
