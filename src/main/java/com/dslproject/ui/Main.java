package com.dslproject.ui;


import com.dslproject.exceptions.TokenizerException;
import com.dslproject.libs.DslTokenizer;
import com.dslproject.libs.Tokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.theory.ChordProgression;

public class Main {

    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(Main.class);
        try {
            Tokenizer tokenizer = DslTokenizer.createDslTokenizer("input.txt");
        } catch (TokenizerException e) {
            log.error(e.getMessage());
            log.error("Exiting Program");
        }
    }

    private void testMusicPlayer() {
        Pattern pattern = new ChordProgression("I IV V")
                .distribute("7%6")
                .allChordsAs("$0 $0 $0 $0 $1 $1 $0 $0 $2 $1 $0 $0")
                .eachChordAs("$0ia100 $1ia80 $2ia80 $3ia80 $4ia100 $3ia80 $2ia80 $1ia80")
                .getPattern()
                .setInstrument("Piano")
                .setTempo(100);
        new Player().play(pattern.repeat(1));
    }

}
