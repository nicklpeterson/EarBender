package com.dslproject.ast.declarations;

import com.dslproject.ast.DslVisitor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DslList extends Declaration {
    private List<Declaration> declarations;

    public DslList(String name, List<Declaration> declarations) {
        super(name);
        this.declarations = declarations;
    }

    @Override
    public <T> T accept(DslVisitor<T> v) {
        return null;
    }
}
