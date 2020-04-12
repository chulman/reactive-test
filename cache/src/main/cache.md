# Spring Cache
- Spring caching abstraction을 지원한다. 

- Spring caching abstraction는 다른 캐시 솔루션을 Spring CacheManager를 통해서 쉽게 사용할 수 있도록 해준다. 

- Spring CacheManager는 다양한 캐시의 솔루션들을 코드에 최소의 영향으로 다양한 캐싱 솔루션(라이브러리)을 도입할 수 있게 해준다. 

- Spring caching abstraction은 자바 메소드에 캐싱을 적용한다. 

- 메소드가 실행될 때마다 메소드의 넘어온 매개변수에 따라 캐쉬를 적용하게 된다. 


# annotation

- @Cacheable    :	메소드에 캐시 트리거 설정
- @CachePut	    :   메소드 실행과 방해없이 캐시 갱신
- @CacheEvict   :   캐시되있는 데이터 지우기
- @Caching      :   여러개의 cache 어노테이션 지정 가능(Cacheable, Cachevict ..)
- @CacheConfig  :   캐쉬 관련 설정
- @EnableCaching:	스프링 캐시활성화


# Key Generation
- 캐시는 본질적으로 키-밸류 저장소이므로 캐시된 메서드를 호출할 때마다 해당 키로 변환되어야 한다. 캐시 추상화는 다음 알고리즘에 기반을 둔 KeyGenerator를 사용한다.

- 파라미터가 없으면 0을 반환한다.
- 파라미터가 하나만 있으면 해당 인스턴스를 반환한다.
- 파라미터가 둘 이상이면 모든 파라미터의 해시를 계산한 키를 반환한다.
- 이 접근은 객체를 리플랙션하는 hashCode()처럼 일반적인 키를 가진 객체와 잘 동작한다. 이러한 경우가 아니고 분산 혹은 유지(persistent)되는 환경이라면 객체가 hashCode를 보관하지 않도록 전략을 변경해야 한다. 사실 JVM 구현체나 운영하는 환경에 따라 같은 VM 인스턴스에서 hashCode를 다른 객체에서 재사용할 수 있다.

다른 기본 키 생성자를 제공하려면 org.springframework.cache.KeyGenerator 인터페이스를 구현해야 한다. 이를 구성하고 나면 전용 키 생성 전략(아래 참고)를 지정하지 않은 모든 선언에서 이 생성자를 사용할 것이다.