package com.dslproject.ast.Declarations;

import com.dslproject.util.ListMethods;
import com.dslproject.ast.DslVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Delegate;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Variable extends Declaration {
    @Delegate(types = ListMethods.class)
    List<Note> notes;

    private String instrument;

    private Integer tempo;

    @Override
    public <T> T accept(DslVisitor<T> v) {
        return null;
    }

}
