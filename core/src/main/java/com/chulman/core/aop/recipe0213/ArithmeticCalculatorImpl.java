package com.chulman.core.aop.recipe0213;

import org.springframework.stereotype.Component;

@Component("arithmeticCalculator")
public class ArithmeticCalculatorImpl implements  ArithmeticCalculator{
    @Override
    public double add(double a, double b) {
        System.err.println("add");
        return a+b;
    }

    @Override
    public double minus(double a, double b) {
        System.err.println("minus");
        return a-b;
    }

    @Override
    public double mul(double a, double b) {
        System.err.println("mul");
        return a*b;
    }

    @Override
    public double div(double a, double b) {
        if(b==0) throw new IllegalArgumentException("Division by zero");
        System.err.println("div");
        return a/b;
    }
}
