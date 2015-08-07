package com.enetwiz.aspectorientedprogramming;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Aspect
public class LoggingAspect {
    
    @Before("execution(* ExampleComponent.doSomething(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("logBefore() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("---");
    }
    
    @After("execution(* ExampleComponent.doSomething(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("logAfter() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("---");

    }
    
    @AfterReturning(pointcut = "execution(* ExampleComponent.returnSomething(..))", returning= "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("logAfterReturning() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("Method returned value is : " + result);
        System.out.println("---");
    }
    
}