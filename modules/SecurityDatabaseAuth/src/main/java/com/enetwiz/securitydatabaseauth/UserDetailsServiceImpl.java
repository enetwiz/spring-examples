package com.enetwiz.securitydatabaseauth;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO = null;
    
    @Override
    public UserDetails loadUserByUsername(String pUsername) throws UsernameNotFoundException {
        // Get user by username
        
        UserEntity userEntity = userDAO.getUserByUsername( pUsername );
        if ( userEntity == null || userEntity.getUsername() == null) {
            throw new UsernameNotFoundException("User " + pUsername + " not exists");
        }
        
        // get user roles and build user authorities
        List<String> roles = userEntity.;
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for (String role : roles) {
            authorities.add(new GrantedAuthorityImpl(role));
        }
        // instanciate Spring Security class User
        return new UserDetailsImpl(userEntity, userEntity, authorities);
    }
    
}