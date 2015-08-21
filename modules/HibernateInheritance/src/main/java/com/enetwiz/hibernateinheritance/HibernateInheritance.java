package com.enetwiz.hibernateinheritance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * @author Mariusz G <mg@netwiz.pl>
 */
public class HibernateInheritance {
    
    public static void main(String[] args) {
        // Loading the application context from annotation (using config file like AppConfig())
        ApplicationContext context = new AnnotationConfigApplicationContext( AppConfig.class );
        
        // Get hibernate active session
        SessionFactory sessionFactory = context.getBean( SessionFactory.class );
        Session session = sessionFactory.openSession();
        
        session.beginTransaction();
        
        // Single table strategy - create vehicles
        TruckEntity truck =  new TruckEntity();
        truck.setManufacturer("Toyota");
        truck.setCapacity(10000);
        session.saveOrUpdate( truck );
        
        MotorcycleEntity motorcycle =  new MotorcycleEntity();
        motorcycle.setManufacturer("Kawasaki");
        motorcycle.setMaxSpeed(350);
        session.saveOrUpdate( motorcycle );
        
        // Table per class strategy - create animals
        BirdEntity bird = new BirdEntity();
        bird.setLegs(2);
        bird.setBeak(true);
        session.saveOrUpdate( bird );
        
        ReptileEntity reptile = new ReptileEntity();
        reptile.setLegs(4);
        reptile.setArmor(true);
        session.saveOrUpdate( reptile );
        
        // Joined inheritance strategy - Create phones
        SmartphoneEntity smartphone = new SmartphoneEntity();
        smartphone.setTouchScreen(true);
        smartphone.setScreenSize(60);
        smartphone.setTouchScreen(true);
        session.saveOrUpdate( smartphone );
        
        CellphoneEntity cellphone = new CellphoneEntity();
        cellphone.setScreenSize(30);
        cellphone.setKeyboard(true);
        session.saveOrUpdate( cellphone );
        
        session.getTransaction().commit();
    }
    
}