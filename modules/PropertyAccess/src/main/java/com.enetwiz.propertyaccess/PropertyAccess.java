package com.enetwiz.propertyaccess;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Przyklad dostepu do ustawien zapisanych w plikach *.properties
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
public class PropertyAccess {
    
    public static void main(String[] args) {
		// Loading the application context from XML file (src/resources/context.xml)
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		final Config bean = context.getBean(Config.class);
		bean.printAnotherProperty();
		bean.printMyProperty();
		bean.printCustomNumber();
		bean.sayNothing();
	}
    
}