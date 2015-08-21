package com.enetwiz.hibernateinheritance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Entity
@Table(name = "truck")
public class TruckEntity extends VehicleEntity {
    
    @Column
    private int capacity = 0; // in kilograms
    
    
    public int getCapacity() {
        return capacity;
    }
    
    public void setCapacity(int pCapacity) {
        capacity = pCapacity;
    }
    
}