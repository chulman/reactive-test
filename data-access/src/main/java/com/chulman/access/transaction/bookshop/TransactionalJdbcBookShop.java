package com.chulman.access.transaction.bookshop;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * Transaction 관리자 API를 사용해서 트랜잭션 관리
 */
public class TransactionalJdbcBookShop extends JdbcDaoSupport implements BookShop {

    private PlatformTransactionManager transactionManager;

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void purchase(String isbn, String username) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        //Transaction 상태 추적용 객
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            int price = getJdbcTemplate().queryForObject(
                    "SELECT PRICE FROM BOOK WHERE ISBN = ?", Integer.class, isbn);


            getJdbcTemplate().update(
                    "UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ?", isbn);

            getJdbcTemplate().update(
                    "UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?", price, username);

            transactionManager.commit(status);
        } catch (DataAccessException e) {   // Spring Jdbc Template 에서 발생하는 예외는 DataAccessException 하위형
            transactionManager.rollback(status);
            throw e;
        }
    }

}