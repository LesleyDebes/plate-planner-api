package com.debes.plateplanner.util;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * @author lesley.debes
 */
public class DateTimeUtilTest {

    @Test
    public void test_formatLocalDateTime() {
        assertEquals("March 1, 2016 9:00:00 AM", DateTimeUtil.format(LocalDateTime.of(2016, 3, 1, 9, 0, 0)));
    }

    @Test
    public void test_formatLocalDate() {
        assertEquals("March 1, 2016", DateTimeUtil.format(LocalDate.of(2016, 3, 1)));
    }

    @Test
    public void test_parseDate() {
        assertEquals(LocalDate.of(2016, 3, 1), DateTimeUtil.parseDate("March 1, 2016"));
    }

    @Test
    public void test_parseDateTime() {
        assertEquals(LocalDateTime.of(2016, 3, 1, 9, 0, 0), DateTimeUtil.parseTimestamp("March 1, 2016 9:00:00 AM"));
    }
}
