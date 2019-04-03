package com.adidas.subscription.subscription.consumer;

import com.adidas.subscription.producer.SubscriptionEvent;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@EnableBinding(SubscriptionStreamProcessor.class)
class SubscriptionConsumer {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionConsumer.class);

    private final SubscriptionService subscriptionService;

    @StreamListener(SubscriptionStreamProcessor.INPUT)
    public void consumeReport(final SubscriptionEvent event) {
        Try.runRunnable(() -> subscriptionService.storeSubscription(event))
                .onFailure(e -> logger.error("Error when saving subscription", e));
    }
}
