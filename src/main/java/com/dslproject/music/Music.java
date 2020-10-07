package com.dslproject.music;

import com.dslproject.ui.Main;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.theory.ChordProgression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Music {

    Logger logger = LoggerFactory.getLogger(Main.class);
    Rhythm rhythm = new Rhythm();
    Player player = new Player();
    List<Pattern> patternList = new ArrayList<Pattern>();

    /**
     * Constructor
     */
    public Music(){

    }

    /**
     * Add a patten to be played later
     *
     * @param musicVar     MusicVar object
     * @param channel      channel to play the MusicVar
     */
    public void addPattern(MusicVar musicVar, int channel){

        // I think there is a bug in jfugue on setting the tempo
        // for multiple patterns on multiple channel.
        // It's related how they put to the order of the pattern string when we call pattern.setTempo(tempo);.
        // To resolve this issue, we will avoid using methods like pattern.setTempo. And we build the pattern
        // string manually.
        // https://stackoverflow.com/questions/19673293/jfugue-not-changing-tempo-in-the-middle-of-a-song-with-2-voices

//        StringBuilder resultNotes = new StringBuilder();
//        for(int i=0; i<loopTimes; i++){
//            resultNotes.append(" ").append(notes);
//        }
//
//        Pattern pattern = new Pattern(resultNotes.toString());
//        pattern.setVoice(channel);
//        pattern.setTempo(tempo);
//        pattern.setInstrument(instrument);
//
//        logger.info("pattern:  " + pattern.toString());
//
//        patternList.add(pattern);

//        for(int i=0; i<loopTimes; i++){
//            patternList.add(pattern);
//        }

        String patternStr = "";

        patternStr += "V" + channel;
        patternStr += " " + "T" + musicVar.getTempo();
        patternStr += " " + "I[" + musicVar.getInstrument() + "]";

        StringBuilder resultNotes = new StringBuilder();
        for(int i=0; i<musicVar.getLoopTimes(); i++){
            resultNotes.append(" ").append(musicVar.getNoteStr());
        }

        patternStr += " " + resultNotes.toString();

        Pattern pattern = new Pattern(patternStr);
        patternList.add(pattern);

    }

    /**
     * Add a music layer to be played later
     *
     * @param musicLayer     a musicLayer object
     */
    public void addMusicLayer(MusicLayer musicLayer){

        MusicVar[] musicVars = musicLayer.getMusicVars();
        int channel = musicLayer.getChannel();

        for (MusicVar musicVar : musicVars) {
            String patternStr = "";

            patternStr += "V" + channel;
            patternStr += " " + "T" + musicVar.getTempo();
            patternStr += " " + "I[" + musicVar.getInstrument() + "]";

            StringBuilder resultNotes = new StringBuilder();
            for (int j = 0; j < musicVar.getLoopTimes(); j++) {
                resultNotes.append(" ").append(musicVar.getNoteStr());
            }

            patternStr += " " + resultNotes.toString();

            Pattern pattern = new Pattern(patternStr);
            patternList.add(pattern);
        }

    }

    /**
     * Add a layer to the rhythm
     * @param layer A string of rhythm e.x. "..X...X...X...XO"
     */
    public void addRhythmLayer(String layer){
        rhythm.addLayer(layer);
    }

    /**
     * Set the length of the rhythm
     * @param length the length of the rhythm
     */
    public void setRhythmLength(int length){
        rhythm.setLength(length);
    }

    /**
     * Play all the defined patterns and rhythm
     */
    public void playMusic(){

//        logger.info("Size of list:  " + patternList.size());
//        logger.info("list content:  " + patternList.toString());

        patternList.add(rhythm.getPattern());
        player.play(patternList.toArray(new Pattern[0]));
    }


}
