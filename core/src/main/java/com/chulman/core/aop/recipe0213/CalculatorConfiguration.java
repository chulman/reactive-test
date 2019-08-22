package com.chulman.core.aop.recipe0213;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
//Aspect Scanning
@EnableAspectJAutoProxy
@ComponentScan
public class CalculatorConfiguration {
}
