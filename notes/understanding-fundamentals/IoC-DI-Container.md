## IoC

Inversion of Control

- 제어의 흐름을 직접 제어하는 것이 아니라 외부에서 관리

### In demo project

- at existing programs, client object itself create and run object that implements server
- Config 파일의 생성 이후에는 구현 객체는 자신의 로직을 실행하는 역할만 담당, 프로그램 제어의 흐름은 Config가 담당

## Framework v.s Library

### Framework

- control the written codes
- run those codes
- ex: JUnit

### Library

- the written codes are responsible to control flow

## DI

Dependency Injection

### Static dependency relationship

- can know static dependency relationship only with import code of the class, not running the application
- but cannot know the object that is really injected into the class

### Dynamic dependency relationship

- dependency relationship that the object instance reference is connected at runtime, when the application starts
- 의존관계 주입: 런타임에 외부에서 실제 구현 객체를 생성, 클라이언트에 전달해 클라이언트와 서버의 실제 의존 관계가 연결되는 것
- create object instance and hand over that reference value to connect

#### Using DI

- can change type of the instance that client calls without changing the client code
- can change dynamic dependency relationship without changing static dependency relationship

## Container

IoC container, DI container

- the one that create and manage object, connect dependency relationship
- ex: `AppConfig.java`
- also called as assembler or object factory