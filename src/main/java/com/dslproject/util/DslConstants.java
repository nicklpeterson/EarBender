package com.dslproject.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DslConstants {
    final static public String SET_REGEX = "SET [A-Za-z0-9]+";
    final static public String NOTES_REGEX = "NOTES\\(([A-G](\\[(q|h|w)*\\])?)(,( *)([A-G](\\[(q|h|w)*\\])?))*\\)";
    // TODO: Add more instruments
    final static public String INSTRUMENT_REGEX = "INSTRUMENT\\(((piano)|(violin)|(trumpet))\\)";
    final static public String TEMPO_REGEX = "TEMPO\\(([0-9]+)\\)";
    final static public String RHYTHM_REGEX = "RHYTHM LAYER1\\((H|B|S|C|.){8}\\) LAYER2\\((H|B|S|C|.){8}\\) LAYER3\\((H|B|S|C|.){8}\\)";

    // Make sure to add new regex strings here so they get passed to the tokenizer
    final static public List<String> REGEX_LIST = Arrays.asList(SET_REGEX, NOTES_REGEX, INSTRUMENT_REGEX, TEMPO_REGEX, RHYTHM_REGEX);
}
