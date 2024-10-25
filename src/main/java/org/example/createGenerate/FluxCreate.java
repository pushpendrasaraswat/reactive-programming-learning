package org.example.createGenerate;

import org.example.common.Util;
import reactor.core.publisher.Flux;

public class FluxCreate {

    public static void main(String[] args) {

        //    To create a flux & emit items programmatically
        // using flux create we can control the flow of items
        Flux.create(fluxSink -> {
                    String country;
                    do {
                        country = Util.faker().country().name();
                        fluxSink.next(country);
                    } while (!country.equalsIgnoreCase("canada"));
                    fluxSink.complete();
                }).log()
                .subscribe(Util.subscriber());
    }
}
