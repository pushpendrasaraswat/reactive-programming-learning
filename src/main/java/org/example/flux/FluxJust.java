package org.example.flux;

import org.example.common.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class FluxJust {

    public static void main(String[] args) {

        Flux.just(1, 2, 3, "sam").log()
                .subscribe(Util.subscriber());
    }
}
