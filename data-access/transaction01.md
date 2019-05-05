
## transaction 관리
- 여러 액션을 한 단위의 작업으로 뭉뚱그린다. (완전히 성공하거나-commit, 아무런 영향을 미치지 않거나-rollback)
- 데이터 무결성과 일관성을 보장하는 기술

- 속성 : ACID
  + 원자성(Atomicity) : 원자성 작
  + 일관성(Consistency) : 데이터 및 리소스의 일관된 상태
  + 격리성(Isolation) : 트랜잭션의 격래
  + 지속성(Duration) : 데이터의 영구성

- 스프링 트랜잭션 관리의 목표는 POJO의 트랜잭션 처리 능력을 부여

- 선언적 트랜잭션 관리는 선언을 통해 트랜잭션 관리를 비지니스 메서드와 떼어놓는다.
- 트랜잭션 관리는 공통 관심사이므로, AOP를 이용해 모듈화 할 수 있다.

- low-level JDBC는 Connection 객체의 commit 혹은 rollback을 통한 transaction 처리 

- 스프링에서는 추상화된 PlatformTransactionManager, 
   TransactionTemplate, @Transactional 을 통해 반복적 작업이 이루어지지 않도록 기능 지원


### 1. PlatformTransactionManager

- 스프링 트랜잭션 관리 추상화의 핵심 인터페이스

- 3가지 메소드
  + getTransaction(TransactionDefinition definition)
  + commit(TransactionStatus status)
  + rollback(TransactionStatus status)


![Alt text](http://javacodebook.com/wp-content/uploads/2013/07/fig09-03.jpg)
  

### 2. TransactionTemplate

- JdbcTemplate과 유사한 TransactionTemplate을 제공함으로써 전체 트랜잭션 관리 프로세스 및 예외처리를 효과적으로 제어
- TransactionCallback 인터페이스를 구현한 클래스에서 excute 메서드에 전달
- 가벼운 객체이므로 , 성능에 영향 x



### 3. @Transactional

- 메소드 내에서 Exception이 발생하면 해당 메소드에서 이루어진 모든 DB작업을 초기화 시킵니다. 즉, save 메소드를 통해서 10개를 등록해야하는데 5번째에서 Exception이 발생하면 앞에 저장된 4개까지를 전부 롤백
  
-  이미 트랜잭션이 중이라면 참여하고 트랜잭션이 없다면 트랜잭션을 새로 시작하라

- 메소드/ 클래스 레벨에 @Transactional을 붙이면 persistence 같은 트랜잭션에서 처리하도록 보장한다.

- Config 클래스에는 @EnableTransactionManagement 를 선언 (@transactional 선언된 메소드 중 publc 메서드를 가져와 어드바이스 적용)