package com.enetwiz.hibernateinheritance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Animal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id = 0;
    
    @Column
    private int legs = 0;
    
    
    public int getId() {
        return id;
    }

    public int getLegs() {
        return legs;
    }

    public void setLegs(int pLegs) {
        legs = pLegs;
    }
    
}