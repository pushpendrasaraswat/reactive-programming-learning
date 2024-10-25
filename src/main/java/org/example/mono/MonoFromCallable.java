package org.example.mono;

import org.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class MonoFromCallable {

    private static final Logger log = LoggerFactory.getLogger(MonoFromCallable.class);

    public static void main(String[] args) {
        /**
         * Their difference in usage can be seen from their respective documentation:
         *
         * Callable:
         *public interface Callable<V> {
         *     V call() throws Exception;
         * }
         *
         *
         * A task that returns a result and may throw an exception. Implementors define a single method with no arguments called call.
         *
         * The Callable interface is similar to Runnable, in that both are designed for classes whose instances are potentially executed by another thread.
         *
         * Supplier:
         *
         *  public interface Supplier<T> {
         *               T get();
         *           }
         * Represents a supplier of results.
         *
         * There is no requirement that a new or distinct result be returned each time the supplier is invoked.
         */
        List<Integer> list = List.of(1, 2, 3);
        // both supplier and callback looks same difference is callBack has checked exception but fromSupplier doesn´t have any exception
        Mono.fromCallable(() -> sum(list))
                .subscribe(Util.subscriber());

    }

    private static int sum(List<Integer> list) throws Exception {
        log.info("finding the sum of {}", list);
        return list.stream().mapToInt(a -> a).sum();
    }
}
