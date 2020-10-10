package com.dslproject.ast.Declarations;

import com.dslproject.ast.Statement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Declaration extends Statement {
    protected String name;
}
