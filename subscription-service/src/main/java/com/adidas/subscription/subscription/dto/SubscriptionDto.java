package com.adidas.subscription.subscription.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Getter
public class SubscriptionDto {

    private final String newsletterId;
    private final String email;

    private final String firstName;
    private final String lastName;
    private final Gender gender;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private final LocalDate dateOfBirth;

    private final Set<Consent> consents;

    public static enum Gender {
        MALE, FEMALE
    }

    @AllArgsConstructor
    @Getter
    public static class Consent {
        private final String key;
        private final boolean value;
    }
}
