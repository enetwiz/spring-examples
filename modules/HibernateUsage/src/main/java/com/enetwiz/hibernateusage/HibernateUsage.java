package com.enetwiz.hibernateusage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * @author Mariusz G <mg@netwiz.pl>
 */
public class HibernateUsage {
    
    public static void main(String[] args) {
        // Loading the application context from annotation (using config file like AppConfig())
        ApplicationContext context = new AnnotationConfigApplicationContext( AppConfig.class );
        
        // Create new entity object with random number in label
        ExampleEntity exampleEntity = new ExampleEntity();
        exampleEntity.setLabel("hello world! " + (int)(Math.random() * 100) );
        
        // Add new entity to DB
        System.out.println("Add new entity to database");
        System.out.println("---");
        ExampleEntityDao exampleEntityDAO = context.getBean( ExampleEntityDao.class );
        exampleEntityDAO.saveOrUpdate( exampleEntity );
        
        // Get all entities from DB
        System.out.println("Added enitities:");
        System.out.println("---");
        System.out.println("id\t| label");
        System.out.println("--------------------");
        for (ExampleEntity entity : exampleEntityDAO.list()) {
            System.out.println( entity.getId() + "\t| " + entity.getLabel() );
        }
    }
    
}