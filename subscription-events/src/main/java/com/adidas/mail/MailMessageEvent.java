package com.adidas.mail;

import lombok.Getter;

@Getter
public class MailMessageEvent {
    private final String from;
    private final String to;
    private final String body;

    public MailMessageEvent(final String from, final String to, final String body) {
        this.from = from;
        this.to = to;
        this.body = body;
    }
}
