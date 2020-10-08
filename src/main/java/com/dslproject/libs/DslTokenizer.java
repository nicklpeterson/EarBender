package com.dslproject.libs;

import com.dslproject.exceptions.TokenizerException;
import com.dslproject.ui.Main;
import com.dslproject.util.DslConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DslTokenizer implements Tokenizer {

    final private Logger log = LoggerFactory.getLogger(Main.class);
    final private List<String> regexList = DslConstants.REGEX_LIST;
    final private String separatorPattern;
    private final String inputProgram;
    private String[] tokens;
    private int currentToken = 0;

    public static Tokenizer createDslTokenizer(String filename) throws TokenizerException {
        return new DslTokenizer(filename);
    }

    @Override
    public String getNext() {
        String token="";
        if (currentToken<tokens.length){
            token = tokens[currentToken];
            currentToken++;
        }
        else
            token="NULLTOKEN";
        return token;
    }

    @Override
    public boolean checkToken(String regexp) {
        String s = checkNext();
        log.debug("comparing: |"+s+"|  to  |"+regexp+"|");
        return (s.matches(regexp));
    }

    @Override
    public String getAndCheckNext(String regexp) {
        String s = getNext();
        if (!s.matches(regexp)) {
            throw new RuntimeException("Unexpected next token for Parsing! Expected something matching: " + regexp + " but got: " + s);
        }
        log.debug("matched: "+s+"  to  "+regexp);
        return s;
    }

    @Override
    public boolean moreTokens() {
        return currentToken<tokens.length;
    }

    private DslTokenizer(String filename) throws TokenizerException {
        try {
            this.separatorPattern = listToRegexPattern(DslConstants.SEPARATOR_LIST);
            this.inputProgram = Files.readString(Paths.get(filename));
        } catch (IOException e) {
            log.error("Failed to load file");
            throw new TokenizerException(e.getMessage());
        }
        tokenize();
    }

    /*
    Tokenize the program
     */
    private void tokenize() throws TokenizerException {
        log.info("INPUT PROGRAM:\n{}\n", this.inputProgram);
        List<String> tokenList = splitBySeparators(this.inputProgram);
        log.debug("Split by Separator:\n{}\n", tokenList);
        tokenList = splitByLeadingToken(tokenList);
        log.debug("Split by Leading Token: \n{}\n", tokenList);
        this.tokens = tokenList.toArray(new String[0]);
    }

    /*
    Split program into tokens by separators as defined in the field separatorsList
     */
    private List<String> splitBySeparators(String inputProgram) {
        Pattern separatorPattern = Pattern.compile(this.separatorPattern);
        Matcher separatorMatcher = separatorPattern.matcher(inputProgram);
        List<String> tokenList = new ArrayList<>();
        int previousEnd = 0;
        while (separatorMatcher.find()) {
            tokenList.add(inputProgram.substring(previousEnd, separatorMatcher.start()));
            tokenList.add(inputProgram.substring(separatorMatcher.start(), separatorMatcher.end()));
            previousEnd = separatorMatcher.end();
        }
        tokenList.add(inputProgram.substring(previousEnd));
        return tokenList;
    }

    /*
    Recursive function that splits remaining tokens by the leading token using the algorithm from
    exercise 1
     */
    private List<String> splitByLeadingToken(List<String> substringList) throws TokenizerException {
        if (substringList.size() == 0) {
            return substringList;
        }
        String sub = substringList.get(0);
        substringList.remove(0);
        List<String> tokenList;
        if (sub.isEmpty()) {
            tokenList = splitByLeadingToken(substringList);
        } else if (isToken(sub)) {
            tokenList = splitByLeadingToken(substringList);
            tokenList.add(0, sub);
        } else {
            // Trim leading whitespace
            sub = sub.trim();
            Matcher matchedStart = getMatchForLeadingToken(sub);
            String token = sub.substring(matchedStart.start(), matchedStart.end());
            substringList.add(0, sub.substring(matchedStart.end()));
            tokenList = splitByLeadingToken(substringList);
            tokenList.add(0, token);
        }
        return tokenList;
    }

    /*
    Returns true if the string is a separator or matches one of the regex patterns in DslConstants
     */
    private boolean isToken(String token) {
        Boolean matchesRegexString = token.matches(this.separatorPattern);
        if (!matchesRegexString) {
            for (String regex : this.regexList) {
                if (token.matches(regex)) {
                    matchesRegexString = true;
                    break;
                }
            }
        }
        return matchesRegexString;
    }

    /*
    Return the matcher that matches the leading token
    Throws TokenizerException if an exception is not found
     */
    private Matcher getMatchForLeadingToken(String sub) throws TokenizerException {
        final List<Matcher> matchers = new ArrayList<>();
        Matcher matchedStart = null;
        for (String regex : this.regexList) {
            matchers.add(Pattern.compile(regex).matcher(sub));
        }
        for (Matcher matcher : matchers) {
            if (matcher.lookingAt()) {
                matchedStart = matcher;
            }
        }
        if (matchedStart == null) {
            throw new TokenizerException("Invalid Substring: " + sub);
        }
        return matchedStart;
    }

    /*
    returns a regex pattern that will match any of the strings in listToConvert
     */
    private String listToRegexPattern(List<String> listToConvert) {
        String separatorRegex = listToConvert.toString();
        separatorRegex = separatorRegex.replace(" ", "");
        separatorRegex = separatorRegex.replaceAll(",", "|");
        return separatorRegex.replaceAll("\\[|\\]", "");
    }

    private String checkNext(){
        String token="";
        if (currentToken<tokens.length){
            token = tokens[currentToken];
        }
        else
            token="NO_MORE_TOKENS";
        return token;
    }
}
