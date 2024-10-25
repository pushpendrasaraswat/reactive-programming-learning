package org.example.flux;

import org.example.common.Util;
import reactor.core.publisher.Flux;

public class FluxFromRange {

    //     To create a range of items based on the given start and count

    public static void main(String[] args) {

        Flux.range(5, 10)
                .subscribe(Util.subscriber());

        // assignment - generate 10 random first names
        Flux.range(1, 10)
                .map(i -> Util.faker().name().firstName())
                .subscribe(Util.subscriber());

    }

}
