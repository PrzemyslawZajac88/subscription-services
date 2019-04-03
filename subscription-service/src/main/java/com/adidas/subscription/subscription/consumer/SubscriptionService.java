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

    private final SubscriptionFacade subscriptionFacade;
    private final MailSender mailSender;

    void storeSubscription(final SubscriptionEvent event) {
        final boolean isSubscriptionExist = subscriptionFacade.isExist(event);

        if (!isSubscriptionExist) {
            final SubscriptionDto subscription = subscriptionFacade.saveSubscription(event);
            mailSender.sendMail(new MailMessage(subscription.getEmail(), body(subscription)));
        }
    }

    private String body(final SubscriptionDto subscription) {
        return "Hello "
                + "FirstName: " + subscription.getFirstName()
                + "" + subscription.getLastName();
    }
}
