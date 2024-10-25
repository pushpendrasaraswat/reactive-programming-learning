package org.example.mono;

import org.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class MonoFromRunnable {

    private static final Logger log = LoggerFactory.getLogger(MonoFromRunnable.class);

    public static void main(String[] args) {

        getProductName(2)
                .subscribe(Util.subscriber());

    }
    // fromRunnable — Create a Mono that completes empty once the provided Runnable has been executed.
    private static Mono<String> getProductName(int productId){
        if(productId == 1){
            return Mono.fromSupplier(() -> Util.faker().commerce().productName());
        }
        // if product is not available, notify business and returning Empty Mono
        return Mono.fromRunnable(() -> notifyBusiness(productId));
    }

    private static void notifyBusiness(int productId){
        log.info("notifying business on unavailable product {}", productId);
    }

}
