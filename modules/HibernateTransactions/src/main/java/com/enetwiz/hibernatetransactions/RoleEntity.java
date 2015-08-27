package com.enetwiz.hibernatetransactions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Entity
@Table(name = "mrole")
public class RoleEntity {
    
    @Id
    @GeneratedValue
    private int id = 0;
    
    @Column(nullable = false, length = 50)
    private String name = null;
    
    @OneToOne(mappedBy = "role")
    private UserEntity user = null;

    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }
    
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity pUser) {
        user = pUser;
    }
    
}