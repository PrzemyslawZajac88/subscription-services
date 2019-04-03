package com.adidas.subscription.subscription.consumer;

import com.adidas.subscription.email.MailSender;
import com.adidas.subscription.subscription.domain.SubscriptionFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SubscriptionConsumerConfig {

    @Bean
    public SubscriptionService subscriptionService(final SubscriptionFacade facade, final MailSender mailSender) {
        return new SubscriptionService(facade, mailSender);
    }
}
