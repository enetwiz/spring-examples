package com.enetwiz.hibernatetransactions;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Declarative transaction example
 * 
 * @author Mariusz G <mg@netwiz.pl>
 */
public class DeclarativeTransactions {
    
    public static void main(String[] args) {
        // Loading the application context from annotation (using config file like AppConfig())
        ApplicationContext context = new AnnotationConfigApplicationContext( AppConfig.class );
        
        // Get service object
        UserRoleService userRoleService = context.getBean( UserRoleService.class );
        
        // Commited transaction example
        RoleEntity role = new RoleEntity();
        role.setName("Administrator");
        
        UserEntity user =  new UserEntity();
        user.setName("John Declarative");
        user.setRole( role );
        
        userRoleService.commitedExample( role, user );
        
        // Uncommited transaction example
        RoleEntity anotherRole = new RoleEntity();
        anotherRole.setName("Moderator");
        
        UserEntity anotherUser =  new UserEntity();
        anotherUser.setName("Mark Declarative");
        anotherUser.setRole( anotherRole );
        
        try {
            userRoleService.uncommitedExample( anotherRole, anotherUser );
        } catch (Exception e) {
            System.err.println( e.getMessage() );
        }
        
    }
    
}