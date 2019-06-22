package com.chulman.access.transaction.bookshop;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;


public class TransactionalJdbcBookShop extends JdbcDaoSupport implements BookShop {

    private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    /**
     * Transaction 관리자 API를 사용해서 트랜잭션 관리
     */
//    public void purchase(String isbn, String username) {
//        TransactionDefinition def = new DefaultTransactionDefinition();
//        //Transaction 상태 추적용 객체
//        TransactionStatus status = transactionManager.getTransaction(def);
//
//        try {
//            int price = getJdbcTemplate().queryForObject(
//                    "SELECT PRICE FROM BOOK WHERE ISBN = ?", Integer.class, isbn);
//
//
//            getJdbcTemplate().update(
//                    "UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ?", isbn);
//
//            getJdbcTemplate().update(
//                    "UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?", price, username);
//
//            transactionManager.commit(status);
//        } catch (DataAccessException e) {   // Spring Jdbc Template 에서 발생하는 예외는 DataAccessException 하위형
//            transactionManager.rollback(status);
//            throw e;
//        }
//    }

    public void purchase(String isbn, String username) {

        /**
         * callback 객체를 실행하다가 unxcheck 예외(RuntimeException, DataAccessException) 이 발생하거나,
         * transactionStatus의 setRoolbackOnly() 메소드를 호출하면 롤백된다.
         * 이외에는 commit 됨.
         */
//        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
//        status.setRollbackOnly();

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                int price = getJdbcTemplate().queryForObject(
                        "SELECT PRICE FROM BOOK WHERE ISBN = ?", Integer.class, isbn);


                getJdbcTemplate().update(
                        "UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ?", isbn);

                getJdbcTemplate().update(
                        "UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?", price, username);
            }
        });

//        11:30:54.841 [main] DEBUG org.springframework.jdbc.core.JdbcTemplate - Executing prepared SQL update
//        11:30:54.842 [main] DEBUG org.springframework.jdbc.core.JdbcTemplate - Executing prepared SQL statement [UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ?]
//        11:30:54.844 [main] DEBUG org.springframework.jdbc.core.JdbcTemplate - SQL update affected 1 rows
//        11:30:54.844 [main] DEBUG org.springframework.jdbc.core.JdbcTemplate - Executing prepared SQL update
//        11:30:54.844 [main] DEBUG org.springframework.jdbc.core.JdbcTemplate - Executing prepared SQL statement [UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?]
//        11:30:54.844 [main] DEBUG org.springframework.jdbc.core.JdbcTemplate - SQL update affected 1 rows
//        11:30:54.845 [main] DEBUG org.springframework.jdbc.datasource.DataSourceTransactionManager - Initiating transaction commit
//        11:30:54.845 [main] DEBUG org.springframework.jdbc.datasource.DataSourceTransactionManager - Committing JDBC transaction on Connection [HikariProxyConnection@2029680286 wrapping conn0: url=jdbc:h2:mem:test user=SA]
    }

}