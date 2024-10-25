package org.example.operators;


import org.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Timeout {

 //   Propagate a TimeoutException as soon as no item is emitted
    //   within the given Duration from the previous emission (or the subscription for the first item).

    private static final Logger log = LoggerFactory.getLogger(Timeout.class);

    public static void main(String[] args){
    getProductName()
            .timeout(Duration.ofSeconds(1))
               // .timeout(Duration.ofSeconds(1), fallback())
            .subscribe(Util.subscriber());

        Util.sleepSeconds(5);

    }

    private static Mono<String> getProductName() {
        return Mono.fromSupplier(() -> "service-" + Util.faker().commerce().productName())
                .delayElement(Duration.ofMillis(1900));
    }

    private static Mono<String> fallback() {
        return Mono.fromSupplier(() -> "fallback -" + Util.faker().commerce().productName())
                .delayElement(Duration.ofMillis(300))
                .doFirst(() -> log.info("do first"));
    }

}
