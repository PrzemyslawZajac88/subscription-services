package com.adidas.subscription.producer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Getter
@ToString
public class SubscriptionEvent {
    private final NewsletterId newsletterId;

    private final String firstName;
    private final String lastName;
    private final String email;
    private final Gender gender;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private final LocalDate dateOfBirth;

    private final Set<Consent> consents;


    @JsonCreator
    SubscriptionEvent(
            @JsonProperty(value = "newsletterId") final NewsletterId newsletterId,
            @JsonProperty(value = "firstName") final String firstName,
            @JsonProperty(value = "lastName") final String lastName,
            @JsonProperty(value = "email") final String email,
            @JsonProperty(value = "gender") final Gender gender,
            @JsonProperty(value = "dateOfBirth") final LocalDate dateOfBirth,
            @JsonProperty(value = "consents") final Set<Consent> consents) {

        this.newsletterId = newsletterId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.consents = consents;
    }

    @Getter
    public static class NewsletterId {
        private final String value;

        @JsonCreator
        public NewsletterId(@JsonProperty("value") final String value) {
            this.value = value;
        }
    }

    public enum Gender {
        MALE, FEMALE
    }

    @ToString
    public static class Consent {
        @Getter
        private final String key;
        @Getter
        private final boolean accept;

        @JsonCreator
        public Consent(@JsonProperty("key") final String key,
                @JsonProperty("accept") final boolean accept) {
            this.key = key;
            this.accept = accept;
        }
    }
}
