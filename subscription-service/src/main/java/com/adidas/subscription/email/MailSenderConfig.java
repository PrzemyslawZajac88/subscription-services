package com.adidas.subscription.email;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableBinding(MailOutputProcessor.class)
class MailSenderConfig {

    @Bean
    public MailSenderService mailSenderService(
            @Qualifier(MailOutputProcessor.OUTPUT) final MessageChannel channel) {
        return new MailSenderServiceImpl(channel);
    }
}
