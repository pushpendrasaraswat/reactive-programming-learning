package org.example.operators;

import org.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class DefaultIfEmpty {
    private static final Logger log = LoggerFactory.getLogger(DefaultIfEmpty.class);


    public static void main(String[] args) {
        // When We need to set hardcode fallback value
        Mono.empty().defaultIfEmpty(50).subscribe(Util.subscriber());
        log.info("-------------------------------------------------");
        Flux.range(1,10).filter(i -> i> 10).defaultIfEmpty(51).subscribe(Util.subscriber());
    }
}
