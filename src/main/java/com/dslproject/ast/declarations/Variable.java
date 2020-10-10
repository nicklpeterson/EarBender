package com.dslproject.ast.declarations;

import com.dslproject.ast.DslVisitor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Variable extends Declaration {
    private List<Note> notes;

    private String instrument;

    private Integer tempo;

    public Variable(String name, List<Note> notes, String instrument, Integer tempo) {
        super(name);
        this.notes = notes;
        this.instrument = instrument;
        this.tempo = tempo;
    }

    @Override
    public <T> T accept(DslVisitor<T> v) {
        return null;
    }

}
