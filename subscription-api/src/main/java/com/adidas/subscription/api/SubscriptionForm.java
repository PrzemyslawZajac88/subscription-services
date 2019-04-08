package com.adidas.subscription.api;

import com.adidas.subscription.producer.SubscriptionEvent;
import com.adidas.subscription.producer.SubscriptionEvent.Consent;
import com.adidas.subscription.producer.SubscriptionEvent.NewsletterId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static com.google.common.collect.ImmutableSet.of;

@Data
class SubscriptionForm {

    public static final String SUBSCRIPTION_CONSENT = "SUBSCRIPTION_CONSENT";

    @NotEmpty
    private final String lastName;

    @NotEmpty
    @Email
    private final String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private final LocalDate dateOfBirth;

    @NotNull
    @AssertTrue
    private final Boolean consent;
    @NotNull
    private final Gender gender;
    @NotNull
    private final String newsletterId;

    private final String firstName;

    @JsonCreator
    SubscriptionForm(@JsonProperty(value = "lastName") final String lastName,
                     @JsonProperty(value = "email") final String email,

                     @JsonProperty(value = "dateOfBirth") final LocalDate dateOfBirth,

                     @JsonProperty(value = "consent") final Boolean consent,
                     @JsonProperty(value = "newsletterId") final String newsletterId,
                     @JsonProperty(value = "firstName") final String firstName,
                     @JsonProperty(value = "gender") final Gender gender) {

        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.consent = consent;
        this.newsletterId = newsletterId;
        this.firstName = firstName;
        this.gender = gender;
    }

    SubscriptionEvent toPayload() {
        return SubscriptionEvent.builder()
                .newsletterId(new NewsletterId(newsletterId))
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .consents(of(new Consent(SUBSCRIPTION_CONSENT, consent)))
                .gender(gender.getPayloadGender())
                .dateOfBirth(dateOfBirth)
                .build();
    }

    @Getter
    @AllArgsConstructor
    private enum Gender {
        MALE(SubscriptionEvent.Gender.MALE),
        FEMALE(SubscriptionEvent.Gender.FEMALE);
        private final SubscriptionEvent.Gender payloadGender;
    }
}
