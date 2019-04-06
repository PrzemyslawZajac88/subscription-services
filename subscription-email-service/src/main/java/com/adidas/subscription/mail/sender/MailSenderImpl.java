package com.adidas.subscription.mail.sender;

import com.adidas.mail.MailMessageEvent;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@AllArgsConstructor
class MailSenderImpl implements MailSender {

    private final JavaMailSender emailSender;

    @Override
    public void send(final MailMessageEvent event) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(event.getTo());
        message.setFrom(event.getFrom());
        message.setSubject("MAIL from mail-service");
        message.setText(event.getBody());
        emailSender.send(message);
    }
}
