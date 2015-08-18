package com.enetwiz.aspectorientedprogramming;

import org.springframework.stereotype.Component;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Component
public class ExampleComponent {
    
    public void doSomething() {
        System.out.println("I do something inside the doSomething()!");
        System.out.println("---");
    }
    
    public void doMore() {
        System.out.println("I do more inside the doMore() and... nothing more happens because pointcut is not defined for this method!");
        System.out.println("---");
    }
    
    public String returnSomething() {
        System.out.println("For a moment to return example value using returnSomething()!");
        System.out.println("---");
        
        return "hello!";
    }
    
}