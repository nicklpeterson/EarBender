package com.dslproject.ast.Declarations;

import com.dslproject.ast.Statement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Declaration extends Statement {
    private String name;
}
