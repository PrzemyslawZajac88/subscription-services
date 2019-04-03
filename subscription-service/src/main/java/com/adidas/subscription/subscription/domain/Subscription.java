package com.adidas.subscription.subscription.domain;


import com.adidas.subscription.subscription.dto.SubscriptionDto;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import static com.adidas.subscription.subscription.dto.SubscriptionDto.Gender.valueOf;

@DynamoDBTable(tableName = "subscription")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
class Subscription {
    @DynamoDBHashKey()
    private String newsletterID;
    @DynamoDBRangeKey()
    private String email;

    private SubscriptionDetails details;

    @DynamoDBIgnore
    private Set<SubscriptionDto.Consent> consents;

    @DynamoDBIgnore
    public SubscriptionDto dto() {
        return SubscriptionDto.builder()
                .newsletterId(newsletterID)
                .email(email)

                .firstName(details.getFirstName())
                .lastName(details.getLastName())
                .dateOfBirth(details.getDateOfBirth())

                .gender(valueOf(details.getGender().name()))
                .consents(consents)
                .build();
    }
}
