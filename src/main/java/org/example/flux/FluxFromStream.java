package org.example.flux;

import org.example.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class FluxFromStream {

    public static void main(String[] args) {

        var list = List.of(1,2,3,4);
        var stream = list.stream();

//        stream.forEach(System.out::println);
//        stream.forEach(System.out::println);


        // Next line will give stream has already been operated upon or closed error because stream can be consumed only once
        //var flux = Flux.fromStream(stream);

        var flux = Flux.fromStream(() -> list.stream());

        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));




    }

}
