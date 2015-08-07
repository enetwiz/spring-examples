package com.enetwiz.aspectorientedprogramming;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Przyklad programowania aspektowego z uzyciem anotacji
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
public class AspectOrientedProgramming {
    
    public static void main(String[] args) {
        
        // Loading the application context from annotation (using config file like AppConfig())
        ApplicationContext context = new AnnotationConfigApplicationContext( AppConfig.class );
        
        ExampleComponent component = (ExampleComponent) context.getBean("exampleComponent");
        component.doSomething();
        component.doMore();
        component.returnSomething();
    }
    
}