## 하이버네이트와 JPA 기술로 객체를 매핑 및 저장하는 전략

- XML로 매핑한 객체를 하이버네이트 API를 이용하여 저장
- JPA 어노테이션을 붙인 객체를 하이버네이트 API를 이용하여 저장
- JPA 어노테이션을 붙인 객체를 JPA를 이용하여 저장


## Data-Access 전략별 핵심 프로그래밍 요소

| 개념 |  <center>JDBC</center> |  <center>Hibernate</center> |  <center>JPA</center> |
|:--------|:--------|:--------:|--------:|
|리소스      |**Connection** | <center> Session </center> |*EntityManager* |
|리소스 팩토리 |**DataSource** | <center>SessionFactory </center> |*EntityManagerFactory* |
|예외       |**SQLException** | <center>HibernateException </center> |*PersistenceException* |


- PersistenceException은 자바 SE 예외형(IllegalArgumentException, IllegalStateException) RuntimeException의 하위 예외이다.


## spring-data-jpa 

- 데이터 액세서 코드를 수시로 작성하는 일을 제거(EntityManager, EntityManagerFactory)
- 비지니스 로직에만 집중(find, delete, ..)

- CrudRepository 혹은 JpaRepository 상속 (JpaRepository를 사용하면 flush, saveAndFlush와 정렬 기능이 부가된 쿼리 기능 사용 가능)
- 모든 Repository 메소드에 @Transactional이 기본적으로 달려 있다.


- config에 @EnableJpaRepository, @EnableTransactionManagement
