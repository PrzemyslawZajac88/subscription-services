package com.adidas.subscription.api;

import com.adidas.subscription.producer.SubscriptionProducer;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ObjectStreamClass;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RequestMapping(value = "/subscriptions")
@RestController
@AllArgsConstructor
class SubscriptionController {

    private final SubscriptionProducer producer;

    @PostMapping
    public ResponseEntity<SubscriptionResponse> subscribe(@RequestBody @Valid final SubscriptionForm subscription, final Errors errors) {
        Objects.requireNonNull(subscription.getDateOfBirth());
        if (errors.hasErrors()) {
            return badRequest()
                    .body(new SubscriptionResponse(toErrors(errors)));
        }
        producer.sendSubscription(subscription.toPayload());
        return ok(new SubscriptionResponse());
    }

    private Map<String, String> toErrors(final Errors errors) {
        return errors.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
    }

}
