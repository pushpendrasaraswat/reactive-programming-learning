package org.example.publisher;

import org.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class HotPublisher {

    private static final Logger log = LoggerFactory.getLogger(HotPublisher.class);

    public static void main(String[] args) {

        var movieFlux = movieStream().share();
        /*

    - does NOT stop when subscribers cancel. So it will start producing even for 0 subscribers once it started.
    - make it real hot - publish().autoConnect(0)

        var movieFlux = movieStream().publish().autoConnect(0);

         */

        /*
        Hot - 1 data producer for all the subscribers.
        share => publish().refCount(1)
        It needs 1 min subscriber to emit data.
        It stops when there is 0 subscriber.
        Re-subscription - It starts again where there is a new subscriber.
        To have min 2 subscribers, use publish().refCount(2);
        var movieFlux = movieStream().publish().refCount(1);
        */

        /*
        - publish().autoConnect(0) will provide new values to the subscribers.
        - replay allows us to cache
        replay takes input as number of items to cache. no argument means all items cached.
**/
        var stockFlux = stockStream().replay().autoConnect(0);

        Util.sleepSeconds(4);

        log.info("sam joining");
        stockFlux
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(10);

        log.info("mike joining");
        stockFlux
                .subscribe(Util.subscriber("mike"));

        Util.sleepSeconds(25);

        Util.sleepSeconds(2);
 /**
        movieFlux
                .take(4)
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(3);

        movieFlux
                .take(3)
                .subscribe(Util.subscriber("mike"));


        Util.sleepSeconds(15);
**/
    }

    // movie theater
    private static Flux<String> movieStream() {
        return Flux.generate(
                        () -> {
                            log.info("received the request");
                            return 1;
                        },
                        (state, sink) -> {
                            var scene = "movie scene " + state;
                            log.info("playing {}", scene);
                            sink.next(scene);
                            return ++state;
                        }
                )
                .take(10)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }


    private static Flux<Integer> stockStream() {
        return Flux.generate(sink -> sink.next(Util.faker().random().nextInt(10, 100)))
                .delayElements(Duration.ofSeconds(3))
                .doOnNext(price -> log.info("emitting price: {}", price))
                .cast(Integer.class);
    }
}
