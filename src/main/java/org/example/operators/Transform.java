package org.example.operators;

import org.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Transform {

    private static final Logger log = LoggerFactory.getLogger(Transform.class);

    record Customer(int id, String name){}
    record PurchaseOrder(String productName, int price, int quantity){}

    public static void main(String[] args) {


        log.info("----------------------1---------------------------");
        getCustomers().doOnNext(i -> log.info("received: {}", i))
                .doOnComplete(() -> log.info("completed"))
                .doOnError(err -> log.error("error", err)).subscribe(Util.subscriber());
        log.info("----------------------2---------------------------");
        getPurchaseOrders().doOnNext(i -> log.info("received: {}", i))
                .doOnComplete(() -> log.info("completed"))
                .doOnError(err -> log.error("error", err)).subscribe(Util.subscriber());
        log.info("-----------------------3--------------------------");
        getCustomers()
                .transform(addDebugger())
                .subscribe();
        log.info("-----------------------4--------------------------");
        getPurchaseOrders()
                .transform(addDebugger())
                .subscribe();

    }

    private static Flux<Customer> getCustomers(){
        return Flux.range(1, 3)
                .map(i -> new Customer(i, Util.faker().name().firstName()));
    }

    private static Flux<PurchaseOrder> getPurchaseOrders(){
        return Flux.range(1, 5)
                .map(i -> new PurchaseOrder(Util.faker().commerce().productName(), i, i * 10));
    }

    private static <T> UnaryOperator<Flux<T>> addDebugger(){
        // Repeating STEPS COULD BE PUT TOGETHER
        return flux -> flux
                .doOnNext(i -> log.info("received: {}", i))
                .doOnComplete(() -> log.info("completed"))
                .doOnError(err -> log.error("error", err));
    }
}
