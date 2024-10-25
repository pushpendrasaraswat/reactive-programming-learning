package org.example.mono;

import org.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class MonoFromSupplier {

    private static final Logger log = LoggerFactory.getLogger(MonoFromSupplier.class);

    public static void main(String[] args) {

        List<Integer> list = List.of(1, 2, 3);

        // If we use- > this code will execute the code eventhough there is no Subscriber and it will print the log
        // some time data provoding code might take some time to produce the data so better wat is fromSupplier

        // we use Just when value is already in the memory and ready to use supplier we use when we want to execute the code only when there is a subscriber
        Mono.just(sum(list));


        Mono.fromSupplier(() -> sum(list))
                .subscribe(Util.subscriber());

    }

    private static int sum(List<Integer> list) {
        log.info("finding the sum of {}", list);
        return list.stream().mapToInt(a -> a).sum();
    }

}
