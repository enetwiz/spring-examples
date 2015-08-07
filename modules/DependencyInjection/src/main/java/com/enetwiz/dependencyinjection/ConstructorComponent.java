package com.enetwiz.dependencyinjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Component
public class ConstructorComponent {
    
    private ExampleBean bean = null;
    
    
    @Autowired
    public ConstructorComponent( ExampleBean pExampleBean ) {
        bean = pExampleBean;
    }
    
    public void doSomething() {
        bean.getLabel();
        System.out.println( bean.getLabel() );
        bean.setLabel("constructor-label");
        System.out.println( bean.getLabel() );
    }
}