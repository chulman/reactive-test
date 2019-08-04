package com.chulman.access.transaction.annotation;

import com.chulman.access.transaction.bookshop.BookShop;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

public class AnnotationTxBookSHopEx extends JdbcDaoSupport implements BookShop {


    /**
     * @transaction은 프록시를 사용해 메서드를 가져와 실행해야 하는데 private, protected 접근자를 붙으면 가져올 수 없기 떄문에
     * 에러는 나지 않지만, 무시된다. 반드시 public 클래스나 메소드여야 한다.
     *
     */
    @Transactional
    public void purchase(String isbn, String username) {
        int price = getJdbcTemplate().queryForObject("SELECT PRICE FROMBOOK WHERE ISBN=?",Integer.class, isbn);

        getJdbcTemplate().update("UPDATE BOOK_STOCK SET STOCK = STOCK -1 WHERE ISBN = ?", isbn);
        getJdbcTemplate().update("UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?", price, username);
    }
}
