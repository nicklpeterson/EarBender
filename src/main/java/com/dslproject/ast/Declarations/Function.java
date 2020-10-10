package com.dslproject.ast.Declarations;

import com.dslproject.ast.DslVisitor;
import com.dslproject.ast.Execution.Execution;
import com.dslproject.util.ListMethods;
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
public class Function extends Declaration {
    @Delegate(types = ListMethods.class)
    private List<Execution> executions;

    @Override
    public <T> T accept(DslVisitor<T> v) {
        return null;
    }

}
