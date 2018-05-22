package com.junitexercises.ch1_5.classroom.booking;

import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class ClassBookingRecordsTest {

    private ClassBookingRecords records = ClassBookingRecords.newInstance();

    private LocalTime time_17 = LocalTime.of(17, 0);
    private LocalTime time_19 = LocalTime.of(19, 0);
    private LocalTime time_20 = LocalTime.of(20, 0);
    private LocalTime time_21 = LocalTime.of(21, 0);
    private LocalTime time_22 = LocalTime.of(22, 0);
    private LocalTime time_23 = LocalTime.of(23, 0);

    @Test
    public void bookShouldReserveAndReservedShouldBeReturned() {
        boolean isBooked = records.book(DayOfWeek.MONDAY, time_19, time_21);
        assertTrue(isBooked);
        assertEquals(records.getBookedHours().iterator().next().getStart(),time_19);
        assertEquals(records.getBookedHours().iterator().next().getEnd(),time_21);
    }

    @Test(expected = IllegalStateException.class)
    public void bookShouldThrowExceptoinWhenStartGreatOrEqualEnd() {
        records.book(DayOfWeek.MONDAY, time_19, time_19);
    }

    @Test(expected = IllegalStateException.class)
    public void bookShouldThrowExceptoinWhenMinutesInStart() {
        LocalTime start = LocalTime.of(19, 1);
        records.book(DayOfWeek.MONDAY, start, time_20);
    }

    @Test(expected = IllegalStateException.class)
    public void bookShouldThrowExceptoinWhenMinutesInEnd() {
        LocalTime end = LocalTime.of(20, 1);
        records.book(DayOfWeek.MONDAY, time_19, end);
    }

    @Test
    public void bookShouldntReserveIfStartInReservedRange() {
        records.book(DayOfWeek.MONDAY, time_19, time_21);
        boolean isBooked = records.book(DayOfWeek.MONDAY, time_20, time_21);
        assertFalse(isBooked);
    }

    @Test
    public void bookShouldntReserveIfEndInReservedRange() {
        records.book(DayOfWeek.MONDAY, time_19, time_21);
        boolean isBooked = records.book(DayOfWeek.MONDAY, time_17, time_20);
        assertFalse(isBooked);
    }

    @Test
    public void bookShouldntReserveIfRangeIncludesAnotherReservations() {
        records.book(DayOfWeek.MONDAY, time_19, time_21);
        boolean isBooked = records.book(DayOfWeek.MONDAY, time_17, time_22);
        assertFalse(isBooked);
    }

    @Test
    public void bookShouldntReserveIfReservationsTheSame() {
        records.book(DayOfWeek.MONDAY, time_19, time_21);
        boolean isBooked = records.book(DayOfWeek.MONDAY, time_19, time_21);
        assertFalse(isBooked);
    }

    @Test
    public void bookShouldAllowCornerReservations() {
        records.book(DayOfWeek.MONDAY, time_19, time_21);
        boolean isBooked = records.book(DayOfWeek.MONDAY, time_21, time_23);
        assertTrue(isBooked);
    }

    @Test
    public void book_DifferentDays_NotIntersect() {
        records.book(DayOfWeek.MONDAY, time_19, time_21);
        boolean isBooked = records.book(DayOfWeek.THURSDAY, time_19, time_21);
        assertTrue(isBooked);
    }

    @Test
    public void book_withEquipment_ContainsEquipment() {
        records.book(DayOfWeek.MONDAY, time_19, time_21, Equipment.MICROPHONE);
        assertEquals(records.getBookedHours().iterator().next().getEquipment().iterator().next(),
                Equipment.MICROPHONE);
    }

}
