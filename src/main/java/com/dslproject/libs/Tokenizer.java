package com.dslproject.libs;

public interface Tokenizer {
    String getNext();

    boolean checkToken(String regexp);

    String getAndCheckNext(String regexp);

    boolean moreTokens();
}
