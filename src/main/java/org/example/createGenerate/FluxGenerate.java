package org.example.createGenerate;

import org.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class FluxGenerate {

    private static final Logger log = LoggerFactory.getLogger(FluxGenerate.class);

    public static void main(String[] args) {



    /*
    Flux generate
    - invokes the given lambda expression again and again based on downstream demand.
    - We can emit only one value at a time
    - will stop when complete method is invoked
    - will stop when error method is invoked
    - will stop downstream cancels
     */

        // Flux genereate reactor controls the loop but in create we control the loop,
        // take operator is used to limit the number of elements otherwise its infinite or oncomplete or error emits
        Flux.generate(synchronousSink -> {
                    log.info("invoked");
                    synchronousSink.next(1);
                    //synchronousSink.next(2);
                    //synchronousSink.complete();
                    //.error(new RuntimeException("oops"));
                }).log()
                .take(4)
                .subscribe(Util.subscriber());


//        Flux.generate(synchronousSink -> {
//                    log.info("invoked");
//                    synchronousSink.next(1);
//                    synchronousSink.next(2);
//                    synchronousSink.complete();
//
//                })
//                .subscribe(Util.subscriber());


    }
}
