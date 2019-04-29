package com.chulman.access.jdbc.exception;

import org.springframework.dao.DataIntegrityViolationException;

// 스프링 jdbc 가 던지는 모든 예외의 루트 클래스는 반드시 DataAccessException이어야 한다.
// 기본적으로 스프링은 sql-error-codes.xml 파일에서 예외를 찾지만 클래스 패스 루트에 이름이 같은 파일을 두고 오버라이드할 수 있다.
public class MyDuplicateKeyException extends DataIntegrityViolationException {

    public MyDuplicateKeyException(String msg) {
        super(msg);
    }

    public MyDuplicateKeyException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
