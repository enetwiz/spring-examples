package com.enetwiz.hibernatetransactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Service
public class ProgrammicUserRoleService {
    
    @Autowired
    private RoleDAO roleDAO = null;
    
    @Autowired
    private UserDAO userDAO = null;
    
    @Autowired
    private HibernateTransactionManager transactionManager = null;
    
    
    public void commitedExample( RoleEntity pRoleEntity, UserEntity pUserEntity ) {    
        // Create new transaction
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction( transactionDefinition );
        
        try {
            roleDAO.save( pRoleEntity ); //TODO: opisz ze kolejnosc jest wazna
            userDAO.save( pUserEntity );
            transactionManager.commit( transactionStatus );
        } catch (Exception e) {
            // Rollback transaction when is caught the exception
            transactionManager.rollback( transactionStatus );
            System.err.println( e.getMessage() );
        }
        
    }
    
    public void uncommitedExample( RoleEntity pRoleEntity, UserEntity pUserEntity ) {
        // Create new transaction
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction( transactionDefinition );
        
        try {
            roleDAO.save( pRoleEntity );
            userDAO.save( pUserEntity );
            int test = 1/0; // Force exception for uncommited transaction test
            transactionManager.commit( transactionStatus );
        } catch (Exception e) {
            // Rollback transaction when is caught the exception
            transactionManager.rollback( transactionStatus );
            System.err.println( e.getMessage() );
        }
        
    }
    
}
