package com.enetwiz.securitydatabaseauth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Entity
@Table(name = "authorities")
public class AuthorityEntity {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private UserEntity username = null;
    
    @Column(nullable = false, length = 50)
    private String authority = null;
    
    @Column
    private boolean enabled = true;

    
    public UserEntity getUsername() {
        return username;
    }

    public void setUsername(UserEntity pUsername) {
        username = pUsername;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String pAuthority) {
        authority = pAuthority;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean pEnabled) {
        enabled = pEnabled;
    }
    
}