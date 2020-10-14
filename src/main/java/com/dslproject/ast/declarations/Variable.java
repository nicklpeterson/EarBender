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

    public String getNotesString(){
        StringBuilder noteStr = new StringBuilder();
        for (Note note: notes) {
            noteStr.append(" ").append(note.getNoteStr());
        }
        return noteStr.toString();
    }

    public String getRestNoteString(){
        StringBuilder noteStr = new StringBuilder();
        for (int i = 0; i < notes.size(); i++) {
            noteStr.append(" ").append("R");
        }
        return noteStr.toString();
    }

    public int getNotesSize(){
        return notes.size();
    }

    @Override
    public <T> T accept(DslVisitor<T> v) {
        return null;
    }

}
