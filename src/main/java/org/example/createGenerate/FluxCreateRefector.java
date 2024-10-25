package org.example.createGenerate;

import org.example.common.Util;
import org.example.createGenerate.helper.NameGenerator;
import reactor.core.publisher.Flux;

public class FluxCreateRefector {

    public static void main(String[] args) {

        var generator = new NameGenerator();
        Flux flux = Flux.create(generator);
        flux.subscribe(Util.subscriber());

        // somewhere else!
        for (int i = 0; i < 10; i++) {
            generator.generate();
        }

    }
}
