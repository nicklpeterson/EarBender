package com.dslproject.ast.declarations;

import com.dslproject.libs.DslVisitor;
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

    public int getBeats() {
        return declarations.stream().mapToInt(x -> getBeats()).sum();
    }

    @Override
    public <T> void accept(T context, DslVisitor<T> v) {
        v.visit(context, this);
    }
}
