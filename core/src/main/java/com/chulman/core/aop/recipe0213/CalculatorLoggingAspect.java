package com.chulman.core.aop.recipe0213;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class CalculatorLoggingAspect {

    /*
     *   Before, After, AfterReturning, AfterThrowing, Around 지원
     *   와일드카드(*)는 모든 수정자(public, protected, privated), 모든 반환형 의미
     *   ..는 인수의 개수가 배열임을 의미
     */
    @Before("execution(* ArithmeticCalculator.add(..))")
    public void logBefore(){
        log.info("The method add() before begins");
    }

    /*
     * 정상 실행되든, 예외가 발생하든 실행됨
     */
    @After("execution(* *.*(..))")
    public void logAfter(JoinPoint joinPoint){
        log.info("After joinPoint method: " + joinPoint.getSignature().getName() + "() ends");
    }

    @AfterReturning(pointcut = "execution(* *.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result){
        log.info("After {}() Returning : {}", joinPoint.getSignature().getName(), result);
    }

    @AfterThrowing(pointcut = "execution(* *.*(..))", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e){
        log.error("Throwing {}() : {}", joinPoint.getSignature().getName(), e);
    }

    /*
        Around는 가장 강력한 어드바이스
        위에서 사용했던 advice를 Around 어드바이스로 조합할 수 있다.
        심지어 원본 조인포인트를 언제 실행할지, 실행 여부, 계속 실행할지도 지정한다.

        매우 강력한 어드바이스라서 원본 값을 바꾸거나 최종 반환값을 변경하는 것도 가능하다.
        하지만 간혹 원본 조인포인트를 진행하는 호출을 잊어버리기 쉬우므로 사용시 주의 한다.


        !! 어드바이스를 사용할 땐 최소한의 요건을 충족하고 가장 약한 기능을 사용하는게 바람직하다.
     */
    @Around("execution(* *.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[logAround] the method{}() begin with {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));

        try {
            Object result = joinPoint.proceed();
            log.info("[logAround] the method{}() ends with {}", joinPoint.getSignature().getName(), result);

            return result;
        } catch (IllegalArgumentException e){
            throw  e;
        }
    }
}
