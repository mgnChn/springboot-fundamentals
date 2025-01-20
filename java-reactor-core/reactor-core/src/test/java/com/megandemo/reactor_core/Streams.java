package com.megandemo.reactor_core;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;


public class Streams
{
    Mono<Integer> just2 = Mono.just(1);

    List<Object> elements = new ArrayList<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(Streams.class);

    public void subscribeAllElements(){
        Flux<Integer> flux = Flux.just(1, 2, 3, 4);

        flux.log().subscribe(elements::add);

        StepVerifier.create(flux)
                .expectNext(1, 2, 3, 4)
                .verifyComplete();
    }

    public void subscribeTwoElements(){
        Flux.just(1, 2, 3, 4)
                .log()
                .subscribe(new Subscriber<Integer>() {
                    private Subscription s;
                    int onNextAmount;

                    @Override
                    public void onSubscribe(Subscription s) {
                        this.s = s;
                        s.request(2);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        elements.add(integer);
                        onNextAmount++;
                        if (onNextAmount % 2 == 0) {
                            s.request(2);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void doubleElements(){

        Flux.just(1, 2, 3, 4)
                .log()
                .map(i -> {
                    LOGGER.debug("{}:{}", i, Thread.currentThread());
                    return i * 2;
                })
                .subscribe(elements::add);

        System.out.println(elements);
    }

    public void combineStreams(){

        Flux<Integer> flux = Flux.just(1, 2, 3, 4);

        flux
            .log()
            .map(i -> i * 2) // This will double the values of the first flux
            .zipWith(Flux.range(0, Integer.MAX_VALUE),
                    (one, two) -> String.format("First Flux: %d, Second Flux: %d", one, two))
            .subscribe(elements::add);

        assertThat(elements).containsExactly(
                "First Flux: 2, Second Flux: 0",
                "First Flux: 4, Second Flux: 1",
                "First Flux: 6, Second Flux: 2",
                "First Flux: 8, Second Flux: 3");

        System.out.println(elements);
    }

    public void convertColdHotStream(){
        ConnectableFlux<Object> publish = Flux.create(fluxSink -> {
                    while(true) {
                        fluxSink.next(System.currentTimeMillis());
                    }
                })
                .publish();

        publish.subscribe(System.out::println);
        publish.subscribe(System.out::println);

        publish.connect();
    }

    public void throttleData() {

        ConnectableFlux<Object> publish = Flux.create(fluxSink -> {
                while(true) {
                    fluxSink.next(System.currentTimeMillis());
                }
            })
            .sample(ofSeconds(2))
            .publish();

        publish.subscribe(System.out::println);

        publish.connect();
    }

    public static void main(String[] args) {
        Streams streams = new Streams();
//        streams.subscribeAllElements();
//        streams.subscribeTwoElements();
//        streams.doubleElements();
//        streams.combineStreams();
//        streams.convertColdHotStream();
        streams.throttleData();
    }

}
