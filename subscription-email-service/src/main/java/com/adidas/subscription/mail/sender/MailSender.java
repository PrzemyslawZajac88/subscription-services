package com.adidas.subscription.mail.sender;

import com.adidas.mail.MailMessageEvent;

public interface MailSender {
    void send(final MailMessageEvent event);
}
