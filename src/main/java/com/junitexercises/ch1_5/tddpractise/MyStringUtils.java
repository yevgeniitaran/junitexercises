package com.junitexercises.ch1_5.tddpractise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyStringUtils {

    private static Pattern pattern = Pattern.compile("\\d{3,}");

    public static Collection<String> parse(String input) {
        Matcher matcher = pattern.matcher(input);
        Collection<String> result = new ArrayList<>();
        while(matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }
}
