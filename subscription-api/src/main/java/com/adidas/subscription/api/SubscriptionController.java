package com.adidas.subscription.api;

import com.adidas.subscription.producer.SubscriptionProducer;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RequestMapping(value = "/subscriptions")
@RestController
@AllArgsConstructor
class SubscriptionController {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

    private final SubscriptionProducer producer;

    @PostMapping
    public ResponseEntity<SubscriptionResponse> subscribe(@RequestBody @Valid final SubscriptionForm subscription, final Errors errors) {
        final String requestId = generateRequestId();
        logger.info("Request: {}, {}", requestId, subscription);

        if (errors.hasErrors()) {
            return badRequest()
                    .body(new SubscriptionResponse(requestId, toErrors(errors)));
        }
        producer.sendSubscription(subscription.toPayload());
        return ok(new SubscriptionResponse(requestId));
    }

    private String generateRequestId() {
        return UUID.randomUUID().toString();
    }

    private Map<String, String> toErrors(final Errors errors) {
        return errors.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
    }

}
