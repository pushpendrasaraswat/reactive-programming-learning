package org.example.operators;

import org.example.common.Util;
import reactor.core.publisher.Flux;

public class Filter {

    public static void main(String[] args) {

        Flux.range(1, 10).log()
                .filter(i -> i > 5)
                .map(i -> i * 2)
                .take(2)
                //.takeWhile(i -> i >= 12 && i < 18)
                .takeUntil(i -> i ==16)

                .subscribe(Util.subscriber());



    }
}
