package org.example.mono;

import org.example.common.Util;
import reactor.core.publisher.Mono;

public class MonoEmptyError {

    public static void main(String[] args) {

        getUsername(3)
                .subscribe(Util.subscriber());

        //getUsername(1).subscribe(i -> System.out.println(i), err -> System.out.println(err), () -> System.out.println("completed"));

    }

    private static Mono<String> getUsername(int userId){
        return switch (userId){
            case 1 -> Mono.just("sam");
            case 2 -> Mono.empty(); // null
            default -> Mono.error(new RuntimeException("invalid input"));
        };
    }
}
