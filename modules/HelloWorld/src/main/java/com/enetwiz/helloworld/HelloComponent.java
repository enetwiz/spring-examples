package com.enetwiz.helloworld;

import org.springframework.stereotype.Component;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Component
public class HelloComponent {
    
    public void say() {
        System.out.println("Hello world from Component!");
    }
    
}