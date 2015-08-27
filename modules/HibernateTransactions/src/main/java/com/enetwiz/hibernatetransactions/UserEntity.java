package com.enetwiz.hibernatetransactions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Entity
@Table(name = "muser")
public class UserEntity {
    
    @Id
    @GeneratedValue
    private int id = 0;
    
    @Column(nullable = false, length = 50)
    private String name = null;
    
    @OneToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role = null;
    
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String pName) {
        name = pName;
    }
    
    public RoleEntity getRole() {
        return role;
    }
    
    public void setRole(RoleEntity pRole) {
        role = pRole;
    }
    
}