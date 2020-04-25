# Recipe

- recipe 11-1 : 기초 설정
- recipe 11-2 : 읽기 / 쓰기
- recipe 11-3 : 커스텀 읽기 / 쓰기
- recipe 11-4 : 출력 전 Processor 에서 데이터 처리하기
- recipe 11-5 : 트랜잭션
- recipe 11-6 : 재시도(retry)
- recipe 11-7 : 스텝 실행 제어
- recipe 11-8 : 잡 실행
- recipe 11-9 : 잡을 매개변수화


### 개념 참조   
> https://joont92.github.io/spring/Spring-Batch/ 

# spring-batch

- 스프링 배치는 보통 대용량 데이터를 읽어 변환된 형식으로 다시 출력한다.
- 트랜잭션 경계, 입력크기, 동시성, 처리단계의 차수 등 연계까지 생각하면 결정해야 할 요소가 많다.


### 런타임 메타데이터 모델

- 스프링 배치는 잡(JobInstances, JobExevution, StepExecution 포함한) 단위로 모든 정보와 메타데이터를 총괄한 jobRepository를 중심으로 작동하며 각 잡은 순차적인 스텝으로 구성된다.
- 스텝은 순차적으로 진행될 수 도 있고, 동시성으로 진행될 수 도 있다.

- JOB : 잡은 보통 실행 시점에 JobParameter와 엮어 자신의 런타임 로직을 매개변수화 한다.그리고 잡 실행을 식별하기 위해 JobInstance를 생성한다.
- JobInstance : JobParameter와 연계되기 때문에 인스턴스는 하나만 존재한다.
- JobExecution :  같은 잡 인스턴스가 실행되는 것(Job+JobParameter)


### JobRepository

- JobRepository 인터페이스는 스프링 배치 설정의 첫 단추이다. 구현체는 SimpleJobRepository
- JobRepository는 DB를 전제로 작동하므로 스키마는 미리 구성되어 있어야 한다.


### Step

- step은 job의 가장 작은 단위.


### Chunk
- input이 스텝으로 전해지고, 처리가 끝나면 출력이 만들어진다.
- chunk 처리는 입력을 읽고 필요시 부가적인 처리를 한 뒤 애그리게이션(종합) 한다.
- commit-inertval을 설정하여 처리 주기를 설정할 수 있다.

```
트랜잭션을 인지한 출력기 혹은 처리기가 롤백할 때는 입력값을 집계하는 문제와 관련해 미묘한 차이가 발생하는데 스프링 배치는 읽은 값을 캐시했다가 출력 값에 쓴다.
출력기는 DB처럼 트랜잭션이 걸려있지만 입력기는 트랜잭션이 걸려 있지 않으면 읽은 값을 캐시하거나 재시도 또는 다른 방법으로 접근해도 본질적으로 잘못이 없다.
그런데 입력기에도 트랜잭션이 적용된 상태면 리소스에서 읽은 값이 롤백되고 변경 가능한 상태로 메모리에 남겨진 캐시값은 무용지물되고 만다.
이런 일이 발생할 경우 reader-transactional-queue=true 로 설정하여 청크 엘리먼트에 값을 캐시하지 않도록 설정할 수 있다.
``` 