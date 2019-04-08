package com.adidas.subscription.mail.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

interface MailConsumerProcessor {
    String INPUT = "mailInput";

    @Input(MailConsumerProcessor.INPUT)
    SubscribableChannel handleMail();
}
