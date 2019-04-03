package com.adidas.subscription.producer;

import com.google.common.collect.ImmutableMap;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@Configuration
class SubscriptionProducerConfig {

    @Value("${kafka.bootstrap}")
    private String bootstrap;

    @Value("${kafka.subscription.topic}")
    private String subscriptionTopicName;

    @Bean
    public SubscriptionProducerImpl subscriptionProducer() {
        return new SubscriptionProducerImpl(subscriptionTopicName, producer());
    }

    private ProducerFactory<String, SubscriptionEvent> producerFactory() {
        final Map<String, Object> configProps = ImmutableMap.<String, Object>builder()
                .put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap)
                .put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
                .put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class)
                .put(ProducerConfig.RETRIES_CONFIG, 11)
                .put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 500)
                .put(ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG, 100)
                .build();

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    private KafkaTemplate<String, SubscriptionEvent> producer() {
        return new KafkaTemplate<>(producerFactory());
    }

}
