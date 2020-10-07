package com.dslproject.libs;

import com.dslproject.ast.DslVisitor;

public abstract class Node {
    abstract public <T> T accept(DslVisitor<T> v); // let the visitors in!
}