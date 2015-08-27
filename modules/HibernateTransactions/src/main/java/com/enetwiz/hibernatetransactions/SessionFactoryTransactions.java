package com.enetwiz.hibernatetransactions;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * @author Mariusz G <mg@netwiz.pl>
 */
public class SessionFactoryTransactions {
    
    public static void main(String[] args) {
        // Loading the application context from annotation (using config file like AppConfig())
        ApplicationContext context = new AnnotationConfigApplicationContext( AppConfig.class );
        
        // Get hibernate active session
        SessionFactory sessionFactory = context.getBean( SessionFactory.class );
        Session session = sessionFactory.openSession();
        
        // Simple example of commited transaction
        session.beginTransaction();
        
        RoleEntity role = new RoleEntity();
        role.setName("Administrator");
        session.save( role );
        
        UserEntity user =  new UserEntity();
        user.setName("John von Session");
        user.setRole( role );
        session.save( user );
        
        session.getTransaction().commit();
        
        // Simple example of uncommited transaction
        session.beginTransaction();
        
        RoleEntity anotherRole = new RoleEntity();
        anotherRole.setName("Moderator");
        session.save( anotherRole );
        
        UserEntity anotherUser =  new UserEntity();
        anotherUser.setName("Mark von Session");
        anotherUser.setRole( anotherRole );
        session.save( anotherUser );
        
        session.getTransaction().rollback();
    }
    
}