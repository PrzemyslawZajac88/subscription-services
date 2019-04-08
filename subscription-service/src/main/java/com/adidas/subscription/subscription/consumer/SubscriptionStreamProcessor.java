package com.adidas.subscription.subscription.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

interface SubscriptionStreamProcessor {
    String INPUT = "subscriptionsInput";

    @Input(SubscriptionStreamProcessor.INPUT)
    SubscribableChannel subscriptionsInput();
}