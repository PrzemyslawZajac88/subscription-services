package com.adidas.subscription.producer;

import com.adidas.subscription.producer.exception.CannotSendSubscriptionException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaOperations;

import java.util.concurrent.ExecutionException;

@AllArgsConstructor
class SubscriptionProducerImpl implements SubscriptionProducer {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionProducerImpl.class);

    private final String topicName;
    private final KafkaOperations<String, SubscriptionEvent> producer;

    @Override
    public void sendSubscription(final SubscriptionEvent event) {
        try {
            producer.send(topicName, event).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new CannotSendSubscriptionException(e);
        }

        logger.info("Send subscription event {}", event);
    }
}
