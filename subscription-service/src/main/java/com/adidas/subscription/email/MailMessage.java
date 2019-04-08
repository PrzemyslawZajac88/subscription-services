package com.adidas.subscription.email;

import com.adidas.mail.MailMessageEvent;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MailMessage {
    private final String from;
    private final String to;
    private final String subject;
    private final String body;

    MailMessageEvent toEvent() {
        return new MailMessageEvent(from, to, subject, body);
    }
}
