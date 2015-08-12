package com.enetwiz.hibernateusage;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Repository
@Transactional
public class ExampleEntityDao {
    
    @Autowired
    private SessionFactory sessionFactory = null;
    
    
    public void saveOrUpdate(ExampleEntity pExampleEntity) {
        sessionFactory.getCurrentSession().saveOrUpdate( pExampleEntity );
    }
    
    public List<ExampleEntity> list() {
        return sessionFactory.getCurrentSession()
                             .createCriteria( ExampleEntity.class )
                             .list();
    }
    
}