package com.enetwiz.singeltonfactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * Przyklad tworzenia obiektow singeltonowych poprzez metody fabryki komponentow
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
public class SingletonFactory {
    
    public static void main(String[] args) {
        
        // Loading the application context from annotation (using config file like AppConfig())
        ApplicationContext context = new AnnotationConfigApplicationContext( AppConfig.class );
        
        // Create real singelton class instance
        ExampleSingleton singelton = (ExampleSingleton) context.getBean("exampleSingelton");
        System.out.println( "Object label: " + singelton.getLabel() );
        
    }
    
}