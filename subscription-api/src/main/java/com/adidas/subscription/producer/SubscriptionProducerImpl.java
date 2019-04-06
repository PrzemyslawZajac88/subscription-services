package com.adidas.subscription.producer;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.security.access.prepost.PreAuthorize;

@AllArgsConstructor
class SubscriptionProducerImpl implements SubscriptionProducer {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionProducerImpl.class);

    private final String topicName;
    private final KafkaOperations<String, SubscriptionEvent> producer;

    @Override
    public void sendSubscription(final SubscriptionEvent event) {
        producer.send(topicName, event);

        logger.info("Send subscription event {}", event);
    }
}
