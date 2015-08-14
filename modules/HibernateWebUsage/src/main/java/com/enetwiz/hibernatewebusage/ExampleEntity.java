package com.enetwiz.hibernatewebusage;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Entity
@Table(name = "entities")
public class ExampleEntity {
    
    @Id
    @GeneratedValue
    private int id = 0;
    
    @Column(nullable = false, length = 50)
    private String label = null;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date created = null;
    
    
    public int getId() {
        return id;
    }
    
    public String getLabel() {
        return label;
    }
    
    public void setLabel(String pLabel) {
        label = pLabel;
    }
    
    public Date getCreated() {
        return created;
    }
    
}