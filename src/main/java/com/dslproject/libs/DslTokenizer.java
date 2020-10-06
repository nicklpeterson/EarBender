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
    final private List<String> separatorsList = Arrays.asList("\n", "\r\n");
    final private List<String> regexList = DslConstants.REGEX_LIST;
    final private String separatorPattern;
    private String inputProgram;
    private String[] tokens;
    private int currentToken = 0;


    private DslTokenizer(String filename) throws TokenizerException {
        try {
            this.separatorPattern = listToRegexPattern(this.separatorsList);
            this.inputProgram = Files.readString(Paths.get(filename));
        } catch (IOException e) {
            log.info("Failed to load file");
            throw new TokenizerException(e.getMessage());
        }
        tokenize();
    }

    public static Tokenizer createDslTokenizer(String filename) throws TokenizerException {
        return new DslTokenizer(filename);
    }

    private void tokenize() throws TokenizerException {
        log.info("\n\n" + this.inputProgram);
        List<String> tokenList = splitBySeparators(this.inputProgram);
        List<String> test = new ArrayList<>();
        log.debug("Split by Separator: \n {}", tokenList);
        tokenList = splitByLeadingToken(tokenList);
        log.debug("Split by Leading Token: \n {}", tokenList);
        this.tokens = tokenList.toArray(new String[0]);
    }

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

    private String listToRegexPattern(List<String> listToConvert) {
        String separatorRegex = listToConvert.toString();
        separatorRegex = separatorRegex.replace(" ", "");
        separatorRegex = separatorRegex.replaceAll(",", "|");
        return separatorRegex.replaceAll("\\[|\\]", "");
    }

    @Override
    public String getNext() {
        return null;
    }

    @Override
    public boolean checkToken(String regexp) {
        return false;
    }

    @Override
    public String getAndCheckNext(String regexp) {
        return null;
    }

    @Override
    public boolean moreTokens() {
        return false;
    }
}
