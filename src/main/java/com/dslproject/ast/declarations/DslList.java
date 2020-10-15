package com.dslproject.ast.declarations;

import com.dslproject.libs.DslVisitor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DslList extends Declaration {
    private List<Declaration> declarations;

    public DslList(String name, List<Declaration> declarations) {
        super(name);
        this.declarations = declarations;
    }

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
