package org.example.farmmanagementfx.data.util;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateUtil {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Converts a LocalDate to a Date.
     *
     * @param localDate the LocalDate to convert
     * @return the converted Date
     */
    public static Date convertToDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Converts java.util.Date to java.time.LocalDate.
     *
     * @param date the java.util.Date to convert
     * @return the converted java.time.LocalDate
     */
    public static LocalDate convertToLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    /**
     * Formats the given Date as a String in the format dd.MM.yyyy.
     *
     * @param date the Date to format
     * @return the formatted date as a StringProperty
     */
    public static StringProperty formatDate(java.util.Date date) {
        if (date == null) {
            return new SimpleStringProperty("");
        }

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Format LocalDate as String
        String formattedDate = localDate.format(DATE_FORMATTER);

        return new SimpleStringProperty(formattedDate);
    }

    /**
     * Returns a StringConverter for LocalDate that uses the format yyyy-MM-dd.
     *
     * @return the StringConverter
     */
    public static StringConverter<LocalDate> getDateConverter() {
        return new StringConverter<>() {

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return DATE_FORMATTER.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    try {
                        return LocalDate.parse(string, DATE_FORMATTER);
                    } catch (DateTimeParseException e) {
                        e.printStackTrace(System.err);
                        return null;
                    }
                } else {
                    return null;
                }
            }
        };
    }

    /**
     * Converts java.sql.Date to java.util.Date.
     *
     * @param sqlDate the java.sql.Date to convert
     * @return the converted java.util.Date
     */
    public static java.util.Date convertToDate(java.sql.Date sqlDate) {
        if (sqlDate == null) {
            return null;
        }
        return new java.util.Date(sqlDate.getTime());
    }

    /**
     * Converts java.util.Date to java.sql.Date.
     *
     * @param utilDate the java.util.Date to convert
     * @return the converted java.sql.Date
     */
    public static java.sql.Date convertToSqlDate(java.util.Date utilDate) {
        if (utilDate == null) {
            return null;
        }
        return new java.sql.Date(utilDate.getTime());
    }
}
