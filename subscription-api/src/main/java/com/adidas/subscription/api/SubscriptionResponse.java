package com.adidas.subscription.api;

import lombok.Getter;

import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;

@Getter
class SubscriptionResponse {
    private final Map<String, String> errors;

    SubscriptionResponse(final Map<String, String> errors) {
        this.errors = errors;
    }

    SubscriptionResponse() {
        this.errors = of();
    }
}
