package com.epam.reactive.backpressure;

import reactor.core.publisher.BaseSubscriber;

import org.reactivestreams.Subscription;
import org.springframework.stereotype.Component;

import com.epam.reactive.domain.Sport;

@Component
public class CustomBackpressureSubscriber extends BaseSubscriber<Sport> {

    private static final int LIMIT = 20;

    @Override
    public void hookOnSubscribe(Subscription subscription) {
        request(LIMIT);
    }

    @Override
    public void hookOnNext(Sport sport) {
        request(LIMIT);
    }



}
