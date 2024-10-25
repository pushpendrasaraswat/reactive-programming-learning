package org.example.operators;

import org.example.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class DelayOperator {


    public static void main(String[] args) {

        // comment Delay code to show that FLux is emitting one itea at a time
        Flux.range(1, 10)
                .log()
                //Delay each of this Flux elements (Subscriber.onNext(T) signals) by a given Duration.
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Util.subscriber());

        //Sleep main thread so that we can see result
        Util.sleepSeconds(12);


    }
}
