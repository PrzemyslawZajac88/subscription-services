package com.adidas.subscription.subscription.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

interface SubscriptionStreamProcessor {
    String INPUT = "subscriptionsInput";
    String OUTPUT = "mailOutput";

    @Input(SubscriptionStreamProcessor.INPUT)
    SubscribableChannel subscriptionsInput();

    @Output(SubscriptionStreamProcessor.OUTPUT)
    SubscribableChannel mailOutput();
}