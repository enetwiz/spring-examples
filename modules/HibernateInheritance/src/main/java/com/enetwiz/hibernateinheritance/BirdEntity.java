package com.enetwiz.hibernateinheritance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Entity
@Table(name = "bird")
public class BirdEntity extends AnimalEntity {
    
    @Column
    private boolean beak = false;
    
    public boolean hasBeak() {
        return beak;
    }

    public void setBeak(boolean pBeak) {
        beak = pBeak;
    }
    
}