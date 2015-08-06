package com.enetwiz.helloworld;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
public class HelloBean {
    
    private long value = 0L;
    
    
    public void say() {
        System.out.println("Hello world from Bean!");
    }
    
    public long getValue() {
        return value;
    }
    
    public void setValue( long pValue ) {
        value = pValue;
    }
    
}