package com.junitexercises.ch1_5.classroom.booking;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingSystem {

    private Map<ClassRoom, ClassBookingRecords> roomBookings = new HashMap<>();

    public void addClassRoom(ClassRoom classRoom) {
        roomBookings.putIfAbsent(classRoom, ClassBookingRecords.newInstance());
    }

    public Collection<ClassRoom> getClassRooms() {
        return roomBookings.keySet();
    }

    public ClassBookingRecords findBookingRecordsByClassId(String classId) {
        ClassRoom classRoom = findClassRoomById(classId);
        return roomBookings.get(classRoom);
    }

    public Collection<ClassRoom> getAvailableRooms(DayOfWeek dayOfWeek, LocalTime start, LocalTime end) {
        return roomBookings.entrySet().stream()
                .filter(entry -> entry.getValue().isBookingPossible(dayOfWeek, start, end))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public boolean book(String classRoomId, DayOfWeek dayOfWeek, LocalTime start, LocalTime end, Equipment... equipment) {
        ClassRoom classRoom = findClassRoomById(classRoomId);
        return roomBookings.computeIfAbsent(classRoom, c -> ClassBookingRecords.newInstance())
                .book(dayOfWeek, start, end, equipment);
    }

    public boolean book(int capacity, DayOfWeek dayOfWeek, LocalTime start, LocalTime end, Equipment... equipment) {
        Optional<Map.Entry<ClassRoom, ClassBookingRecords>> optionalEntry = roomBookings.entrySet().stream()
                .filter(entry -> entry.getKey().getCapacity() >= capacity)
                .sorted(Comparator.comparingInt(e -> e.getKey().getCapacity()))
                .filter(entry -> entry.getValue().isBookingPossible(dayOfWeek, start, end))
                .findAny();
        optionalEntry.ifPresent(e -> e.getValue().book(dayOfWeek, start, end, equipment));
        return optionalEntry.isPresent();
    }

    private ClassRoom findClassRoomById(String id) {
        return roomBookings.keySet().stream()
                .filter(room -> room.getId().equals(id))
                .findAny().get();
    }

}