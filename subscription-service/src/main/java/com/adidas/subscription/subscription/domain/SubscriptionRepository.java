package com.adidas.subscription.subscription.domain;


public interface SubscriptionRepository {
    <T> void save(T subscription);

    boolean isExist(String newsletterId, String email);
}
