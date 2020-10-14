package com.dslproject.ui;



import com.dslproject.libs.DslEvaluator;
import com.dslproject.libs.DslParser;
import com.dslproject.ast.Program;
import com.dslproject.music.Music;
import com.dslproject.music.MusicLayer;
import com.dslproject.music.MusicVar;
import com.dslproject.libs.DslTokenizer;
import com.dslproject.libs.Tokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {


    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(Main.class);
        try {
            Tokenizer tokenizer = DslTokenizer.createDslTokenizer("input.txt");
            Program ast = DslParser.getParser(tokenizer).parseProgram();
            log.info("Successfully Parsed Tokens");

            DslEvaluator.getEvaluator(ast).evaluateProgram();
            log.info("Evaluation done");

        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("Exiting Program");
        }
    }
}
