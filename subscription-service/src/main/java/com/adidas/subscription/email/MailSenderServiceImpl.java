package com.adidas.subscription.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import static org.springframework.integration.support.MessageBuilder.withPayload;

@AllArgsConstructor
class MailSenderServiceImpl implements MailSenderService {

    private static final Logger logger = LoggerFactory.getLogger(MailSenderServiceImpl.class);

    private final MessageChannel output;

    @Override
    public void sendMail(final MailMessage mailMessage) {
        output.send(withPayload(mailMessage.toEvent()).build());
        logger.info("Send mail mock: {}", mailMessage);
    }
}
