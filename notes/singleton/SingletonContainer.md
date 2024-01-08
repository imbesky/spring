# Singleton container

## Singleton pattern

a software design pattern that restricts the instantiation of a class to a singular instance

- 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴
- 객체 인스턴스 2개 이상 생성 불가

### 기존 방식의 문제점

- 웹 어플리케이션의 경우 여러 고객이 동시에 요청을 함
- 순수한 DI 컨테이너를 사용하면 요청마다 새로운 객체를 생성하는 문제가 있음
  - 메모리 낭비
- 객체를 하나만 생성하고 공유하도록 설계하면 됨

### 구성 요소

- `private static final` 로 자기 자신을 객체 인스턴스화
- 그 객체 인스턴스를 반환하는 public 메서드
  - 항상 같은 인스턴스를 반환
- 생성자를 private으로 처리

### 문제점

- 구현 코드가 많음
  - 객체 인스턴스 생성, 반환 메서드, private 생성자
- 클라이언트가 구체 클래스에 의존
  - DIP 위반
  - OCP 위반 가능성 높아짐
- 테스트하기 어려움
- 내부 속성 변경/초기화 어려움
- 자식 클래스 만들기 어려움

#### 결론적으로

- 유연성이 떨어짐
- 안티패턴으로 불리기도 함

### Spring, singleton container

- spring의 싱글톤 container을 사용하면 모든 객체를 자동으로 싱글톤으로 관리해줌
- 스프링 컨테이너는 싱글톤 패턴의 문제점을 해결하는 동시에 객체 인스턴스를 싱글톤으로 관리
- 싱글톤 패턴 관련 코드, DIP, OCP, 테스트, private 생성자로부터 자유로움

### 주의점

객체 인스턴스를 하나만 생성해 공유하는 싱글톤 방식은 상태를 stateful하게 설계하면 안 됨
  - 특정 클라이언트에 의존적인 필드
  - 특정 클라이언트가 값을 변경할 수 있는 필드
  - 가급적 read-only, stateless
  - 필드 대신 공유되지 않는 지역변수, 파라미터, ThreadLocal 등 사용

## @Configuration

### 싱글톤의 법칙 위반?

싱글톤의 특징에 그런 게 있었다
- 객체 인스턴스 2개 이상 호출 불가

그런데 실제 `AppConfig.java` 코드를 보면 

```
@Bean
    public MemoryMemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }

@Bean
    public MemberService memberService(){
        return new MemberServiceImpl(getMemberRepository());
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(getMemberRepository(), getDiscountPolicy());
    }
```

- `@Bean memberService`와 `@Bean orderService` 둘 다 `getMemberRepository()`를 호출하고 있다
- 이때 `getMemberRepository()` 의 코드인 `new MemoryMemberRepository()`를 통해 객체 인스턴스를 2개 이상 호출하는 것 아닌가?

### 진실

`ConfigureSingletonTest.java` 에서 정말 `getMemberRepository`가 두 번 호출되는지 확인해 보았다.

```
MemberRepository memberServiceRepository = memberService.getMemberRepository();
MemberRepository orderServiceRepository = orderService.getMemberRepository();

assertThat(memberServiceRepository).isSameAs(orderServiceRepository);
```

- `MemberService`와 `OrderService`가 호출하는 `getMemberRepository()` 통해 호출되는 객체 인스턴스 `new MemoryMemberRepository()`는 항상 같은 객체이다

#### 에러

`MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);` 에서 `NoSuchBeanDefinitionException` 예외가 발생한다

#### 결론

- `memberRepository` 인스턴스는 공유되고 있다
- 메서드의 호출을 확인하면 `memberRepository()` 메서드는 `memberService()`의 호출 이후에 한 번만 호출되고 있다

### 바이트코드 조작

- 스프링 컨테이너는 싱글톤 레지스트리이므로 빈이 싱글톤이 되도록 보장
- 자바 코드를 가공하지는 않고 클래스의 바이트코드를 조작하는 라이브러리를 사용

#### AppConfig 의 클래스

`AppConfig`의 클래스를 조회하면 `AppConfig$$SpringCGLIB$$0`임을 확인할 수 있다.

- 순수한 클래스라면 `AppConfig`여야 함
- 스프링이 `CGLIB`라는 바이트코드 조작 라이브러리를 사용해 `AppConfig`를 상속받은 임의의 클래스를 만들고 그 클래스를 스프링 빈으로 등록한 것
- 이 임의의 클래스가 싱글톤을 보장하도록 함
- 이 임의의 클래스는 이미 스프링 컨테이너에 등록된 객체 인스턴스는 찾아서 반환하고, 그렇지 않다면 생성 후 스프링 컨테이너에 등록하고 반환함
  - 따라서 싱글톤이 보장됨

#### @Configuration을 사용하지 않으면?

- 순수한 클래스인 `AppConfig` 으로 빈 등록됨
- `memberRepository`는 여러 번 호출돼 여러 개 생성됨