package com.junitexercises.others;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class FahrenheitCelciusConverterTest {

    private Object[] getCelciusToFahrenheitValues() {
        return $(
                $(32, 0),
                $(98, 37),
                $(212, 100)
        );
    }

    @Test
    @Parameters(method = "getCelciusToFahrenheitValues")
    public void shouldConvertCelciusToFahrenheit(double fahrenheit, double celcius) {
        assertEquals(fahrenheit, FahrenheitCelciusConverter.toFahrenheit(celcius), 1);
    }

    @Test
    @Parameters(method = "getCelciusToFahrenheitValues")
    public void shouldConvertFahrenheitToCelcius(double fahrenheit, double celcius) {
        assertEquals(celcius, FahrenheitCelciusConverter.toCelcius(fahrenheit), 1);
    }
}