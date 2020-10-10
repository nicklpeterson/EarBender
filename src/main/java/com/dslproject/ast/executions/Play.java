package com.dslproject.ast.executions;

import com.dslproject.ast.Declarations.Declaration;
import com.dslproject.ast.DslVisitor;
import com.dslproject.util.ListMethods;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Play extends Execution {
    List<Declaration> declarations;

    @Override
    public <T> T accept(DslVisitor<T> v) {
        return null;
    }
}
