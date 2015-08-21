package com.enetwiz.hibernateinheritance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Entity
@Table(name = "reptile")
public class Reptile extends Animal {
    
    @Column
    private boolean armor = false;
    
    
    public boolean hasArmor() {
        return armor;
    }
    
    public void setArmor(boolean pArmor) {
        armor = pArmor;
    }
    
}