package com.dslproject.ui;



import com.dslproject.ast.Statement;
import com.dslproject.libs.*;
import com.dslproject.ast.Program;
import com.dslproject.music.Music;
import com.dslproject.music.MusicLayer;
import com.dslproject.music.MusicVar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(Main.class);
        try {
            Tokenizer tokenizer = DslTokenizer.createDslTokenizer("input.txt");
            Program ast = DslParser.getParser(tokenizer).parseProgram();
            log.info("Successfully Parsed Tokens");
            System.out.println(DslValidator.getValidator(ast).validateProgram());
            DslEvaluator.getEvaluator(ast).evaluateProgram();
            log.info("Evaluation done");

        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("Exiting Program");
        }
    }
}
