package com.chulman.core.aop.recipe0213;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RunApplication {
    public static void main(String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(CalculatorConfiguration.class);

        ArithmeticCalculator arithmeticCalculator = context.getBean("arithmeticCalculator", ArithmeticCalculator.class);

        arithmeticCalculator.add(2, 3);
        arithmeticCalculator.minus(3,2);
        arithmeticCalculator.div(2,4);
        arithmeticCalculator.mul(2,4);
//        arithmeticCalculator.div(2,0);

        UnitCalculator unitCalculator = context.getBean("unitCalculator", UnitCalculator.class);
        unitCalculator.kilogramToPound(2.3);
        unitCalculator.kilometerToMile(3.5);
    }
}
