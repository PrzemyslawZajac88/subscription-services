package com.adidas.subscription.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
class MailSenderImpl implements MailSender {

    private static final Logger logger = LoggerFactory.getLogger(MailSenderImpl.class);

    @Override
    public void sendMail(final MailMessage mailMessage) {
        logger.info("Send mail mock: {}", mailMessage);
    }
}
