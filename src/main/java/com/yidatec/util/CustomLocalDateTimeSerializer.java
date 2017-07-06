package com.yidatec.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author QuShengWen
 */
public class CustomLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    private final static DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void serialize(LocalDateTime value, JsonGenerator jgen, SerializerProvider prov) throws IOException, JsonProcessingException {

        jgen.writeString(value.format(DATE_TIME_FORMAT));
//        jgen.writeStringField("date", DATE_FORMAT.print(value));
//        jgen.writeStringField("time", TIME_FORMAT.print(value));

    }
}