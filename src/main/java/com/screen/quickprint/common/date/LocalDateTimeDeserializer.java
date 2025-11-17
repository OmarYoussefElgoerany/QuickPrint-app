//package com.screen.quickprint.common.date;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.OffsetDateTime;
//import java.time.format.DateTimeParseException;
//
//public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
//    @Override
//    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt)
//            throws IOException {
//        String date = p.getText().trim();
//        try {
//            if (date.contains("+") || date.endsWith("Z")) {
//                return OffsetDateTime.parse(date).toLocalDateTime();
//            }
//            return LocalDateTime.parse(date);
//        } catch (DateTimeParseException e) {
//            throw new IOException("Failed to parse date: " + date, e);
//        }
//    }
//}
//
