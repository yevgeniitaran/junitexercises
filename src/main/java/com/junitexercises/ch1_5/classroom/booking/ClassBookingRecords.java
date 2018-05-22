package com.junitexercises.ch1_5.classroom.booking;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

public class ClassBookingRecords {

    private ClassBookingRecords() {}

    public static ClassBookingRecords newInstance() {
        return new ClassBookingRecords();
    }

    private Collection<BookedRange> bookedHours = new TreeSet<>();

    public boolean book(DayOfWeek dayOfWeek, LocalTime start, LocalTime end, Equipment... equipment) {
        if (isBookingPossible(dayOfWeek, start, end)) {
            bookedHours.add(new BookedRange(dayOfWeek, start, end, equipment));
            return true;
        }
        return false;
    }

    public boolean isBookingPossible(DayOfWeek dayOfWeek, LocalTime start, LocalTime end) {
        if (start.getMinute() != 0 || end.getMinute() != 0) {
            throw new IllegalStateException("Start or end date must not contain minutes. Start = " + start
                    + ", end = " + end);
        }
        if (start.compareTo(end) >= 0) {
            throw new IllegalStateException("Start must be larger then end, input: start = " + start
                    + "end = " + end);
        }
        boolean overlap = bookedHours.stream().anyMatch(bookedRange -> (
                bookedRange.dayOfWeek.equals(dayOfWeek) && bookedRange.start.compareTo(end) < 0
                        && bookedRange.end.compareTo(start) > 0));
        return !overlap;
    }

    public Collection<BookedRange> getBookedHours() {
        return this.bookedHours;
    }

    public class BookedRange implements Comparable<BookedRange> {
        private DayOfWeek dayOfWeek;
        private LocalTime start;
        private LocalTime end;
        private Collection<Equipment> equipment;

        private BookedRange(DayOfWeek dayOfWeek, LocalTime start, LocalTime end, Equipment... equipment) {
            this.dayOfWeek = dayOfWeek;
            this.start = start;
            this.end = end;
            this.equipment = new HashSet<>(Arrays.asList(equipment));
        }

        public DayOfWeek getDayOfWeek() {
            return dayOfWeek;
        }

        public LocalTime getStart() {
            return start;
        }

        public LocalTime getEnd() {
            return end;
        }

        public Collection<Equipment> getEquipment() {
            return equipment;
        }

        @Override
        public int compareTo(BookedRange o) {
            return Comparator.comparing(BookedRange::getDayOfWeek)
                    .thenComparing(BookedRange::getStart)
                    .compare(this, o);
        }

        @Override
        public String toString() {
            return "BookedRange{" +
                    "dayOfWeek=" + dayOfWeek +
                    ", start=" + start +
                    ", end=" + end +
                    ", equipment=" + equipment +
                    '}';
        }
    }
}
