package org.example.createGenerate;

import org.example.common.Util;
import reactor.core.publisher.Flux;

public class FluxGenerateWithState {


    // generate only 10 country name or name is canada
    public static void main(String[] args) {
 

        Flux.generate(
                        () -> 0,
                        (counter, sink) -> {
                            var country = Util.faker().country().name();
                            sink.next(country);
                            counter++;
                            if (counter == 10 || country.equalsIgnoreCase("canada")) {
                                sink.complete();
                            }
                            return counter;
                        }
                )
                .subscribe(Util.subscriber());
    }
}
