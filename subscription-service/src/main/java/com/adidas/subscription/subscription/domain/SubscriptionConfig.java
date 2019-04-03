package com.adidas.subscription.subscription.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SubscriptionConfig {

    @Bean
    public SubscriptionFacade subscriptionFacade(final DynamoDBMapper mapper) {
        return new SubscriptionFacade(new SubscriptionRepositoryImpl(mapper), new SubscriptionFactory());
    }

}
