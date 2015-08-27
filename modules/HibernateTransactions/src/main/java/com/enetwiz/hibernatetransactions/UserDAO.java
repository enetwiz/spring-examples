package com.enetwiz.hibernatetransactions;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Repository
public class UserDAO {
    
    @Autowired
    private SessionFactory sessionFactory = null;
    
    public void save( UserEntity pUserEntity ) {
        sessionFactory.getCurrentSession().save( pUserEntity );
    }
    
}