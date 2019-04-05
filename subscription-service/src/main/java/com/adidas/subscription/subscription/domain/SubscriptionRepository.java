package com.adidas.subscription.subscription.domain;


interface SubscriptionRepository {
    void save(Subscription subscription);

    boolean isExist(String newsletterId, String email);
}
