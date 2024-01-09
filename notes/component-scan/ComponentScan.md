# Component scan

설정 정보가 없어도 스프링 빈을 자동으로 등록하는 스프링의 기능

- 등록해야 할 스프링 빈이 많아지면 설정 정보가 커지고 반복적인 작업을 해야 함

## 방법 및 기능

`@ComponentScan`사용

- 스프링 빈 등록에서 제외할 것 지정 가능
- `@Component` 애노테이션이 붙은 클래스를 스캔해 스프링 빈으로 등록

### 스프링 빈의 이름

- 스프링 빈의 기본 이름은 클래스명의 맨 앞글자를 소문자로 바꾼 것으로 지정됨
- 빈 이름을 직접 지정하려면 `@Component("원하는 이름")`

### 문제점

기존의 `AppConfig`에서는 의존관계 주입을 명시하고 수행했었는데 `AutoAppConfig`는 그런 기능이 없다

## 의존관계 자동 주입

constructor에 `@Autowired`를 붙이면 의존관계가 자동으로 주입됨

- 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아 주입함
- 기본 조회 전략은 타입이 같은 빈을 찾아 주입하는 것
- `getBean(클래스.class)` 와 유사

## 탐색 위치 지정

1. 패키지
   - `basePackages = "패키지 경로"`
   - 해당 패키지를 포함해 하위 패키지를 모두 탐색
   - 쉼표를 이용해 여러 시작 위치 지정 가능

2. 클래스
   - `basePackageClasses`
   - 해당 클래스의 패키지를 탐색 시작 위치로 지정

3. 지정 안함(default)
    - 애노테이션이 붙은 설정 정보 클래스의 패키지가 시작 위치가 됨
    - 프로젝트 최상단에 설정 정보 클래스를 두는 경우 이 방법을 이용

### @SpringBootApplication

`@ComponentScan`이 붙어 있다

## 기본 대상

`@Component` 애노테이션이 붙어 있는 것들

1. `@Configuration`; 설정 정보로 인식, 스프링 빈이 싱글톤을 유지하도록 추가 처리
2. `@Controller`; MVC 컨트롤러로 인식
3. `@Service`
4. `@Repository`; 스프링 데이터 계층으로 인식, 데이터 계층의 예외를 스프링 예외로 변환

- 어노테이션에 다른 어노테이션이 붙어 있는 것을 인식하는 것은 스프링의 기능
- 뒤에 붙은 기능은 해당 어노테이션에 대해 스프링이 수행하는 부가 기능

## 필터

- `includeFilters`: 컴포넌트 스캔 대상을 추가로 지정
- `excludeFilters`: 컴포넌트 스캔에서 제외할 대상 지정

`includeFilters = @Filter(type = FilterType.필터타입, classes = 클래스.class)`

### FilterType 옵션

- ANNOTATION: 기본값(생략 가능), 어노테이션 인식 및 동작
- ASSIGNABLE_TYPE: 지정한 타입과 자식 타입 인식
- ASPECTJ: AspectJ 패턴 사용
- REGEX: 정규 표현식
- CUSTOM: `TypeFilter` 인터페이스 구현해 처리

## 중복 등록과 충돌

스프링 빈 이름이 중복되는 경우

### 자동 빈 등록 vs 자동 빈 등록

`ConflictBeanDefinitionException` 발생

### 수동 빈 등록 vs 자동 빈 등록

수동 빈 등록이 우선, 수동 빈이 자동 빈을 오버라이딩

- 최근 스프링 부트에서는 오류가 기본값
