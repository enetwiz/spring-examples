package com.enetwiz.componentscope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * Przyklad zmiany zasiegu componentu
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
public class ComponentScope {
    
    public static void main(String[] args) {
        
        // Loading the application context from annotation (using config file like AppConfig())
        ApplicationContext context = new AnnotationConfigApplicationContext( AppConfig.class );
        
        // Try to create two different objects using component with default scope
        DefaultComponent dc = (DefaultComponent) context.getBean("defaultComponent");
        dc.setLabel("label");
        DefaultComponent dc2 = (DefaultComponent) context.getBean("defaultComponent");
        dc2.setLabel("another label");
        
        System.out.println( dc.getLabel() );
        System.out.println( dc2.getLabel() );
        
        System.out.println( "---" );
        
        // Try to create two different objects using component with prototype scope
        PrototypeComponent pc = (PrototypeComponent) context.getBean("prototypeComponent");
        pc.setLabel("label");
        PrototypeComponent pc2 = (PrototypeComponent) context.getBean("prototypeComponent");
        pc2.setLabel("another label");
        
        System.out.println( pc.getLabel() );
        System.out.println( pc2.getLabel() );
        
    }
    
}