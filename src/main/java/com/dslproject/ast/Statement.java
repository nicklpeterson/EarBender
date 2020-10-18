package com.dslproject.ast;

public abstract class Statement extends Node {
    public abstract Integer getBeats();
    public abstract boolean validateStructure();
}
