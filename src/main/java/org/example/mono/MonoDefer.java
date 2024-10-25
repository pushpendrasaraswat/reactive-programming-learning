package org.example.mono;

import org.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class MonoDefer {

    private static final Logger log = LoggerFactory.getLogger(MonoDefer.class);

    public static void main(String[] args) {

        // The defer operator is there to make this source lazy,
        // re-evaluating the content of the lambda each time there is a new subscriber:
        Mono.defer(MonoDefer::createPublisher)
                .subscribe(Util.subscriber());

    }

    // time-consuming publisher creation
    private static Mono<Integer> createPublisher(){
        log.info("creating publisher");
        var list = List.of(1, 2, 3);
        Util.sleepSeconds(1);
        return Mono.fromSupplier(() -> sum(list));
    }

    // time-consuming business logic
    private static int sum(List<Integer> list) {
        log.info("finding the sum of {}", list);
        Util.sleepSeconds(3);
        return list.stream().mapToInt(a -> a).sum();
    }
}
