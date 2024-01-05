# Container & Bean

## Container

`ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);`

- `ApplicationContext` is spring container, an interface
- spring container can be created as XML based or annotation based java setting class
- `new AnnotationConfigApplicationContext(AppConfig.class)` is an implementation of the interface `ApplicationContext`

### Creation sequence

1. create spring container
    - should set composition information when creating
    - ex: AppConfig.class
2. register spring bean
    - spring container register spring bean at spring bean storage
    - use information about the class that is passed as a parameter(ex: AppConfig.class)
    - bean name is automatically set as the method name, but can be assigned by user
3. prepare to set dependency of spring bean
4. end to set dependency of spring bean
   - 설정 정보를 참고해서 DI

#### Additionally

- 스프링 빈을 생성하고 의존관계 주입하는 단계가 나누어져 있음
- 자바 코드로 스프링 빈을 등록하면 생성자를 호출하면서 의존관계 주입도 한 번에 처리됨(상기 설명은 이해를 위해 개념적으로 나누어진 것)

## Spring bean

`ApplicationContextInfo.java`

### Inquire spring bean

`ApplicationContextBasicFindTest`

1. `getBean(name, type)`
2. `getBean(type)`

#### Type duplication

`ApplicationContextSameTypeBeanFindTest.java`

- 타입으로 조회시 같은 타입의 스프링 빈이 두 개 이상인 경우 오류가 발생
- `getBeanOfType()` 사용시 해당 타입의 모든 빈 조회 가능

### Inheritance relationship

`ApplicationContextExtendsFindTest.java`

- 부모 타입으로 조회시 자식 타입도 조회됨
- `Object` 타입으로 조회시 모든 스프링 빈 조회

### BeanFactory and ApplicationContext

#### BeanFactory

root interface of spring container

- manage spring bean and inquire them
- `getBean()`

#### ApplicationContext

extends `BeanFactory`

- provide various additional functions
  - MessageSource
  - EnvironmentCapable
  - ApplicationEventPublisher
  - ResourceLoader

### Setting forms

1. java code
2. XML
3. and so forth(even can make a new one)

#### XML

- XML 사용시 컴파일 없이 빈 설정 정보를 변경할 수 있음
- `GenericApplicationContext`를 사용하면서 xml 설정 파일을 넘기면 됨

### Bean definition

- 스프링이 다양한 설정 형식을 지원할 수 있도록 하는 추상화
- 역할과 구현을 개념적으로 나눈 것
  - 자바 코드든, XML이든 읽어서 `BeanDefinition`으로 만든다

- 빈 설정 메타정보라고도 함
  - `@Bean`, `<bean>`당 각각 하나씩 메타 정보가 생성됨
- 스프링 컨테이너는 이 메타정보를 기반으로 스프링 빈 생성