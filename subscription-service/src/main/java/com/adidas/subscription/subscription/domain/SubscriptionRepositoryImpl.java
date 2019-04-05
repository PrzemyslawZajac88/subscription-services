package com.adidas.subscription.subscription.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

class SubscriptionRepositoryImpl implements SubscriptionRepository {
    private final DynamoDBMapper amazonDynamoDB;

    SubscriptionRepositoryImpl(final DynamoDBMapper mapper) {
        this.amazonDynamoDB = mapper;
    }

    @Override
    public void save(final Subscription subscription) {
        amazonDynamoDB.save(subscription);
    }

    @Override
    public boolean isExist(final String newsletterId, final String email) {

        final Map<String, AttributeValue> eav = ImmutableMap.<String, AttributeValue>builder()
                .put(":newsletterIdVal", new AttributeValue(newsletterId))
                .put(":emailVal", new AttributeValue(email))
                .build();

        final DynamoDBQueryExpression<Subscription> queryExpression = new DynamoDBQueryExpression<Subscription>()
                .withKeyConditionExpression(Subscription.NEWSLETTER_ID + " = :newsletterIdVal and " + Subscription.EMAIL + " = :emailVal ")
                .withExpressionAttributeValues(eav);

        final int count = amazonDynamoDB.count(Subscription.class, queryExpression);
        return count >= 1;
    }
}
