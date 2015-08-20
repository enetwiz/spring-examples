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
public class OneToMany {
    
    public static void main(String[] args) {
        // Loading the application context from annotation (using config file like AppConfig())
        ApplicationContext context = new AnnotationConfigApplicationContext( AppConfig.class );
        
        // Get hibernate active session
        SessionFactory sessionFactory = context.getBean( SessionFactory.class );
        Session session = sessionFactory.openSession();
        
        session.beginTransaction();
        
        // Create first company
        CompanyEntity company1 = new CompanyEntity();
        company1.setName( "Google INC" );
        session.saveOrUpdate( company1 );
        
        // Create employess
        EmployeeEntity c1Employee1 = new EmployeeEntity();
        c1Employee1.setCompany( company1 );
        c1Employee1.setFirstName( "Sergey" );
        c1Employee1.setLastName( "Brin" );
        company1.getEmployees().add( c1Employee1 );
        session.saveOrUpdate( c1Employee1 );
        
        EmployeeEntity c1Employee2 = new EmployeeEntity();
        c1Employee2.setCompany( company1 );
        c1Employee2.setFirstName( "Larry" );
        c1Employee2.setLastName( "Page" );
        company1.getEmployees().add( c1Employee2 );
        session.saveOrUpdate( c1Employee2 );
        
        // Create second company
        CompanyEntity company2 = new CompanyEntity();
        company2.setName( "Virgin Mobile" );
        session.saveOrUpdate( company2 );
        
        EmployeeEntity c2Employee1 = new EmployeeEntity();
        c2Employee1.setCompany( company2 );
        c2Employee1.setFirstName( "Richard" );
        c2Employee1.setLastName( "Branson" );
        company2.getEmployees().add( c2Employee1 );
        session.saveOrUpdate( c2Employee1 );
        
        session.getTransaction().commit();
        
        // Get all entities from DB
        System.out.println("Added companies:");
        System.out.println("---");
        System.out.println("id\t| name");
        System.out.println("--------------------");
        
        List<CompanyEntity> companies = session.createCriteria( CompanyEntity.class ).list();
        for (CompanyEntity company : companies) {
            System.out.print("\n");
            System.out.println( company.getId() + "\t| " + company.getName() );
            System.out.println("--------------------");
            System.out.print("\n");
            
            System.out.println("\tCompany employees:");
            System.out.println("\tid\t| full name");
            System.out.println("\t-------------------------------");
            for (EmployeeEntity employee : company.getEmployees()) {
                System.out.println( "\t" + employee.getId() + "\t| " + employee.getFirstName() + " " + employee.getLastName() );
            }
            
        }
    }
    
}