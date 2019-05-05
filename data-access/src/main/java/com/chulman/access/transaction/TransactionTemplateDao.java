package com.chulman.access.transaction;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class TransactionTemplateDao {

    private PlatformTransactionManager platformTransactionManager;

    public TransactionTemplateDao(PlatformTransactionManager platformTransactionManager){
        this.platformTransactionManager = platformTransactionManager;
    }

    public void insert(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {

//                jdbcTemplate.update("UPdate....");

            }
        });

        transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(TransactionStatus status) {
//                return jdbcTemplate.update("..");
                return 0;
            }
        });
    }
}
