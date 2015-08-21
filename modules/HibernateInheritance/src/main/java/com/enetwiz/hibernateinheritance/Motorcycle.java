package com.enetwiz.hibernateinheritance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Entity
@Table(name = "motorcycle")
public class Motorcycle extends Vehicle {
    
    @Column
    private int maxSpeed = 0; // in kilometers per hour
    
    
    public int getMaxSpeed() {
        return maxSpeed;
    }
    
    public void setMaxSpeed(int pMaxSpeed) {
        maxSpeed = pMaxSpeed;
    }
    
}