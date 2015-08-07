package com.enetwiz.dependencyinjection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Przyklad uzycia wstrzykiwania zaleznosci (Dependency Injection) roznymi kanalami
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
public class DependencyInjection {
    
    public static void main(String[] args) {
        
        // Loading the application context from annotation (using config file like AppConfig())
        ApplicationContext context = new AnnotationConfigApplicationContext( AppConfig.class );
        
        // Inject POJO object using object property field
        PropertyComponent pc = (PropertyComponent) context.getBean("propertyComponent");
        pc.doSomething();
        
        // Inject POJO object using component constructor
        ConstructorComponent cc = (ConstructorComponent) context.getBean("constructorComponent");
        cc.doSomething();
        
        // Inject POJO object using setter method
        SetterComponent sc = (SetterComponent) context.getBean("setterComponent");
        sc.doSomething();
        
    }
    
}