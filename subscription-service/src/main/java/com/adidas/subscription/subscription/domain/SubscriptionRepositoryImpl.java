package com.adidas.subscription.subscription.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.Condition;

class SubscriptionRepositoryImpl implements SubscriptionRepository {
    private final DynamoDBMapper amazonDynamoDB;

    public SubscriptionRepositoryImpl(final DynamoDBMapper mapper) {
        this.amazonDynamoDB = mapper;
    }

    @Override
    public <T> void save(final T subscription) {
        amazonDynamoDB.save(subscription);
    }

    @Override
    public boolean isExist(final String newsletterId, final String email) {
        //TODO move aws related things outside domain package
        final Condition rangeKeyCondition = new Condition().withAttributeValueList(new AttributeValue(email));
        final DynamoDBQueryExpression<Subscription> condition = new DynamoDBQueryExpression<Subscription>()
                .withHashKeyValues(Subscription.builder().newsletterID(newsletterId).build())
                .withRangeKeyCondition(email, rangeKeyCondition);

        final int count = amazonDynamoDB.count(Subscription.class, condition);
        return count >= 1;
    }
}
