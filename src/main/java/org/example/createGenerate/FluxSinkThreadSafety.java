package org.example.createGenerate;

import org.example.common.Util;
import org.example.createGenerate.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

public class FluxSinkThreadSafety {

    private static final Logger log = LoggerFactory.getLogger(FluxSinkThreadSafety.class);

    public static void main(String[] args) {

        demo2();

    }

    // arraylist is not thread safe
    private static void demo1(){
        var list = new ArrayList<Integer>();
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
        Util.sleepSeconds(3);
        // size of the list should be 10000 but it will be not because Array list is not thread safe
        log.info("list size: {}", list.size());
    }


    // arraylist is not thread safe.
    // flux sink is thread safe. we get all the 10000 items safely into array list
    private static void demo2(){
        var list = new ArrayList<String>();
        var generator = new NameGenerator();
        var flux = Flux.create(generator);

        //flux.subscribe(name -> list.add(name));

        flux.subscribe(list::add);

        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                generator.generate();
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
        Util.sleepSeconds(3);
        
        // size of the list should be 10000 becuase flux is thread safe
        log.info("list size: {}", list.size());
    }

}
