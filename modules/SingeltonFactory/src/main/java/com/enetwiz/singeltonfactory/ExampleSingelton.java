package com.enetwiz.singeltonfactory;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
public class ExampleSingelton {
    
    private String label = "default";
    
    
    private ExampleSingelton() {
        
    }
    
    private static class ExampleSingeltonHolder {
        private static ExampleSingelton instance = new ExampleSingelton();
    }
    
    public static ExampleSingelton getInstance() {
        return ExampleSingeltonHolder.instance;
    }
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String pLabel) {
        label = pLabel;
    }
    
}