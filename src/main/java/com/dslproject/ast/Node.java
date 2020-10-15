package com.dslproject.ast;

import com.dslproject.libs.DslVisitor;

public abstract class Node {
    abstract public <T, C> T accept(C context, DslVisitor<T, C> v); // let the visitors in!
}