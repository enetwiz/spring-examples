package com.enetwiz.dependencyinjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Component
public class PropertyComponent {
    
    @Autowired
    private ExampleBean bean = null;
    
    
    public void doSomething() {
        bean.getLabel();
        System.out.println( bean.getLabel() );
        bean.setLabel("property-label");
        System.out.println( bean.getLabel() );
    }
}