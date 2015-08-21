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
@Table(name = "vehicle")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator")
public abstract class VehicleEntity {
    
    @Id
    @GeneratedValue
    private int id = 0;
    
    @Column(nullable = false, length = 50)
    private String manufacturer = null;
    
    
    public int getId() {
        return id;
    }
    
    public String getManufacturer() {
        return manufacturer;
    }
    
    public void setManufacturer(String pManufacturer) {
        manufacturer = pManufacturer;
    }
    
}