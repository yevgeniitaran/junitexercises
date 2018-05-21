package com.junitexercises.ch1_5.others;

public class FahrenheitCelciusConverter {
    public static double toFahrenheit(double celsius) {
        return celsius * 9 / 5 + 32;
    }

    public static double toCelcius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }
}
