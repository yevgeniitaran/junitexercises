package com.junitexercises.ch1_5.classroom.booking;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClassRoomTest {

    private final int CAPACITY_1 = 1;
    private final int CAPACITY_10 = 10;
    private final int INCORRECT_CAPACITY_0 = 0;
    private final int INCORRECT_CAPACITY_MINUS_TEN = -10;

    @Test
    public void Constructor_withCapacity_Succeeded() {
        ClassRoom classRoomA = new ClassRoom(CAPACITY_1);
        assertEquals(classRoomA.getCapacity(), CAPACITY_1);
        ClassRoom classRoomB = new ClassRoom(CAPACITY_10);
        assertEquals(classRoomB.getCapacity(), CAPACITY_10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Constructor_withIncorrectCapacity0_ThrowsException() {
        ClassRoom classRoom = new ClassRoom(INCORRECT_CAPACITY_0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Constructor_withIncorrectCapacityMinus10_ThrowsException() {
        ClassRoom classRoom = new ClassRoom(INCORRECT_CAPACITY_MINUS_TEN);
    }
}