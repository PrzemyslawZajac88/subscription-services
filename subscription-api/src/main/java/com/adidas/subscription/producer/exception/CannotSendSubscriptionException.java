package com.adidas.subscription.producer.exception;

public class CannotSendSubscriptionException extends RuntimeException {
    public CannotSendSubscriptionException(final Exception e) {
        super(e);
    }
}
