package com.adidas.subscription.mail.sender;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
class MailSenderConfig {

    @Bean
    public MailSenderService mailSenderService() {
        return new MailSenderServiceImpl(new JavaMailSenderImpl());
    }
}
