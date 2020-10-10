package com.dslproject.ast.Execution;

import com.dslproject.ast.DslVisitor;
import com.dslproject.util.ListMethods;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Delegate;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Loop extends Execution {
    @Delegate(types = ListMethods.class)
    List<Execution> executions;

    @Override
    public <T> T accept(DslVisitor<T> v) {
        return null;
    }
}
