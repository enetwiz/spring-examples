package com.enetwiz.propertyaccess;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Bean with configuration based on properties file
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Configuration
public class Config {

	@Value("${my.property}")
	private String myProperty;

	@Value("${another.property}")
	private String anotherProperty;

	@Value("${custom.number}")
	private String customNumber;

    public void printMyProperty() {
        System.out.println(myProperty);
    }

	public void printAnotherProperty() {
		System.out.println(anotherProperty);
	}

	public void printCustomNumber() {
		System.out.println(customNumber);
	}

	public void sayNothing() {
		System.out.println("nothing");
	}
    
}