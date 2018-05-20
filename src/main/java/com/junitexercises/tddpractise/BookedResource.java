package com.junitexercises.tddpractise;

import java.time.LocalTime;
import java.util.Collection;
import java.util.TreeSet;

public class BookedResource {

    private Collection<BookedRange> bookedHours = new TreeSet<>();

    public boolean book(LocalTime start, LocalTime end) {
        if (start.getMinute() != 0 || end.getMinute() != 0) {
            throw new IllegalStateException("Start or end date must not contain minutes. Start = " + start
                    + ", end = " + end);
        }
        if (start.compareTo(end) >= 0) {
            throw new IllegalStateException("Start must be larger then end, input: start = " + start
                    + "end = " + end);
        }
        boolean overlap = bookedHours.stream().anyMatch(bookedRange -> (
                bookedRange.start.compareTo(end) < 0 && bookedRange.end.compareTo(start) > 0));
        if (overlap) {
            return false;
        }
        bookedHours.add(new BookedRange(start, end));
        return true;
    }

    public Collection<BookedRange> getBookedHours() {
        return this.bookedHours;
    }

    public class BookedRange implements Comparable<BookedRange> {
        private LocalTime start;
        private LocalTime end;

        private BookedRange(LocalTime start, LocalTime end) {
            this.start = start;
            this.end = end;
        }

        public LocalTime getStart() {
            return start;
        }

        public LocalTime getEnd() {
            return end;
        }

        @Override
        public int compareTo(BookedRange o) {
            return this.start.compareTo(o.getStart());
        }
    }
}
