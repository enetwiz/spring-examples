package com.enetwiz.initdestroymethods;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Component
public class ExampleComponent {
    
    private String label = "default";
    
    //opis tego jak to dziala: http://jlaskowski.blogspot.com/2007/01/nie-do-wiary-postconstruct-oraz.html
    @PostConstruct
    public void postConstruct() throws Exception {
        label = "post-construct";
        System.out.println("Init method - label are set to: " + label);
    }
    
    @PreDestroy
    public void preDestroy() throws Exception {
        label = null;
        System.out.println("Spring Container is destroy! Label are set to: " + label);
    }
    
    public ExampleComponent() {
        label = "constructor";
        System.out.println("Construct object - label are set to: " + label);
    }
    
}
