package com.dslproject.ast.Declarations;

import com.dslproject.util.MusicNote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Note {
    // Length is the integer amount of quarters
    // F[qqq] => length = 3 and G[wq] => length = 5
    private int length;

    private MusicNote note;
}