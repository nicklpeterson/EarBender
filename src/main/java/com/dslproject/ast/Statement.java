package com.dslproject.ast;

import java.util.List;

public abstract class Statement extends Node {
    public abstract int getBeats();
    public abstract List<Integer> getTempoList();
}
