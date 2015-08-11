package com.enetwiz.singeltonfactory;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
public class ExampleSingleton {
    
    private String label = "default";
    
    
    private ExampleSingleton() {
        
    }
    
    private static class ExampleSingeltonHolder {
        private static ExampleSingleton instance = new ExampleSingleton();
    }
    
    public static ExampleSingleton getInstance() {
        return ExampleSingeltonHolder.instance;
    }
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String pLabel) {
        label = pLabel;
    }
    
}