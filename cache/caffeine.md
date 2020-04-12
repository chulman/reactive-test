# caffeine

- Caffeine Cache는 고성능 캐시 라이브러리이다. 캐시에서 어떤 오브젝트를 제거할 지는 eviction policy에 의해 결정된다. 따라서 eviction policy는 캐시의 히트율에 직접적으로 영향을 준다. 또한 eviction policy는 캐시 라이브러리의 가장 중요한 특징 중 하나이다. Caffeine Cache는 Window TinyLfu eviction policy를 사용하며, 대부분의 경우 높은 히트율을 제공한다.


- Caffeine Cache는 일정한 주기로 데이터를 Refresh하는 기능도 제공한다. Refresh는 비동기적으로 진행된다. 캐시에 있는 데이터를 Refresh하는 동안 캐시에 접근하면 캐시는 이전 데이터를 반환한다.

- Caffeine#recordStats() 메소드를 통해 통계 수집 기능을 사용할 수도 있다. Cache#stats() 메소드는 CacheStats 객체를 리턴한다. CacheStats 객체를 통해 히트율, eviction count, 새로운 값을 로딩하는데 소요된 평균 시간 등을 구할 수 있다. 이러한 통계는 캐시를 튜닝하는데 큰 도움이 된다. 또한 성능이 중요한 애플리케이션은 이러한 통계를 지속적으로 모니터링해야 한다.