package com.junitexercises.ch1_5.tddpractise;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class MyStringUtilsTest {

    private static Object[] getStringsForThreeNumbersTestWithResults() {
        return $(
                $("abc 12", Collections.EMPTY_LIST),
                $("cdefg 345 12bb23", Collections.singletonList("345")),
                $("cdefg 345 12bbb33 67899tt", Arrays.asList("345","67899"))
        );
    }

    @Test
    @Parameters(method = "getStringsForThreeNumbersTestWithResults")
    public void parseShouldReturnListOfThreeDigitNumbers(String input, Collection<String> result) {
        Collection<String> parsedString = MyStringUtils.parse(input);
        assertEquals(result, parsedString);
    }

    @Test(expected = NullPointerException.class)
    public void parseShouldThrowNullPointerOnNullInput() {
        MyStringUtils.parse(null);
    }

}
