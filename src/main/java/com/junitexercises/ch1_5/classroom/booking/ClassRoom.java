package com.junitexercises.ch1_5.classroom.booking;

import java.util.UUID;

public class ClassRoom {
    private int capacity;
    private String id;

    public ClassRoom(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity can't be lower then 0, entered capacity: " + capacity);
        }
        this.id = UUID.randomUUID().toString();
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }
}