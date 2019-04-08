package com.adidas.subscription.mail.sender;

import com.adidas.mail.MailMessageEvent;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@AllArgsConstructor
class MailSenderServiceImpl implements MailSenderService  {

    private static final Logger logger = LoggerFactory.getLogger(MailSenderServiceImpl.class);

    private final JavaMailSender emailSender;

    @Override
    public void send(final MailMessageEvent event) {
        logger.info("Sending message: {}", event);

        final SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(event.getTo());
        message.setFrom(event.getFrom());
        message.setSubject(event.getSubject());
        message.setText(event.getBody());
        emailSender.send(message);

        logger.info("Sent message: {}", event);
    }
}
