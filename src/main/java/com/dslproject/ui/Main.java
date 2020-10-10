package com.dslproject.ui;



import com.dslproject.ast.DslParser;
import com.dslproject.ast.Program;
import com.dslproject.music.Music;
import com.dslproject.music.MusicLayer;
import com.dslproject.music.MusicVar;
import com.dslproject.exceptions.TokenizerException;
import com.dslproject.libs.DslTokenizer;
import com.dslproject.libs.Tokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {


    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(Main.class);
        try {
            Tokenizer tokenizer = DslTokenizer.createDslTokenizer("input.txt");
            DslParser parser = DslParser.getParser(tokenizer);
            Program ast = parser.parseProgram();
          
//             playMusic();
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("Exiting Program");
        }
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

        music.addRhythmLayer("..X...X...X...XO");
        music.addRhythmLayer("..S...S...S...S.");
        music.addRhythmLayer("````````````````");
        music.addRhythmLayer("...............+");

        music.setRhythmLength(3);

        music.playMusic();
    }
}
