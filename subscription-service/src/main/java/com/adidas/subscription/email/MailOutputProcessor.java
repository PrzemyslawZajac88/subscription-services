package com.adidas.subscription.email;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

interface MailOutputProcessor {
    String OUTPUT = "mailOutput";

    @Output(MailOutputProcessor.OUTPUT)
    SubscribableChannel mailOutput();
}
