package com.enetwiz.hibernaterelationship;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Entity
@Table(name = "mgroup")
public class GroupEntity {
    
    @Id
    @GeneratedValue
    private int id = 0;
    
    @Column(nullable = false, length = 50)
    private String name = null;
    
    @ManyToMany
    @JoinTable( name = "user_group", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "user_id") )
    private Set<UserEntity> users = new HashSet<>();
    
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }
    
    public void addUser(UserEntity pEntity) {
        users.add( pEntity );
    }
    
    public Set<UserEntity> getUsers() {
        return users;
    }
    
}