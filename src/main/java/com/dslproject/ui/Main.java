package com.dslproject.ui;

import com.dslproject.libs.*;
import com.dslproject.ast.Program;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {


    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(Main.class);
        try {
            Tokenizer tokenizer = DslTokenizer.createDslTokenizer("rhody.txt");
            Program ast = DslParser.getParser(tokenizer).parseProgram();
            log.info("Successfully Parsed Tokens");
            log.info("Validation: " + DslValidator.getValidator(ast).validateProgram());
            log.info("Succesfully validated AST");
            // DslEvaluator.getEvaluator(ast).evaluateProgram();
            DSLEvaluator.getEvaluator(ast).evaluateProgram();
            log.info("Evaluation done");

        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("Exiting Program");
        }
    }
}
