package com.adidas.subscription.subscription.consumer;

import com.adidas.subscription.email.MailMessage;
import com.adidas.subscription.email.MailSender;
import com.adidas.subscription.producer.SubscriptionEvent;
import com.adidas.subscription.subscription.domain.SubscriptionFacade;
import com.adidas.subscription.subscription.dto.SubscriptionDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
class SubscriptionService {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionService.class);

    private static final String EMAIL_FROM = "adidas@adids.com";

    private final SubscriptionFacade subscriptionFacade;
    private final MailSender mailSender;

    void storeSubscription(final SubscriptionEvent event) {
        if (!subscriptionFacade.isExist(event)) {
            final SubscriptionDto subscriptionDto = subscriptionFacade.saveSubscription(event);
            mailSender.sendMail(createMailMessage(subscriptionDto));
        }
    }

    private MailMessage createMailMessage(final SubscriptionDto subscriptionDto) {
        return  new MailMessage(EMAIL_FROM, subscriptionDto.getEmail(),
                subscriptionDto.getFirstName() +" "+ subscriptionDto.getLastName() );
    }

}
