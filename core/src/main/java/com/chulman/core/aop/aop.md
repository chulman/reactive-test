# AOP
- 관점 지향 프로그래밍(Aspect Object Programming)
- 횡단 관심사 (CrossCutting)
- Pure Java로 구현
- OOP 모듈의 핵심단위가 클래스라면, AOP 모듈의 핵심단위 Aspect 이다. 
- Spring IOC 컨테이너는 AOP에 의존하지 않는다.

## 개념

- Aspect: 여러 클래스에 부가되는 영향을 미치는 모듈. @Aspect 혹은 aspect 구현 클래스. 가장 좋은 예는 transaction

- Join point: 메소드의 실행이나 예외 처리 같은 프로그램의 실행 중의 지점 

- Advice: 특정 조인 포인트에서 Aspect에 의해 취해지는 액션. around, before, after ..  

- Pointcut: 어드바이스를 적용할 조인포인트의 선별 기능을 정의한 모듈.
    + **포인트 컷 표현에 의해 일치하는 조인포인트를 구별하는게 스프링 aop의 핵심이며, aspectJ pointcut 표현식을 사용한다.** 

- Target object: 하나 혹은 여러 aspect에 의해 advised된 object. 항상 runtime proxies에 의해 구현되므로 proxied object 이다.

- Introduction: advised object에 추가적인 메소드 및 필드를 선언할 수 있다. 

- AOP proxy: 타겟 객체를 감싸서 타겟의 요청을 대신 받아주는 오브젝트. 타겟 실행 전, 후 처리 실
    + 프록시는 타겟의 요청을 가로챈 후 advise를 먼저 실행하며 이후 타겟 메소드를 실행한다.

- Weaving: 지정된 객체에 aspect를 적용해서 새로운 advised 객체를 생성하는 과정. 런타임에 위빙이 수행된다.


## advice type

- Before advice : join point 전에 실행되는 advice. 예외를 던지지 않는 한 join point가 실행되는 것을 막을 수는 없다.

- After returning advice : join point가 정상적으로 완료된 후 실행되는 advice
 
- After throwing advice: 메소드가 예외를 throw하고 종료되면 실행되는 advuce

- After (finally) advice: return 혹은 예외와 관계없이 join point 이 후 무조건 실행되는 advice

- Around advice : 메소드 호출과 같이 join point를 감싸는 advice 
    + **가장 강력한 advice**
    + 메소드 호출 전 혹은 후 사용자 정의 동작을 정의할 수 있음
    + 조인 포인트에서 계속 수행할지 혹은 return이나 예외를 Throw하여 advice를 선택할지 정할 수도 있다.


## Around Advice

- Around Advice는 모든 유형의 advice를 제공하는 가장 일반적이고 강력한 advice이다.
- 스프링에서 왠만하면 Around Advice를 사용하기를 권고한다.


## 핵심
- 포인트 컷에 의해 매칭되는 조인포인트의 개념이 AOP의 핵심이다.
- 포인트 컷은 객체지향 구조와 독립적으로 advice를 가능하게 한다.
- 예를 들면.. 선언적 transaction management


https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop

