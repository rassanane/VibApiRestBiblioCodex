package fr.keltou.biblio.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateUtils {
    private DateUtils() {}

    // Parse une date ISO yyyy-MM-dd en LocalDate
    public static LocalDate parseIso(String value) {
        if (value == null) return null;
        try {
            return LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}

