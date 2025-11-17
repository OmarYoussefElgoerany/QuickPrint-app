//package com.screen.quickprint.common.date;
//
//import java.time.Duration;
//import java.time.LocalDate;
//import java.time.OffsetDateTime;
//
//public class DateUtils {
//    public static LocalDate parseToLocalDate(String dateString) {
//        if (dateString == null || dateString.isBlank()) {
//            return null;
//        }
//        try {
//            return OffsetDateTime.parse(dateString).toLocalDate();
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Invalid date format: " + dateString, e);
//        }
//    }
//
//    public static String formatDuration(Duration duration) {
//        long hours = duration.toHours();
//        long minutes = duration.minusHours(hours).toMinutes();
//        return String.format("%dh%02dm", hours, minutes);
//    }
//
//    public static boolean isWithinLast30Days(LocalDate date) {
//        if (date == null) return false;
//        return !date.isBefore(LocalDate.now().minusDays(30));
//    }
//}
