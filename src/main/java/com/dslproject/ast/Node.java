package com.dslproject.ast;

import com.dslproject.libs.DslVisitor;

public abstract class Node {
    abstract public <T> void accept(T context, DslVisitor<T> v); // let the visitors in!
}