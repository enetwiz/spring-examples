package com.enetwiz.hibernateusage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
//TODO: zrzuuc tabele z DB
@Entity
@Table(name = "entities")
public class ExampleEntity {
    
    @Id
    @GeneratedValue
    private int id = 0;
    
    @Column(nullable = false, length = 50)
    private String label = null;
    
    
    public int getId() {
        return id;
    }
    
    public String getLabel() {
        return label;
    }
    
    public void setLabel(String pLabel) {
        label = pLabel;
    }
    
}