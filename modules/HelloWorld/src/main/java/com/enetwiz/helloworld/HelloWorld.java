package com.enetwiz.helloworld;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
public class HelloWorld {
    
    public static void main(String[] args) {
        // Loading the application context from annotation (using config file like AppConfig())
        ApplicationContext context = new AnnotationConfigApplicationContext( AppConfig.class );
        
        // Get bean from context and say hello
        Hello hello = (Hello) context.getBean("hello");
        hello.say();
    }
    
}