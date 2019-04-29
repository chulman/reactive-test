# Spring Data-Access / Intergration


### ORM  Framework

-  ORM (Object Relation Mapping)
-  XML 혹은 어노테이션 기반의 매핑 메타데이터(클래스와 테이블, 프로퍼티와 컬럼간의 매핑 등)에 따라 객체를 저장
-  JDBC, Hibernate, iBatis/Mybatis, JPA, JDO 등 지원


### JDBC Template

- 여러 유형의 jdbc 작업을 템플릿으로 묶음
- 대부분의 jdbc API는 반드시 붙잡아 처리해야 하는 sqlException을 던지도록 선언
- jdbc template에서 발생하는 모든 예외는 RuntimeException을 상속한 DataAccessException의 하위 클래스라 붙잡을 필요가 없다.



### Hibernate

- 오픈소스 ORM 프레임워크
- caching, cascading, lazy loading 등의 기능 지원



### JPA

- Java Persistence API
- JSR-220 EJB에 명시
- 명료하게 jdbc API, JPA엔진은 jdbc 드라이버에 해당




  

