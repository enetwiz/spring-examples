package com.enetwiz.hibernaterelationship;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * @author Mariusz G <mg@netwiz.pl>
 */
public class ManyToMany {
    
    public static void main(String[] args) {
        // Loading the application context from annotation (using config file like AppConfig())
        ApplicationContext context = new AnnotationConfigApplicationContext( AppConfig.class );
        
        // Get hibernate active session
        SessionFactory sessionFactory = (SessionFactory) context.getBean( "sessionFactory" );
        Session session = sessionFactory.openSession();
        
        session.beginTransaction();
        
        // Create groups
        GroupEntity groupAdmin = new GroupEntity();
        groupAdmin.setName( "Administrator" );
        
        GroupEntity groupGuest = new GroupEntity();
        groupGuest.setName( "Guest" );
        
        // Create users
        UserEntity adminUser1 = new UserEntity();
        adminUser1.setUsername("AdminUser");
        
        UserEntity adminUser2 = new UserEntity();
        adminUser2.setUsername("AnotherAdminUser");

        UserEntity guestUser1 = new UserEntity();
        guestUser1.setUsername("GuestUser");
        
        // Save
        groupAdmin.addUser( adminUser1 );
        groupAdmin.addUser( adminUser2 );
        groupGuest.addUser( guestUser1 );
        session.save( groupAdmin );
        session.save( groupGuest );
        
        adminUser1.addGroup( groupAdmin );
        adminUser2.addGroup( groupAdmin );
        guestUser1.addGroup( groupGuest );
        session.save( adminUser1 );
        session.save( adminUser2 );
        session.save( guestUser1 );
        
        session.getTransaction().commit();
        
        // Get all entities from DB
        System.out.println("Groups:");
        System.out.println("---");
        System.out.println("id\t| name");
        System.out.println("--------------------");
        
        List<GroupEntity> groups = session.createCriteria( GroupEntity.class ).list();
        for (GroupEntity group : groups) {
            System.out.print("\n");
            System.out.println( group.getId() + "\t| " + group.getName() );
            System.out.println("--------------------");
            System.out.print("\n");
            
            System.out.println("\tUsers:");
            System.out.println("\tid\t| full name");
            System.out.println("\t-------------------------------");
            for (UserEntity user : group.getUsers()) {
                System.out.println( "\t" + user.getId() + "\t| " + user.getUsername() );
            }
        }
    }
    
}