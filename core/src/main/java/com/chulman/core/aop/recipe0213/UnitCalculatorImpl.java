package com.chulman.core.aop.recipe0213;

import org.springframework.stereotype.Component;

@Component("unitCalculator")
public class UnitCalculatorImpl implements UnitCalculator{
    @Override
    public double kilogramToPound(double kilometer) {
        return kilometer * 2.2;
    }

    @Override
    public double kilometerToMile(double kilometer) {
        return kilometer * 0.62;
    }
}
