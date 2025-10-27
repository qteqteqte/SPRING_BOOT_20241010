# 2025-2학기 자바웹프로그래밍(2)
## 개요
<b>2025-2 자바웹프로그래밍(2) 수업 프로젝트입니다.</b>
- 기간: 2025/9/3~(진행중), 매주 수요일
- 2025/10/27, 중간점검
- [프로젝트 메인 주소](https://github.com/qteqteqte/SPRING_BOOT_20241010)

# 중간 (2주차~7주차)
## 2주차
**개발환경 준비 (스프링부트 설치) 및 스프링부트**
---
- 백엔드와 스프링부트
- 스프링부트 동작 과정
- thymeleaf, URL 맵핑과 컨트롤러

### 백엔드와 스프링부트
- <b>백엔드</b>
    - 서버 기반 정보 요청/응답 (데이터 처리를 서버에 의존)
    - 웹/애플리케이션 구현 시 사용
    - 기존 라이브러리의 한계 → <b>프레임워크 사용</b>
      - 높은 생산성과 유지 보수의 필요성으로 독립적으로 작성하는 라이브러리 대신 공통 표준 모듈인 프레임워크 사용
      - Node.js, Spring, Django 등
     
- <b>스프링부트 (Spring Boot)</b>
    - java(자바) 기반의 웹 프레임워크
      - 기존 스프링(Spring) 의 단점을 상당 개선
    - 자동 구성(Auto-configuration)을 통해 설정 간소화 (자동 환경설정, 자동 빌드 및 리로드)
    - 내장 서버 포함/제공
      - 웹/애플리케이션을 독립 실행이 가능한 .jar 파일 형태로 만들고 실행 가능

- <b>자바 클래스</b>
```java
public class HelloWorldApp { //'HelloWorldApp' 라는 클래스를 '공개적' 으로 선언
  public static void main(String[] args) { //메인 메서드 호출, 프로그램이 '시작되는 지점' 설정
    System.out.println("Hello World!"); //프로그램이 '실제로 실행하는' 코드 설정
  }
}
```

- <b>Spring 프로젝트 폴더 구성(Maven)</b>
  - Maven 표준 디렉터리 레이아웃을 따름
  ```
  Project Root/
  ├── pom.xml
  └── src/
    ├── main/
    │   ├── java/
    │   ├── resources/
    │   └── webapp/ (웹 프로젝트인 경우)
    └── test/
        ├── java/
        └── resources/
  ```
  - 프로젝트 루트 (Project Root)
  
  |파일/폴더|설명|
  |---|------|
  |`pom.xml`|Maven 프로젝트의 핵심 설정 파일<br>프로젝트 정보, 라이브러리(의존성, `dependencies`), 빌드 설정, 플러그인 등이 정의됨|
  |`target/`|빌드 결과물(컴파일된 클래스, JAR/WAR 파일 등)이 저장되는 폴더<br>Maven이 자동으로 생성 및 관리함|

  - `src/main` (주요 소스 및 리소스)
  
  |폴더|설명|
  |---|------|
  |`src/main/java`|자바 소스 코드 파일(*.java)이 위치함<br>애플리케이션의 핵심 로직(컨트롤러, 서비스, 모델 등)이 **패키지 구조**로 분류되어 저장 (`DemoApplication.java` 위치)|
  |`src/main/resources`|자바 코드 외의 리소스 파일이 위치함<br>- 설정 파일: `application.properties` 또는 `application.yml` (Spring Boot), XML 설정 파일 등<br>- 템플릿: 뷰 템플릿 (예: Thymeleaf의 경우 `templates/` 폴더)<br>- 정적 리소스: CSS, JavaScript, 이미지 파일(일반적으로 `static/` 폴더)|
  |`src/main/webapp`|웹 애플리케이션의 루트에 해당하며, WAR 파일로 패키징할 때 사용|

- `DemoApplication.java`
```java
package com.example.demo; // 현재 폴더 위치

import org.springframework.boot.SpringApplication; // 스프링 핵심 클래스
Import org.springframework.boot.autoconfigure.SpringBootApplication; // 자동 설정 기능 활성화

@SpringBootApplication // 어노테이션(스프링 부트 APP 명시, 하위 다양한 설정을 자동 등록)
public class DemoApplication { // 클래스 이름
  public static void main(String[] args) { // 메인 메서드(프로그램 시작점)
    SpringApplication.run(DemoApplication.class, args); // run 메서드로 실행
  }
}
```
- <b>어노테이션 (Annotations)</b>
  - 코드 상단에 특정 역할을 명시
  - 어노테이션 목록
  
  |어노테이션|설명|
  |---|------|
  |`@SpringBootApplication`|Spring Boot Application 으로 설정|
  |`@Controller`|View를 제공하는 Controller 설정|
  |`@RestController`|REST API를 제공하는 Controller 설정|
  |`@RequestMapping`|URL 주소를 맵핑|
  |`@GetMapping`|(Http GetMethod) URL 주소를 맵핑|
  |`@PostMapping`|(Http PostMethod) URL 주소를 맵핑|
  |`@PutMapping`|(Http PutMethod) URL 주소를 맵핑|
  |`@DelateMapping`|(Http DeleteMethod) URL 주소를 맵핑|
  |`@RequestParam`|URL Query Parameter 맵핑|
  |`@RequestBody`|Http Body를 Parsing 맵핑|
  |`@Valid`|POJO 자바 클래스 검증|

### 스프링부트 동작 과정
- <b>웹 애플리케이션 서버(WAS)</b>
  - NGINX, <b>APACHE</b>, IIS 등
