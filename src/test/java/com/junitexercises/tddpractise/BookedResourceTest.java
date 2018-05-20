package com.junitexercises.tddpractise;

import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class BookedResourceTest {

    private BookedResource resource = new BookedResource();

    @Test
    public void bookShouldReserveAndReservedShouldBeReturned() {
        LocalTime start = LocalTime.of(19, 0);
        LocalTime end = LocalTime.of(21, 0);
        boolean isBooked = resource.book(start, end);
        assertTrue(isBooked);
        assertEquals(resource.getBookedHours().iterator().next().getStart(),start);
        assertEquals(resource.getBookedHours().iterator().next().getEnd(),end);
    }

    @Test(expected = IllegalStateException.class)
    public void bookShouldThrowExceptoinWhenStartGreatOrEqualEnd() {
        LocalTime start = LocalTime.of(19, 0);
        LocalTime end = LocalTime.of(19, 0);
        resource.book(start, end);
    }

    @Test(expected = IllegalStateException.class)
    public void bookShouldThrowExceptoinWhenMinutesInStart() {
        LocalTime start = LocalTime.of(19, 1);
        LocalTime end = LocalTime.of(20, 0);
        resource.book(start, end);
    }

    @Test(expected = IllegalStateException.class)
    public void bookShouldThrowExceptoinWhenMinutesInEnd() {
        LocalTime start = LocalTime.of(19, 0);
        LocalTime end = LocalTime.of(20, 1);
        resource.book(start, end);
    }

    @Test
    public void bookShouldntReserveIfStartInReservedRange() {
        LocalTime start = LocalTime.of(19, 0);
        LocalTime end = LocalTime.of(21, 0);
        resource.book(start, end);
        start = LocalTime.of(20, 0);
        end = LocalTime.of(21, 0);
        boolean isBooked = resource.book(start, end);
        assertFalse(isBooked);
    }

    @Test
    public void bookShouldntReserveIfEndInReservedRange() {
        LocalTime start = LocalTime.of(19, 0);
        LocalTime end = LocalTime.of(21, 0);
        resource.book(start, end);
        start = LocalTime.of(17, 0);
        end = LocalTime.of(20, 0);
        boolean isBooked = resource.book(start, end);
        assertFalse(isBooked);
    }

    @Test
    public void bookShouldntReserveIfRangeIncludesAnotherReservations() {
        LocalTime start = LocalTime.of(19, 0);
        LocalTime end = LocalTime.of(21, 0);
        resource.book(start, end);
        start = LocalTime.of(17, 0);
        end = LocalTime.of(22, 0);
        boolean isBooked = resource.book(start, end);
        assertFalse(isBooked);
    }

    @Test
    public void bookShouldntReserveIfReservationsTheSame() {
        LocalTime start = LocalTime.of(19, 0);
        LocalTime end = LocalTime.of(21, 0);
        resource.book(start, end);
        start = LocalTime.of(19, 0);
        end = LocalTime.of(21, 0);
        boolean isBooked = resource.book(start, end);
        assertFalse(isBooked);
    }

    @Test
    public void bookShouldAllowCornerReservations() {
        LocalTime start = LocalTime.of(19, 0);
        LocalTime end = LocalTime.of(21, 0);
        resource.book(start, end);
        start = LocalTime.of(21, 0);
        end = LocalTime.of(23, 0);
        boolean isBooked = resource.book(start, end);
        assertTrue(isBooked);
    }




}
