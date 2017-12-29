package com.kafeneio.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.kafeneio.constants.ApplicationConstant;

@Component
public class CustomDateDeserializer extends StdDeserializer<Date> {

    /**
    * 
    */
    private static final long serialVersionUID = 1L;
    private SimpleDateFormat formatter = new SimpleDateFormat(ApplicationConstant.DATE_TIME_FORMAT);

    public CustomDateDeserializer() {
    this(null);
    }

    public CustomDateDeserializer(Class<?> vc) {
    super(vc);
    }

    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext context)
        throws IOException, JsonProcessingException {
    String date = jsonparser.getText();
    try {
        return formatter.parse(date);
    } catch (ParseException e) {
        throw new RuntimeException(e);
    }
    }
}
