package com.dslproject.ast.executions;

import com.dslproject.ast.declarations.Declaration;
import com.dslproject.ast.declarations.DslList;
import com.dslproject.exceptions.ValidatorException;
import com.dslproject.libs.DslVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PlaySync extends Execution {
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
    public boolean validateStructure() {
        for (Declaration declaration : declarations) {
            if(!declaration.getClass().equals(DslList.class)) {
                throw new ValidatorException("wrong playSync structure");
            };
            declaration.validateStructure();
        }
        return true;
    }

    @Override
    public <T, C> T accept(C context, DslVisitor<T, C> v) {
        return v.visit(context, this);
    }
}
