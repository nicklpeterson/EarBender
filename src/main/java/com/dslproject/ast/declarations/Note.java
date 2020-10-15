package com.dslproject.ast.declarations;

import com.dslproject.util.MusicNote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Note {
    // Length is string representation of length
    // i.e. qqqh or hhw
    private String length;

    private MusicNote note;

    public Note(String noteString) {
        String[] tokens = noteString.split("\\[|\\]");
        this.note = stringToNote(tokens[0]);
        this.length = tokens.length > 1 ? tokens[1].trim() : "q";
    }

    public String toString() {
        return this.note.name() + this.length;
    }

    private MusicNote stringToNote(String note) {
        MusicNote musicNote;
        switch (note.trim()) {
            case "A":
                musicNote = MusicNote.A;
                break;
            case "B":
                musicNote = MusicNote.B;
                break;
            case "C":
                musicNote = MusicNote.C;
                break;
            case "D":
                musicNote = MusicNote.D;
                break;
            case "E":
                musicNote = MusicNote.E;
                break;
            case "F":
                musicNote = MusicNote.F;
                break;
            default:
                musicNote = MusicNote.G;
                break;
        }
        return musicNote;
    }
}