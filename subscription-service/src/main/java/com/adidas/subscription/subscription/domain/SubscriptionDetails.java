package com.adidas.subscription.subscription.domain;

import com.adidas.subscription.subscription.domain.converters.LocalDateToStringTypeConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@DynamoDBDocument
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
class SubscriptionDetails {

    private String lastName = "";
    private String firstName = "";

    @DynamoDBTypeConvertedEnum
    private Gender gender = Gender.NO_INFO;

    @DynamoDBTypeConverted(converter = LocalDateToStringTypeConverter.class)
    private LocalDate dateOfBirth;
}
