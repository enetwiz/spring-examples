package com.enetwiz.hibernaterelationship;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
    private String username = null;
    
    @ManyToMany( mappedBy = "users" )
    private Set<GroupEntity> groups = new HashSet<>();
    
    
    public int getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String pUsername) {
        username = pUsername;
    }
    
    public void addGroup(GroupEntity pEntity) {
        groups.add( pEntity );
    }
    
    public Set<GroupEntity> getGroups() {
        return groups;
    }
    
}