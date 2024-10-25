package org.example.flux;

import org.example.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class FluxInterval {

    // generate a sequence of numbers after a particular intrval or process some data or emit messages after a particular interval

    public static void main(String[] args) {

        Flux.interval(Duration.ofMillis(500))
                .map(i -> Util.faker().name().firstName())
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);

    }
}
