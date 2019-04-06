package com.adidas.subscription.mail;

import com.adidas.mail.MailMessageEvent;
import com.adidas.subscription.mail.sender.MailSender;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class MailConsumer {

    private final MailSender mailSender;

    @KafkaListener(topics = "${kafka.mail.topic}", containerFactory = ConsumerConf.MAIL_CONTAINER_FACTORY)
    public void receive(final MailMessageEvent event) {
        mailSender.send(event);
    }

}
