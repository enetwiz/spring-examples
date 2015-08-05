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
        HelloBean hello = (HelloBean) context.getBean("helloBean");
        hello.say();
        
        // Get bean from context and say hello
        HelloComponent helloComponent = (HelloComponent) context.getBean("helloComponent");
        helloComponent.say();
    }
    
}