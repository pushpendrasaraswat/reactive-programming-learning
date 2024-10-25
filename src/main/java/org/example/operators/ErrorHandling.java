package org.example.operators;

import org.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ErrorHandling {
    private static final Logger log = LoggerFactory.getLogger(ErrorHandling.class);

    public static void main(String[] args) {

        //onErrorContinue();
        // onErrorComplete();
         //onErrorReturn();
        // onErrorResume();
    }


    // log the error and continue
    private static void onErrorContinue(){
        Flux.range(1, 10)
                .map(i -> i == 5 ? 5 / 0 : i) // intentional
                .onErrorContinue((ex, obj) -> log.error("==> {}", obj, ex))
                .subscribe(Util.subscriber());
    }

    // in case of error, emit complete
    private static void onErrorComplete() {
        Mono.error(new ArrayIndexOutOfBoundsException("oops"))
       // Flux.range(1,10).map(i -> i == 5 ? i/0 :i)
        //Mono.just(1)
                .onErrorComplete()
                .subscribe(Util.subscriber());
    }

    // when you want to return a hardcoded value / simple computation , onErrorReturn Hardcoded value
    private static void onErrorReturn() {
        //Mono.error(new IllegalArgumentException("oops")
        //Mono.error(new ArithmeticException("oops")
       // Flux.range(1,10)
        //Mono.just(5)
        Mono.error(new ArithmeticException("oops"))
               // .map(i -> i == 5 ? 5 / 0 : i) // intentional
                .onErrorReturn(IllegalArgumentException.class, -1)
                .onErrorReturn(ArithmeticException.class, -2)
                .onErrorReturn(-3)
                .subscribe(Util.subscriber());
    }

    // when you have to use another publisher in case of error - fallback value with computation
    private static void onErrorResume() {
        //Mono.error(new ArithmeticException("oops"))
        Flux.range(1,10)
                        .map(i -> i == 5 ? i / 0 : i) // intentional
       // Mono.error(new RuntimeException("oops"))
                .onErrorResume(ArithmeticException.class, ex -> fallback1())
                .onErrorResume(ex -> fallback2())
                .onErrorReturn(-5)
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> fallback1() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(10, 100));
    }

    private static Mono<Integer> fallback2() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(100, 1000));
    }
}
