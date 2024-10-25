package org.example.mono;

import org.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Just {

    private static final Logger log = LoggerFactory.getLogger(Just.class);

    public static void main(String[] args) {
        // we use Just when value is already in the memory and ready to use
        Mono mono = Mono.just("example");
        mono.log().subscribe(Util.subscriber());

    }

}
