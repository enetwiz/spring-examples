package com.enetwiz.hibernateinheritance;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Entity
@Table(name = "phone")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PhoneEntity {
    
    @Id
    @GeneratedValue
    private int id = 0;
    
    @Column
    private int screenSize = 0; // in milimeters
    
    
    public int getId() {
        return id;
    }
    
    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }
    
    
    
    
}