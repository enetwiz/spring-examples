package com.enetwiz.hibernateinheritance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Entity
@Table(name = "cellphone")
public class CellphoneEntity extends PhoneEntity {
    
    @Column
    private boolean keyboard = false;

    public boolean hasKeyboard() {
        return keyboard;
    }

    public void setKeyboard(boolean pKeyboard) {
        this.keyboard = pKeyboard;
    }
    
}