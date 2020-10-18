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

    private String note;

    public Note(String noteString) {
        String[] tokens = noteString.split("\\[|\\]");
        this.note = tokens[0];
        this.length = tokens.length > 1 ? tokens[1].trim() : "q";
    }

    public String toString() {
        return this.note + this.length;
    }

    public Integer getBeats() {
        Integer beats = 0;
        char[] tokens = length.toCharArray();
        for (char token : tokens) {
            switch(token) {
                case 'o':
                    beats += 1;
                    break;
                case 'x':
                    beats += 2;
                    break;
                case 't':
                    beats += 4;
                    break;
                case 's':
                    beats += 8;
                    break;
                case 'i':
                    beats += 16;
                    break;
                case 'q':
                    beats += 32;
                    break;
                case 'h':
                    beats += 64;
                    break;
                case 'w':
                    beats += 128;
                    break;
            }
        }
        return beats;
    }
}