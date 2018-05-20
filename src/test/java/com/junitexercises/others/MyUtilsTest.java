package com.junitexercises.others;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class MyUtilsTest {

    private Object[] getStringWithReverse() {
        return $(
                $("abcde", "edcba"),
                $(" ", " "),
                $("xyz zyx", "xyz zyx")
        );
    }

    @Test
    @Parameters(method = "getStringWithReverse")
    public void reverseShouldReverseString(String input, String ER) {
        assertEquals(MyUtils.reverse(input), ER);
    }

    @Test(expected = NullPointerException.class)
    public void reverseShouldThrowNullPointerOnNull() {
        MyUtils.reverse(null);
    }
}
