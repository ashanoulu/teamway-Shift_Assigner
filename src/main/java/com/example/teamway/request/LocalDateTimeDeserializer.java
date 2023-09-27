package com.example.teamway.request;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {


    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {

        LocalDateTime lt = null;
        try {
            lt = LocalDateTime.parse("2018-02-05T11:59:11.332Z");
        } catch (Exception e) {
            System.out.printf(e.toString());
        }
        return lt;
    }
}

