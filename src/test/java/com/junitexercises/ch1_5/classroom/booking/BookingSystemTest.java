package com.junitexercises.ch1_5.classroom.booking;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BookingSystemTest {

    private LocalTime time_17 = LocalTime.of(17, 0);
    private LocalTime time_19 = LocalTime.of(19, 0);
    private LocalTime time_20 = LocalTime.of(20, 0);
    private LocalTime time_21 = LocalTime.of(21, 0);

    private BookingSystem bookingSystem = new BookingSystem();

    private int CLASSROOM_CAPACITY_1 = 1;
    private int CLASSROOM_CAPACITY_2 = 2;
    private int CLASSROOM_CAPACITY_3 = 3;
    private ClassRoom classRoomWithCapacity1 = new ClassRoom(CLASSROOM_CAPACITY_1);
    private ClassRoom classRoomWithCapacity2 = new ClassRoom(CLASSROOM_CAPACITY_2);
    private ClassRoom classRoomWithCapacity3 = new ClassRoom(CLASSROOM_CAPACITY_3);

    @Test
    public void addClassRoom_ForCorrectClassRoom_Succeeded() {
        bookingSystem.addClassRoom(classRoomWithCapacity1);
        assertEquals(classRoomWithCapacity1, bookingSystem.getClassRooms().iterator().next());
    }

    @Test
    public void book_WithCorrectRoomId_Succeeded() {
        bookingSystem.addClassRoom(classRoomWithCapacity1);
        assertTrue(bookingSystem.book(classRoomWithCapacity1.getId(), DayOfWeek.MONDAY, time_17, time_19));
    }

    @Test
    public void book_WithCorrectRoomIdAndMocking_GivesCorrectReservationRecord() {
        bookingSystem.addClassRoom(classRoomWithCapacity1);
        assertTrue(bookingSystem.book(classRoomWithCapacity1.getId(), DayOfWeek.MONDAY, time_17, time_19));
    }


    @Test
    public void book_WithCorrectRoomId_GivesCorrectReservationRecord() {
        bookingSystem.addClassRoom(classRoomWithCapacity1);
        assertTrue(bookingSystem.book(classRoomWithCapacity1.getId(), DayOfWeek.MONDAY, time_17, time_19));
        ClassBookingRecords records = bookingSystem.findBookingRecordsByClassId(classRoomWithCapacity1.getId());
        ClassBookingRecords.BookedRange range = records.getBookedHours().iterator().next();
        assertEquals(DayOfWeek.MONDAY, range.getDayOfWeek());
        assertEquals(time_17, range.getStart());
        assertEquals(time_19, range.getEnd());
        assertTrue(range.getEquipment().isEmpty());
    }

    @Test
    public void book_WithEquipment_GivesEquipmentInReservation() {
        bookingSystem.addClassRoom(classRoomWithCapacity1);
        assertTrue(bookingSystem.book(classRoomWithCapacity1.getId(), DayOfWeek.MONDAY,
                time_17, time_19, Equipment.PROJECTOR, Equipment.WHITEBOARD));
        ClassBookingRecords records = bookingSystem.findBookingRecordsByClassId(classRoomWithCapacity1.getId());
        ClassBookingRecords.BookedRange range = records.getBookedHours().iterator().next();
        assertTrue(range.getEquipment().contains(Equipment.PROJECTOR)
                && range.getEquipment().contains(Equipment.WHITEBOARD));
    }

    @Test
    public void getAvailableRooms_OneRoomAvailable_ReturnsOneRoom() {
        bookingSystem.addClassRoom(classRoomWithCapacity1);
        assertEquals(bookingSystem.getAvailableRooms(DayOfWeek.MONDAY, time_17, time_19).size(), 1);
    }

    @Test
    public void getAvailableRooms_RoomNotAvailable_ReturnsNoRoom() {
        bookingSystem.addClassRoom(classRoomWithCapacity1);
        bookingSystem.book(classRoomWithCapacity1.getId(), DayOfWeek.MONDAY,
                time_17, time_19, Equipment.PROJECTOR, Equipment.WHITEBOARD);
        assertEquals(bookingSystem.getAvailableRooms(DayOfWeek.MONDAY, time_17, time_19).size(), 0);
    }

    @Test
    public void getAvailableRooms_RoomForDifferentTime_ReturnsThisRoom() {
        bookingSystem.addClassRoom(classRoomWithCapacity1);
        bookingSystem.book(classRoomWithCapacity1.getId(), DayOfWeek.MONDAY,
                time_17, time_19, Equipment.PROJECTOR, Equipment.WHITEBOARD);
        assertEquals(bookingSystem.getAvailableRooms(DayOfWeek.MONDAY, time_20, time_21).size(), 1);
    }

    @Test
    public void book_WithCapacity_booksCorrectRoom() {
        bookingSystem.addClassRoom(classRoomWithCapacity1);
        bookingSystem.addClassRoom(classRoomWithCapacity2);
        bookingSystem.addClassRoom(classRoomWithCapacity3);
        assertTrue(bookingSystem.book(3, DayOfWeek.MONDAY, time_20, time_21));
        assertEquals(bookingSystem.findBookingRecordsByClassId(classRoomWithCapacity3.getId()).getBookedHours().size(), 1);
    }

    @Test
    public void book_WithNotAvailableCapacity_dontBooksRoom() {
        bookingSystem.addClassRoom(classRoomWithCapacity1);
        bookingSystem.addClassRoom(classRoomWithCapacity2);
        bookingSystem.addClassRoom(classRoomWithCapacity3);
        assertFalse(bookingSystem.book(5, DayOfWeek.MONDAY, time_20, time_21));
        assertEquals(bookingSystem.findBookingRecordsByClassId(classRoomWithCapacity1.getId()).getBookedHours().size(), 0);
        assertEquals(bookingSystem.findBookingRecordsByClassId(classRoomWithCapacity2.getId()).getBookedHours().size(), 0);
        assertEquals(bookingSystem.findBookingRecordsByClassId(classRoomWithCapacity3.getId()).getBookedHours().size(), 0);
    }
}