package com.adidas.subscription.api;

import lombok.Getter;

import java.util.Map;
import java.util.UUID;

import static com.google.common.collect.ImmutableMap.of;

@Getter
class SubscriptionResponse {
    private final String requestId;
    private final Map<String, String> errors;

    SubscriptionResponse(final String requestId, final Map<String, String> errors) {
        this.requestId = requestId;
        this.errors = errors;
    }

    SubscriptionResponse(final String requestId) {
        this(requestId, of());
    }
}
