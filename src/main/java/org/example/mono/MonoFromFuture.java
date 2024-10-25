package org.example.mono;

import org.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class MonoFromFuture {

    private static final Logger log = LoggerFactory.getLogger(MonoFromFuture.class);

    public static void main(String[] args) {

        Mono.fromFuture(MonoFromFuture::getName)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(1);

    }
    //        Mono.fromFuture(getName())
    // completableFuture is not lazy that's why we need to use as method reference
    private static CompletableFuture<String> getName(){
        return CompletableFuture.supplyAsync(() -> {
            log.info("generating name");
            return Util.faker().name().firstName();
        });
    }

}
