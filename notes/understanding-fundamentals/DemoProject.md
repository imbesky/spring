# Demo Project for understanding fundamentals of Spring

## Requirements

### Member
  
- Can join member and inquire information
- Two grades; standard and VIP
- Datas of members can be created locally or linked with external system (uncertain)

### Order and discounts

- Member can order products

### Discount

- Discount policy is based on the order
    - All VIP members always get 1,000won discount (changeable)
    - discount policy is very changeable; uncertain, even can be cancelled

### New discount policy

- set discount value as range

#### Problem

- violate DIP: depending on details and abstract both
  - abstract: `DiscountPolicy`
  - detail: `FixDiscountPolicy`, `RateDiscountPolicy`

- violate OCP: to change the implemented object, have to change client code
  - ex: changing `FixDiscountPolicy` into `RateDiscountPolicy`

#### Solution

- original: `private final DiscountPolicy discountPolicy = new RateDiscountPolicy();`
- correction: `private DiscountPolicy discountPolicy;`

- -> only depending on abstract, interface `DiscountPolicy`
- but there aren't any implementation(구현체); NullPointException occurs
- to solve this, someone should create object of DiscountPolicy and inject it

## Sequence

1. order
2. inquire member
3. apply discount
4. return result

## Manifesto for Agile Software Development

(web page)[https://agilemanifesto.org/iso/en/manifesto.html]

- Individuals and interactions over processes and tools
- Working software over comprehensive documentation
- Customer collaboration over contract negotiation
- Responding to change over following a plan

## Separation of Concern

One responsibility at a time

### AppConfig

responsible to configure application

- create object
- inject reference by using constructor

### DI

- 객체를 생성하고 그 참조값을 `Impl`이 생성되면 생성자로 전달
- `Impl`에서는 의존관계를 외부에서 주입해주는 것이라고 느낌; DI, Dependency Injection

### by using AppConfig

- only depends on abstract; interface
- 생성자를 통해 어떤 구현 객체가 들어올 지 알 수 없음
- 생성자를 통해 어떤 구현 객체를 주입할지는 오직 `AppConfig`에서 결정
- `Impl` 파일들은 의존관계에 대한 고민의 책임은 없고 실행에만 집중 가능