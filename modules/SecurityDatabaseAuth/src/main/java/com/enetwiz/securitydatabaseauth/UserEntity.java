package com.enetwiz.securitydatabaseauth;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Entity
@Table(name = "users")
public class UserEntity {
    
    @Id
    private String username = null;
    
    @Column(nullable = false, length = 50)
    private String password = null;
    
    @Column
    private boolean enabled = true;
    
    @OneToMany(mappedBy = "username")
    private Set<AuthorityEntity> authorities = new HashSet<>(0);
    
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String pUsername) {
        username = pUsername;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String pPassword) {
        password = pPassword;
    }
    
    public boolean isEnabled() {
        return enabled;
    }
    
    public void setEnabled(boolean pEnabled) {
        enabled = pEnabled;
    }
    
    public Set<AuthorityEntity> getAuthorities() {
        return authorities;
    }
    
    public void setAuthorities(Set<AuthorityEntity> pAuthorities) {
        authorities = pAuthorities;
    }
    
}