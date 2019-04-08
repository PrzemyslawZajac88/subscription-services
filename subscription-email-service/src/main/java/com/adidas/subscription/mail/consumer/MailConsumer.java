package com.adidas.subscription.mail.consumer;

import com.adidas.mail.MailMessageEvent;
import com.adidas.subscription.mail.sender.MailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@EnableBinding(MailConsumerProcessor.class)
class MailConsumer {

    private final MailSenderService mailSenderService;

    @StreamListener(MailConsumerProcessor.INPUT)
    public void handle(final MailMessageEvent event) {
        mailSenderService.send(event);
    }
}
