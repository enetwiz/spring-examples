package com.enetwiz.securitydatabaseauth;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Repository
@Transactional
public class UserDAO {
    
    @Autowired
    private SessionFactory sessionFactory = null;
    
    
    public UserEntity getUserByUsername(String pUsername) {
        
        Session session = sessionFactory.getCurrentSession();
        
        Criteria criteria = session.createCriteria( UserEntity.class );
        criteria.add( Restrictions.eq("username", pUsername) );
        
        return (UserEntity) criteria.uniqueResult();
    }
    
}