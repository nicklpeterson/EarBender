package com.dslproject.ast.Declarations;

import com.dslproject.util.ListMethods;
import com.dslproject.ast.DslVisitor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Delegate;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class DslList extends Declaration {
    @Delegate(types = ListMethods.class)
    private List<Variable> variables;

    @Override
    public <T> T accept(DslVisitor<T> v) {
        return null;
    }
}
