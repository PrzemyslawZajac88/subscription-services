package com.adidas.subscription.subscription.domain;

import com.adidas.subscription.producer.SubscriptionEvent;
import com.adidas.subscription.subscription.dto.SubscriptionDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SubscriptionFacade {

    private final SubscriptionRepository repository;
    private final SubscriptionFactory factory;

    public SubscriptionDto saveSubscription(final SubscriptionEvent event) {
        final Subscription subscription = factory.createSubscription(event);
        repository.save(subscription);
        return subscription.dto();
    }

    public boolean isExist(final SubscriptionEvent event) {
        return repository.isExist(event.getNewsletterId().getValue(), event.getEmail());
    }
}
