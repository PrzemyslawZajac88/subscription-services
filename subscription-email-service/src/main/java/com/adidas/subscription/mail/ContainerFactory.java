package com.adidas.subscription.mail;

import lombok.experimental.UtilityClass;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

@UtilityClass
final class ContainerFactory {

    static <T> KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, T>> createListenerContainerFactory(final Class<T> transportObjectClass, final Map<String, Object> config) {
        final ConcurrentKafkaListenerContainerFactory<String, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(transportObjectClass)));
        factory.getContainerProperties().setAckOnError(false);
        factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.RECORD);
        factory.getContainerProperties().setErrorHandler(new SeekToCurrentErrorHandler());
        return factory;
    }

}