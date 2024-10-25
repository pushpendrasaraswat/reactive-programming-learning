package org.example.backpressure;

import org.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
public class LimitRateBackPressure {

    private static final Logger log = LoggerFactory.getLogger(LimitRateBackPressure.class);

    public static void main(String[] args) {

        var producer = Flux.generate(
                        () -> 1,
                        (state, sink) -> {
                            log.info("generating {}", state);
                            sink.next(state);
                            return ++state;
                        }
                )
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer
                // telling producer i am a slow consumer so produce 5 items at a time
                // in the output we can see as soon as 75 % items consumed start producing again
                .limitRate(5)
                .publishOn(Schedulers.boundedElastic())
                .map(LimitRateBackPressure::timeConsumingTask)
                .subscribe(Util.subscriber());


        Util.sleepSeconds(60);

    }

    private static int timeConsumingTask(int i) {
        Util.sleepSeconds(1);
        return i;
    }
}
