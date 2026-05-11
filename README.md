# SPRING ADVANCED

Spring Boot 기반의 일정 관리 API 프로젝트입니다.  
인증/인가, AOP 로깅, ArgumentResolver, Validation, N+1 문제 해결, 테스트 코드 개선 등을 학습하고 적용하는 것을 목표로 진행했습니다.

## 🚀 STEP 0. 프로젝트 세팅 및 에러 분석

### 문제 상황

애플리케이션 실행 시 다양한 설정 오류로 인해 서버가 정상 실행되지 않았다.

### 해결 내용

- datasource 설정 수정
- JWT Secret Key 설정 수정
- 환경설정 누락 수정

### 학습 내용

- Spring Boot 실행 과정
- 환경설정(application.yml) 구조 이해
- Bean 생성 및 DI 흐름 이해

## 🚀 STEP 1. ArgumentResolver 구현

### 문제 상황

@Component만 등록하면 Resolver가 자동 동작할 것이라고 생각했지만,
컨트롤러에서 인증 사용자 객체가 정상 주입되지 않았다.

### 원인

ArgumentResolver는 Bean 등록만으로는 동작하지 않으며,
Spring MVC 흐름에 직접 등록이 필요했다.

### 해결

WebConfig에 Resolver를 등록하여 해결했다.

## 🚀 STEP 2. 코드 개선

### 1️⃣ Early Return 적용

#### 개선 전

중복 이메일이어도 password encoding이 먼저 수행되었다.

#### 개선 후

이메일 중복 검사를 먼저 수행하여 불필요한 연산을 제거했다.

#### 학습 내용

Early Return 패턴
불필요한 로직 실행 방지

### 2️⃣ 불필요한 if-else 제거

#### 개선 내용

불필요한 else 블록 제거를 통해 코드 가독성을 개선했다.

### 3️⃣ Validation 개선

#### 개선 전

비밀번호 검증을 Service 내부에서 직접 처리했다.

#### 개선 후

Bean Validation 기반 DTO 검증으로 변경했다.

#### 적용 기술

@Valid
@Pattern
@Size

## 🚀 STEP 3. N+1 문제 해결

### 문제 상황

Todo 목록 조회 시 연관 엔티티를 반복 조회하면서 N+1 문제가 발생한다.

### 해결 내용

기존 fetch join 기반 JPQL을 제거하고,
`@EntityGraph` 기반 조회 방식으로 변경했다.

### 적용 기술

JPA
EntityGraph
학습 내용
N+1 문제 발생 원리
연관 엔티티 조회 최적화

## 🚀 STEP 4. 테스트 코드 개선

### 구현 내용

PasswordEncoder 테스트 수정
예외 테스트 수정
테스트 메서드명 개선
서비스 로직 변경에 따른 테스트 수정

### 학습 내용

테스트 코드 유지보수
예외 상황 테스트 작성
테스트와 실제 로직의 일관성 유지

## 🚀 STEP 5. API 로깅

### Interceptor 구현

구현 내용
관리자 권한 검증
요청 URL 및 요청 시간 로깅

### AOP 구현

#### 구현 내용

요청 데이터 로깅
응답 데이터 로깅
실행 시간 측정

#### 적용 기술

- `@Aspect`
- `@Around`

#### 핵심 코드

```java
@Around("@annotation(org.example.expert.domain.common.annotation.AdminAspect)")
```
