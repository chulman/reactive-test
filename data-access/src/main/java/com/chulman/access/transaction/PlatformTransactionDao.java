package com.chulman.access.transaction;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class PlatformTransactionDao {

    private PlatformTransactionManager platformTransactionManager;

    public PlatformTransactionDao(PlatformTransactionManager platformTransactionManager){
        this.platformTransactionManager = platformTransactionManager;
    }

    public void insert(){
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus status = platformTransactionManager.getTransaction(transactionDefinition);

        JdbcTemplate template = new JdbcTemplate();
        try{
//            template.update("INSERT INTO ...");

            platformTransactionManager.commit(status);
        } catch (DataAccessException e){
            platformTransactionManager.rollback(status);
            throw e;
        }
    }
}
