package com.junitexercises.ch1_5.domain;

public interface User {
    String getPassword();

    void setPassword(String passwordMd5);
}