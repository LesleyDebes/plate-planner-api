package com.debes.plateplanner.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.sql.Date;

/**
 * @author lesley.debes
 */
public class DateSerializer extends JsonSerializer<Date> {

    /**
     * Method that can be called to ask implementation to serialize values of type this serializer handles.
     *
     * @param value       Value to serialize; can <b>not</b> be null.
     * @param gen         Generator used to output resulting Json content
     * @param serializers Provider that can be used to get serializers for
     */
    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeString(DateTimeUtil.format(value.toLocalDate()));
    }
}
