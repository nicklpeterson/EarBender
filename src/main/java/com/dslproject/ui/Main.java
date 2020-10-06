package com.dslproject.ui;


import com.dslproject.music.Music;
import org.jfugue.rhythm.Rhythm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.theory.ChordProgression;

public class Main {


    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);
        logger.info("Starting Main");

        Music music = new Music();

        music.addPattern("C D E F", 80, "Piano", 0, 3);
        music.addPattern("C D E F", 80, "Violin", 1, 3);

        music.addPattern("R A B C", 220, "Piano", 0, 3);
        music.addPattern("R A B C", 220, "Violin", 1, 3);

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
