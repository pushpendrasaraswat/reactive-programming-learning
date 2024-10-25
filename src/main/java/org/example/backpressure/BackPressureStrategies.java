package org.example.backpressure;

import org.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class BackPressureStrategies {

    private static final Logger log = LoggerFactory.getLogger(BackPressureStrategies.class);

    public static void main(String[] args) {

        var producer = Flux.create(sink -> {
                    for (int i = 1; i <= 500 && !sink.isCancelled(); i++) {
                        log.info("generating {}", i);
                        sink.next(i);
                        Util.sleep();
                    }
                    sink.complete();
                })
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer
                /** once queue is full keep in memory **/
                // .onBackpressureBuffer()
                /** once queue is full Throw error  producer is producing more than the consumer is expecting**/
               // .onBackpressureError()
                /** once queue is full keep in memory 10 items and if more items comes throw error **/
                // .onBackpressureBuffer(10)
                /** once queue is full new item is dropped **/
                 //.onBackpressureDrop()
                /** once queue is full keep 1 latest item as and when it arrives and drop old **/
                //.onBackpressureLatest()
                .log()
                .limitRate(1)
                .publishOn(Schedulers.boundedElastic())
                .map(BackPressureStrategies::timeConsumingTask)
                .subscribe();



        Util.sleepSeconds(60);


    }

    private static int timeConsumingTask(int i) {
        log.info("received: {}", i);
        Util.sleepSeconds(1);
        return i;
    }


}
