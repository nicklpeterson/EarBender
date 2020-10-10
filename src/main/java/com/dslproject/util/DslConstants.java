package com.dslproject.util;

import java.util.Arrays;
import java.util.List;

public class DslConstants {
    final static public List<String> SEPARATOR_LIST = Arrays.asList("\\n", "\\r\\n");
    final static public String VAR_REGEX = "SET VAR [A-Za-z0-9]+";
    final static public String LIST_REGEX = "SET LIST [A-Za-z0-9]+\\([A-Za-z0-9]+(,( *)[A-Za-z0-9]+)*\\)";
    final static public String NOTES_REGEX = "NOTES\\(([A-G](\\[(q|h|w)*\\])?)(,( *)([A-G](\\[(q|h|w)*\\])?))*\\)";
    final static public String INSTRUMENT_REGEX = "INSTRUMENT\\(((piano)|(violin)|(trumpet)|(guitar)|(bass))\\)"; // TODO: Add more instruments
    final static public String TEMPO_REGEX = "TEMPO\\(([0-9]+)\\)";
    final static public String RHYTHM_REGEX = "RHYTHM LAYER1\\((H|B|S|C|.){8}\\) LAYER2\\((H|B|S|C|.){8}\\) LAYER3\\((H|B|S|C|.){8}\\)";
    final static public String START_REGEX = "START";
    final static public String SIMUL_REGEX = "PLAY SIMUL [A-Za-z0-9]+(,( *)[A-Za-z0-9]+)*";
    final static public String LOOP_REGEX = "LOOP [0-9]+ TIMES";
    final static public String PLAY_REGEX = "PLAY [A-Za-z0-9]+(,( *)[A-Za-z0-9]+)*";
    final static public String END_REGEX = "END LOOP";
    final static public String STOP_REGEX = "STOP";
    final static public String FUNCTION_REGEX = "FUNCTION [A-Za-z0-9]+";
    final static public String OPEN_BRACKET = "\\{";
    final static public String CLOSED_BRACKET = "\\}";
    final static public String FUNCTION_CALL_REGEX = "DO [A-Za-z0-9]+";
    final static public String NEWLINE_REGEX = "\\n|\\r|\\r\\n";


    // Make sure to add new regex strings here so they get passed to the tokenizer
    final static public List<String> REGEX_LIST = Arrays.asList(
            VAR_REGEX,
            LIST_REGEX,
            NOTES_REGEX,
            INSTRUMENT_REGEX,
            TEMPO_REGEX,
            RHYTHM_REGEX,
            PLAY_REGEX,
            LOOP_REGEX,
            START_REGEX,
            END_REGEX,
            STOP_REGEX,
            FUNCTION_REGEX,
            FUNCTION_CALL_REGEX,
            OPEN_BRACKET,
            CLOSED_BRACKET,
            SIMUL_REGEX);
}
