package com.chulm.reactive.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class SimpleTest {

    @Test
    public void printTest(){
        Observable.just(1, 2, 3, 4, 5).subscribe(System.out::println);
    }

    @Test
    public void createTest(){
        Observable<Integer> source = Observable.create(
                (ObservableEmitter<Integer> emitter) -> {
                    emitter.onNext(100);
                    emitter.onNext(200);
                    emitter.onNext(300);
                    emitter.onComplete();
                }
        );

        source.subscribe(System.out::println);
    }

    @Test
    public void iteratorbleTest(){
        List<String> names = new ArrayList<>();
        names.add("Jerry");
        names.add("William");
        names.add("Bob");

        Observable<String> source = Observable.fromIterable(names);
        source.subscribe(System.out::println);
    }

    @Test
    public void fromCollableTest(){
        Callable<String> callable = () -> {
            Thread.sleep(1000);
            return "Hello World";
        };

        Observable<String> source = Observable.fromCallable(callable);
        source.subscribe(System.out::println);
    }

    @Test
    public void fromFutureTest(){

        Future<String> future = Executors.newSingleThreadExecutor().submit(() -> {
            Thread.sleep(1000);
            return "Hello World";
        });

        Observable<String> source = Observable.fromFuture(future);
        source.subscribe(System.out::println);
    }
}
