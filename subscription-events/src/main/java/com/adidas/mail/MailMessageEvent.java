package com.adidas.mail;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MailMessageEvent {

    private final String from;
    private final String to;
    private final String subject;
    private final String body;

    @JsonCreator
    public MailMessageEvent(@JsonProperty("from") final String from,
                            @JsonProperty("to") final String to,
                            @JsonProperty("subject") final String subject,
                            @JsonProperty("body") final String body) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
    }
}
