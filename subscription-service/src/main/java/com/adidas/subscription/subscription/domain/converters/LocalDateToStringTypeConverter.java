package com.adidas.subscription.subscription.domain.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.LocalDate;

public class LocalDateToStringTypeConverter implements DynamoDBTypeConverter<String, LocalDate> {

    @Override
    public String convert(final LocalDate localDate) {
        return localDate.toString();
    }

    @Override
    public LocalDate unconvert(final String s) {
        return LocalDate.parse(s);
    }
}