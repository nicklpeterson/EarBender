package com.dslproject.ast;

public interface DslVisitor<T> {

    T visit(Program program);
}
