package com.njupt.sniper.app.model.http;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import org.springframework.hateoas.hal.Jackson2HalModule;

/**
 * author：Zsl
 * date：2016/8/30
 */
public abstract class ObjectMapperBuilder {

    public static ObjectMapper build() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new ISO8601DateFormat());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        mapper.registerModule(new Jackson2HalModule());
        return mapper;
    }
}
