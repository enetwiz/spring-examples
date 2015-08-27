package com.enetwiz.hibernatetransations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Service
public class UserRoleService {
    
    @Autowired
    private RoleDAO roleDAO = null;
    
    @Autowired
    private UserDAO userDAO = null;
    
    
    @Transactional
    public void commitedExample( RoleEntity pRoleEntity, UserEntity pUserEntity ) {    
        roleDAO.save( pRoleEntity ); //TODO: opisz ze kolejnosc jest wazna
        userDAO.save( pUserEntity );
    }
    
    @Transactional(timeout = 1)
    public void uncommitedExample( RoleEntity pRoleEntity, UserEntity pUserEntity ) {
        roleDAO.save( pRoleEntity );
        
        // Add unexpected timeout
        long currentTimeMillis = System.currentTimeMillis();
        while( (currentTimeMillis + 1500) > System.currentTimeMillis() ) {
            // do nothing - just wait
        }
        
        userDAO.save( pUserEntity );
    }
    
}
