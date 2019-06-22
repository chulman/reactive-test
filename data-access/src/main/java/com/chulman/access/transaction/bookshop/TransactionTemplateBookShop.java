package com.chulman.access.transaction.bookshop;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Transaction Template을 사용해서 트랜잭션 관리하기
 */
public class TransactionTemplateBookShop {

    private PlatformTransactionManager platformTransactionManager;

    public TransactionTemplateBookShop(PlatformTransactionManager platformTransactionManager){
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
