package com.adidas.subscription.mail.sender;

import com.adidas.mail.MailMessageEvent;

public interface MailSenderService {
    void send(final MailMessageEvent event);
}
