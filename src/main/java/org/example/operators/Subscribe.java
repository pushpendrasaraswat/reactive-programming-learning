package org.example.operators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Subscribe {
    private static final Logger log = LoggerFactory.getLogger(Subscribe.class);

    public static void main(String[] args) {

        Flux.range(1, 10)
                .doOnNext(i -> log.info("received: {}", i))
                .doOnComplete(() -> log.info("completed"))
                .doOnError(err -> log.error("error", err))
                // Subscribe doesn't have all the handlers , doOnNext , doOnCOmplete, doOnError , doing the same thing as above
                .subscribe();


    }
}
