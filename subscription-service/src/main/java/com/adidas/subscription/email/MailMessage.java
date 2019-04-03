package com.adidas.subscription.email;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MailMessage {
    private final String email;
    private final String body;
}
