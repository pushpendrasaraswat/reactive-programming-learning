package org.example.operators;

import org.example.common.Util;
import reactor.core.publisher.Flux;

public class Handle {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .handle((item, sink) -> {
                    switch (item){
                        case 1 -> sink.next(-2);
                        case 4 -> {}
                        case 7 -> sink.error(new RuntimeException("oops"));
                        default -> sink.next(item);
                    }
                })
                .cast(Integer.class)
                .subscribe(Util.subscriber());
    }
}
