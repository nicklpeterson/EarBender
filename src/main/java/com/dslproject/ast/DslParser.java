package com.dslproject.ast;

import com.dslproject.libs.Tokenizer;

import java.util.ArrayList;
import java.util.List;

public class DslParser {

    private final Tokenizer tokenizer;

    public DslParser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public static DslParser getParser(Tokenizer tokenizer) {
        return new DslParser(tokenizer);
    }

    public Program parseProgram() {
        List<Statement> statements = new ArrayList<>();
        while (tokenizer.moreTokens()) {
            statements.add(parseStatement());
        }
        return null;
    }

    private Statement parseStatement() {
        //Stub
        // TODO: Implement
        return null;

    }



}
