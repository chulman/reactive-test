package com.chulman.reactive.rxjava;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


interface Observer {
    public abstract void Update(NumberGenerator numberGenerator);
}

class DigitObserver implements Observer {
    @Override
    public void Update(NumberGenerator numberGenerator) {
        System.out.println(numberGenerator.getNumber());
    }
}

class NumberGenerator {

    private ArrayList observers = new ArrayList();
    private Random random = new Random();
    private int number;

    public void add(Observer observer) {
        observers.add(observer);
    }

    public void excute() {
        for (int i = 0; i < 20; i++) {
            number = random.nextInt(50);
            notifyObservers();
        }
    }

    public void notifyObservers() {
        Iterator iter = observers.iterator();
        while (iter.hasNext()) {
            Observer observer = (Observer) iter.next();
            observer.Update(this);
        }
    }

    public int getNumber() {
        return number;
    }
}


public class ObserverPatternTest {

    @Test
    public void randomNumberObseervingTest() {
        NumberGenerator generator = new NumberGenerator();

        Observer observer = new DigitObserver();
        generator.add(observer);
        generator.excute();
    }
}
