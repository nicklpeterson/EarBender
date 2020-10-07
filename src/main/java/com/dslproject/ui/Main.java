package com.dslproject.ui;


import com.dslproject.music.Music;
import com.dslproject.music.MusicLayer;
import com.dslproject.music.MusicVar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {


    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);
        logger.info("Starting Main");

        playMusic();
    }

    public static void playMusic(){
        Music music = new Music();

        MusicVar var1 = new MusicVar("C D E F", 80, "Piano", 2);
        MusicVar var2 = new MusicVar("C D E F", 80, "Violin", 2);

        MusicVar var3 = new MusicVar("R A B C", 220, "Piano", 2);
        MusicVar var4 = new MusicVar("R A B C", 220, "Violin", 2);

        MusicLayer layer1 = new MusicLayer(new MusicVar[]{var1, var4, var1}, 0);
        MusicLayer layer2 = new MusicLayer(new MusicVar[]{var2, var3, var2}, 1);

        music.addMusicLayer(layer1);
        music.addMusicLayer(layer2);

//        music.addPattern(layer1);
//        music.addPattern(layer2);

//        music.addPattern(var3, 0);
//        music.addPattern(var4, 1);

        music.addRhythmLayer("..X...X...X...XO");
        music.addRhythmLayer("..S...S...S...S.");
        music.addRhythmLayer("````````````````");
        music.addRhythmLayer("...............+");

        music.setRhythmLength(3);

        //Song of Storms - The Legend of Zelda - Ocarina of Time
//        String notes = "D F D D F D E F E F E C A A D F G A A D F G E D F D D F D E F E F E C A " +
//                "A D F G A A D";
//        music.addPattern(notes, 120, "Ocarina", 0, 2);

        music.playMusic();
    }
}
