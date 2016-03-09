package com.debes.plateplanner.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Description here.
 *
 * @author lesley.debes
 */
public class DateTimeUtil {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy hh:mm:ss a", Locale.US);
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.US);

    public static String format(LocalDateTime localDateTime) {
        return dateTimeFormatter.format(localDateTime);
    }

    public static String format(LocalDate localDate) {
        return dateFormatter.format(localDate);
    }

    public static LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString, dateFormatter);
    }

    public static LocalDateTime parseTimestamp(String timestampString) {
        return LocalDateTime.parse(timestampString, dateTimeFormatter);
    }

}
