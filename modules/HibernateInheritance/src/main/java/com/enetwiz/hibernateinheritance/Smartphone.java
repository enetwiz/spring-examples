package com.enetwiz.hibernateinheritance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Entity
@Table(name = "smartphone")
public class Smartphone extends Phone {
    
    @Column
    private boolean touchScreen = false;
    
    public boolean hasTouchScreen() {
        return touchScreen;
    }
    
    public void setTouchScreen(boolean pTouchScreen) {
        touchScreen = pTouchScreen;
    }
    
}