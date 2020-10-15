package com.dslproject.ast.executions;

import com.dslproject.ast.declarations.Declaration;
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
    public int getBeats() {
        int beats = 0;
        for (Execution execution : executions) {
            beats += execution.getBeats();
        }
        return beats;
    }

    @Override
    public List<Integer> getTempoList() {
        List<Integer> tempoList = new ArrayList<>();
        for (Execution execution : executions) {
            tempoList.addAll(execution.getTempoList());
        }
        return tempoList;
    }

    @Override
    public <T> void accept(T context, DslVisitor<T> v) {
        v.visit(context, this);
    }
}
