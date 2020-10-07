package com.dslproject.music;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class MusicLayer {
    @Getter
    @Setter
    public MusicVar[] musicVars = new MusicVar[]{};

    @Getter
    @Setter
    public int channel = 0;

}
