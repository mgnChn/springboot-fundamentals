package com.megandemo.reactor_core;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StreamController {

    //    Create Mono and FLux streams in simple tests using sinks or any other means. Transform the streamed items
    List<Integer> items = new ArrayList<>();

    public void processMono() {

        Mono<Integer> mono_just = Mono.just(1);

        mono_just
                .map(item -> item + 1)
                .subscribe(item -> {
                    item *=2;
                    items.add(item);
                });

        System.out.println(items);
    }

    public void processFlux(){
        Flux<Integer> flux_just = Flux.just(1, 2, 3, 4);

        flux_just
                .map(item -> item + 1)
                .subscribe(item -> {
                    item *=2;
                    items.add(item);
                });

        System.out.println(items);
    }


    // Receive the stream from springboot-rest-demo
    @Autowired
    private WebClient.Builder webClientBuilder;
    private static final Logger logger = LoggerFactory.getLogger(StreamController.class);

    @RequestMapping("/streamed-data")
    public void receiveStream(){

        // Create a reactive stream of integers
        Flux<Long> currentTime = webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/topics/stream")
                .retrieve()
                .bodyToFlux(Long.class);
//                .doOnNext(item -> System.out.println("Received item: " + item))
//                .doOnError(error -> System.out.println("Error: " + error.getMessage()));
        // Subscribing to the stream will allow values to be emitted
        // print out the values as they are received

        currentTime.subscribe(item -> System.out.println("Received item: " + item));

    }



    @RequestMapping("/test")
    public void test() {
        List<Object> topics = webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/topics")
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();

        System.out.println(topics);
    }


//    public static void main(String[] args) {
//        StreamController streams = new StreamController();
////        streams.processMono();
//        streams.processFlux();
//
//    }
//    Create a collection of Topics and convert it to a stream of Mono<Topics>. Transform each topic in some way perhaps suffixing a string to the topic name like " transformed". Send this out as a regular collection through an endpoint.
//    Create a new springboot application using spring-Webflux starter. Stream the topic out of another endpoint without making blocking calls.

}
