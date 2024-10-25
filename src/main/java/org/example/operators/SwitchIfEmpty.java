package org.example.operators;

import org.example.common.Util;
import reactor.core.publisher.Flux;

public class SwitchIfEmpty {

    public static void main(String[] args) {

        /*
        usecase :
            Database and caches example , if data is cache return ot else fallback to database
         */

        Flux.range(1, 10)
                .filter(i -> i > 10)
                .switchIfEmpty(fallback())
                .subscribe(Util.subscriber());


    }

    private static Flux<Integer> fallback(){
        return Flux.range(100, 3);
    }
}
