package com.enetwiz.helloworld;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
public class HelloWorld {
    
    public static void main(String[] args) {
        // Loading the application context from XML file (src/resources/hello.xml)
        ApplicationContext context = new ClassPathXmlApplicationContext("hello.xml");
        
        // Get bean from context and say hello
        Hello hello = (Hello) context.getBean("hello");
        hello.say();
    }
    
}