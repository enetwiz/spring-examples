package com.enetwiz.initdestroymethods;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * Przyklad wplywu na cykl zycia komponentu za pomoca anotacji @PostConstruct i @PreDestroy
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
public class InitDestroyMethods {
    
    public static void main(String[] args) {
        
        // Loading the application context from annotation (using config file like AppConfig())
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext( AppConfig.class );
        
        // Create real singelton class instance
        ExampleComponent component = (ExampleComponent) context.getBean("exampleComponent");
        context.close();
        
    }
    
}