package org.example.operators;

import org.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class DoOperators {

    private static final Logger log = LoggerFactory.getLogger(DoOperators.class);

    public static void main(String[] args) {
            // to show complete
                //Flux.just()
        // to show error
            //Flux.just(1,2).map(i -> i / 0)
        //Working
        Flux.range(1,10)
                .filter(i -> i > 10)
                .flatMap(i -> {
                    log.info("flatMap-1");
                    return Flux.range(i,1);
                }
                )
                // when  Flux completes successfully without error
                .doOnComplete(() -> log.info("doOnComplete-1"))
                .doFirst(() -> log.info("doFirst-1"))
                // When value is emitted from the source   whenever value emitted
                .doOnNext(item -> log.info("doOnNext-1: {}", item))
                .doOnSubscribe(subscription -> log.info("doOnSubscribe-1: {}", subscription))
                // when subscriber request for items , number in the log is maximum value give me everything
                .doOnRequest(request -> log.info("doOnRequest-1: {}", request))
                // when error is emitted
                .doOnError(error -> log.info("doOnError-1: {}", error.getMessage()))
                // when complete or error is emitted
                .doOnTerminate(() -> log.info("doOnTerminate-1")) // complete or error case
                // when subscription is cancelled
                .doOnCancel(() -> log.info("doOnCancel-1"))
                // when item is discarded and gives you object which is discarded
                .doOnDiscard(Object.class, o -> log.info("doOnDiscard-1: {}", o))
                // finally irrespective of the reason
                .doFinally(signal -> log.info("doFinally-1: {}", signal))
//                .take(2)
//                .doOnComplete(() -> log.info("doOnComplete-2"))
//                .doFirst(() -> log.info("doFirst-2"))
//                .doOnNext(item -> log.info("doOnNext-2: {}", item))
//                .doOnSubscribe(subscription -> log.info("doOnSubscribe-2: {}", subscription))
//                .doOnRequest(request -> log.info("doOnRequest-2: {}", request))
//                .doOnError(error -> log.info("doOnError-2: {}", error.getMessage()))
//                .doOnTerminate(() -> log.info("doOnTerminate-2")) // complete or error case
//                .doOnCancel(() -> log.info("doOnCancel-2"))
//                .doOnDiscard(Object.class, o -> log.info("doOnDiscard-2: {}", o))
//                .doFinally(signal -> log.info("doFinally-2: {}", signal)) // finally irrespective of the reason
//                // to show DoOnRequest enable next line
//                .take(4)
                .subscribe(Util.subscriber("subscriber"));
                // doFIrst execution starts near the subscriber
                // subscription object goes from Publisher to subscriber

    }
}
