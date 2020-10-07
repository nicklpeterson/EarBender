package com.dslproject.music;

import lombok.*;

@AllArgsConstructor
public class MusicVar {

    @Getter
    @Setter
    public String noteStr = "";

    @Getter
    @Setter
    public int tempo = 100;

    @Getter
    @Setter
    public String instrument = "Piano";

//    @Getter
//    @Setter
//    public int channel = 0;

    @Getter
    @Setter
    public int loopTimes = 1;

}
