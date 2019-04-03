package com.adidas.subscription.subscription.domain;

import com.adidas.subscription.producer.SubscriptionEvent;
import com.adidas.subscription.subscription.dto.SubscriptionDto;

import java.util.Set;
import java.util.stream.Collectors;

import static com.adidas.subscription.subscription.domain.Gender.valueOf;
import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

class SubscriptionFactory {
    Subscription createSubscription(final SubscriptionEvent event) {
        requireNonNull(event);
        return Subscription.builder()
                .consents(toConsents(event.getConsents()))
                .newsletterID(event.getNewsletterId().getValue())
                .email(event.getEmail())
                .details(createDetails(event))
                .build();
    }

    private SubscriptionDetails createDetails(final SubscriptionEvent event) {
        return new SubscriptionDetails(
                event.getFirstName(),
                event.getLastName(),
                getGender(event),
                event.getDateOfBirth()
        );
    }

    private Gender getGender(final SubscriptionEvent event) {
        final String genderName = ofNullable(event.getGender())
                .map(Enum::name)
                .orElse(null);

        return valueOf(genderName);
    }

    private Set<SubscriptionDto.Consent> toConsents(final Set<SubscriptionEvent.Consent> consents) {
        return consents.stream()
                .map(consent -> new SubscriptionDto.Consent(consent.getKey(), consent.isAccept()))
                .collect(Collectors.toSet());
    }
}
