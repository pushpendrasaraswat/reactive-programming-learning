package org.example.subscribe;

import org.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
public class PublishOn {

    private static final Logger log = LoggerFactory.getLogger(PublishOn.class);

    public static void main(String[] args) {

        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("generating: {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .publishOn(Schedulers.parallel())
                .doOnNext(v -> log.info("value: {}", v))
                .doFirst(() -> log.info("first1"))
                // subscribeON for up stream publish on for downstream
                .subscribeOn(Schedulers.boundedElastic())
                //.publishOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("first2"));


        Runnable runnable1 = () -> flux.subscribe(Util.subscriber("sub1"));

        new Thread(runnable1).start();

        Util.sleepSeconds(2);


    }
}
