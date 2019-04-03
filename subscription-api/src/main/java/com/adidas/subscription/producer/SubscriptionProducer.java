package com.adidas.subscription.producer;

public interface SubscriptionProducer {
    void sendSubscription(SubscriptionEvent payload);
}
