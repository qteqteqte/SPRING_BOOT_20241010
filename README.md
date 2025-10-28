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
      |`src/main/java`|자바 소스 코드 파일(*.java)이 위치함<br>애플리케이션의 핵심 로직 (컨트롤러, 서비스, 모델 등)이 **패키지 구조**로 분류되어 저장<br>(`DemoApplication.java` 위치)|
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
  - 동적 개발 (DB, 서버 언어 확장)

- <b>스프링부트 프레임워크</b>
    - APACHE 웹 서버 + 톰캣(TOMCAT)
        - 내부 객체의 생성부터 초기화, 호출, 종료 관리
        - 멀티 스레딩 지원 등
    - <b>동작 과정</b>
        - (1) 클라이언트 요청(URI 매핑)
        - (2) 어댑터 조회 및 컨트롤러 호출
        - (3) 응답 반환(응답)
        - (4) 뷰 리졸버 실행(뷰 렌더링)
        - (5) 화면 리턴
    - <b>서블릿(DispatcherServlet) 내부 동작</b>
        - 프론트 컨트롤러 역할(페이지 요청/응답 처리)
            - (1) html 페이지 요청
            - (2) uri 읽은 후 .java 파일 → .class 변환 * URL과 URI는 다르다
            - (3) html 파일 생성 후 페이지 응답
    - 기본 MVC 디자인 패턴 적용
        - M(MODEL) : 페이지 정보 저장
        - V(VIEW) : 페이지 화면 구성
            - 템플릿 엔진 : jsp, thymeleaf 등
        - C(CONTROLLER) : 페이지 흐름 제어

### thymeleaf, URL 맵핑과 컨트롤러
- <b>Thymeleaf</b>
    - HTML 기반 템플릿 엔진
    - MVC에서 화면 출력 : 뷰(V) 역할
        - <b>DispatcherServlet</b>을 통해 동작
        - TemplateEngine 내장
    - 주요 기능
        - 데이터 바인딩, 연산, 객체 호출 등
        - 템플릿 Fragment (Spring-Boot-Starter-thymeleaf)
        - 전용 제어문 (반복/조건 등)
    - 모든 링크는 컨트롤러를 통해 동작
        - URL 요청 이후 컨트롤러는 viewName 리턴
        - `main/resources/templates` 에 위치
        - 기본 템플릿은 `pom.xml` 에 위치
     
    - 기본 템플릿 엔진
      ```xml
      <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
      </dependency>
      ```
      
    - <b>Thymeleaf 사용법 (`.html`)</b>
        - 선언: `<html lang="en" xmlns:th="http://www.thymeleaf.org">`
        - 주석: `<!--/* This code will be removed at thymeleaf parsing time! */-->`
        - 텍스트 출력: `<span th:text=${data}">`

- <b>URL 맵핑과 컨트롤러</b>
    - `java/com/example/demo` 에 컨트롤러 파일 생성

      ```java
      package com.example.demo;
      import org.springframework.stereotype.Controller;
      import org.springframework.ui.Model;
      import org.springframework.web.bind.annotation.GetMapping;

      @Controller // 컨트롤러 어노테이션 명시
      Public class DemoController {
          @GetMapping("/hello") // 전송 방식 GET
          public String hello(Model model) {
              model.addAttribute("data", " 방갑습니다."); // model 설정
              return "hello"; // hello.html 연결
          }
      }
      ``` 
    - 맵핑하기 (컨트롤러↔템플릿)
      ```html
      <!DOCTYPE html>
      <html xmlns:th="http://www.thymeleaf.org">
      <head>
          <meta charset="UTF-8">
          <title>Hello 페이지</title>
      </head>
      <body>
          <h1>안녕하세요!</h1>
          <p th:text="${data}"></p>
          <a href="/hello">홈페이지 메인</a>
      </body>
      </html>
      ```

## 3주차
**개인 포트폴리오 작성 시작**
---
- 자바 VS C# 비교
- 상세 페이지 생성

### 자바 VS C# 비교
- <b>자바 vs C#</b>

    |구분|자바|C#|
    |---|------|------|
    |개발환경|JVM (Java Virtual Machine) 기반|.NET Framework 또는 .NET Core 기반|
    |주요 용도|안드로이드 앱 개발, 서버 사이드 개발, 엔터프라이즈 시스템 개발|윈도우 데스크톱 앱 개발, 게임 개발, 웹 개발, 클라우드 서비스 개발|
    |학습 난이도|중간|중간|
    |장점|<b>플랫폼 독립성</b>, 강력한 객체지향 기능, 방대한 라이브러리와 커뮤니티, 안드로이드 개발의 표준 언어|<b>윈도우 환경과의 높은 호환성</b>, MS Visual Studio라는 강력한 IDE 제공, 게임 개발에 특화된 유니티 엔진과의 연동|
    |단점|JVM 성능 오버헤드, verbose한 문법, 변화하는 자바 버전에 대한 학습 부담|.NET Framework의 복잡성, 윈도우 환경에 종속적인 부분 (단, .NET Core 도입 이후 개선됨)|
    |특징|안정성, 확장성, 대규모 엔터프라이즈 시스템 개발에 적합|생산성, 윈도우 환경에서 다양한 애플리케이션 개발에 활용|
    |분야|모든 E-상거래 및 금융권 거래, CMS, 공공(한국), 클라우드 서버 연동 및 분산 처리|게임(UNITY), 비즈니스 시스템, 데스크톱(WINDOW), 시스템 통합(SI) 및 유지보수(SM) 등|

### 상세 페이지 생성
- 상세 페이지 생성
    - 메인 페이지에 하이퍼링크 추가 `<a class="btn btn-primary py-3 px-5 mt-3" href="/about_detailed" target="_blank">상세 소개</a>`
    - 맵핑 템플릿 `about_detailed.html` 생성
    -  `DemoController.java` 에 맵핑 등록
  
        ```java
       @GetMapping("/about_detailed")
       public String about() {
           return "about_detailed";
       }
       ```
## 4주차
**DB 연동 및 테스트**
---
- 데이터베이스와 MySQL
- Thymeleaf 복습
- 데이터베이스 연동
- 구조 변경 (클래스 추가)

### 데이터베이스와 MySQL
- 데이터베이스 트렌드
    - 오픈소스 DBMS의 인기가 높음
    - 그러나 RDB (관계형 데이터베이스)인 ORACLE, MySQL이 여전히 대세

- <b>MySQL</b>
    - 오픈 소스 기반 RDB였으나, 2010년 오라클 인수 후 무료/상용화 버전이 분리
    - 최신 8.X 버전부터 NoSQL을 지원
    - <b>스프링 부트 3 환경</b>: JPA를 필수적으로 사용하며, MYSQL 8 버전 이상이 요구

### Thymeleaf 복습
- Thymeleaf 출력
  ```java
  @GetMapping("/test1")
  public String thymeleaf_test1(Model model) {
      model.addAttribute("data1", "<h2> 방갑습니다 </h2>");
      model.addAttribute("data2", "태그의 속성 값");
      model.addAttribute("link", 01);
      model.addAttribute("name", "홍길동");
      model.addAttribute("para1", "001");
      model.addAttribute("para2", 002);
      return "thymeleaf_test1";
  }
  ```

  ```html
  <!DOCTYPE html>
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <meta charset="UTF-8">
      <title>테스트</title>
  </head>
  <body>
      <span th:utext="${data1}">message</span> <!-- 텍스트 출력(html 태그 인식) -->
      <input type="text" th:value="${data2}">
      <input type="checkbox" th:field="*{data2}" th:value="true"> 동의합니다. <!-- Input/select 등 태그의 값 지정 -->
      <p><a th:href="@{/posts/(id=${link})}">글 상세보기</a></p> <!-- 태그의 속성 값 여러 개 지정(링크는 @) -->
      <div th:with="first=${name}">
          <p>리스트에서 처음 사람의 이름 = <span th:text="${first}"></span></p>
      <div> <!-- 지역변수(태그 내에서만 동작) -->
      <a th:href="@{/products/detail/(productId=${para1}, productId=${para2})}">상품 상세보기</a>
      <!-- 링크에 파라미터 추가하기(&를 ,로 표현) -->
  </body>
  </html>
  ```

### 데이터베이스 연동
- <b>JPA(Java Persistence API)</b>
    - <b>ORM</b> : OOP 객체(클래스) 맵핑 역할
    - 공용 인터페이스 생성
    - 데이터 제어와 영속성(객체를 메모리에서 관리)을 제공

- <b>스프링부트에서 DB 연동하기</b>
    - `application.properties` 파일을 염
    - 데이터베이스 계정 정보 추가 (MySQL 서버 기본 포트: 3306)
      ```
      spring.datasource.url=jdbc:mysql://localhost:3306/spring?serverTimezone=Asia/Seoul
      spring.datasource.username=root
      spring.datasource.password=패스워드
      spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
      spring.jpa.hibernate.ddl-auto=update
      spring.jpa.show-sql=true
      ```

### 구조 변경 (클래스 추가)
- 기존의 MVC 구조 세분화
    - M(Model), V(View/Templetes), C(Controller) 중 <b>M에서 `Service`, `Repository` 추가</b>
    
        |계층|패키지/폴더|역할|
        |---|------|------|
        |Model (M)|`model/domain`|DB 테이블 구조 (Entity 객체)|
        |View (V)|`templates`|화면 출력 (Thymeleaf 활용)|
        |Controller (C)|`controller`|사용자 요청/응답 흐름 제어/반환|
        |Repository|`model/repository`|DB 연결/접근 및 CRUD 기능 제공|
        |Service|`model/service`|중간 로직 처리, 데이터 가공|
      
    - <b>엔티티 클래스 (Entity)</b> (영속 계층)
        - 데이터베이스 테이블 작성
            ```java
            package com.example.demo.model.domain;
            import jakarta.persistence.*;
            import lombok.Data;
    
            @Entity // TestDB라는 객체와 DB 테이블을 매핑. JPA가 관리
            @Table(name = "testdb") // 테이블 이름은 testdb
            @Data // set/get/tostring 등 필수 어노테이션 자동 생성
    
            public class TestDB {
                @Id // 해당 변수가 PK
                @GeneratedValue(strategy = GenerationType.IDENTITY) // 값이 없어도 자동으로 할당
                private Long id;
                @Column(nullable = true) // 테이블의 컬럼 설정 값을 명시
                private String name;
            }
            ```
            
    - <b>리포지토리 클래스 (Repository)</b> (영속 계층)
        - 데이터베이스 제어 기능 작성
            ```java
            package com.example.demo.model.repository;
            import org.springframework.data.jpa.repository.JpaRepository; // JPA 필수 등록
            import org.springframework.stereotype.Repository; // 빈 등록
            import com.example.demo.model.domain.TestDB; // 도메인 연동
    
            @Repository // 리포지토리 등록
            public interface TestRepository extends JpaRepository<TestDB, Long> {
            // Find all TestDB entities by a name
                TestDB findByName(String name);
            }
            ```
    - <b>서비스 클래스 (Service)</b> (서비스 계층)
        - 주요 기능 작성
            ```java
            package com.example.demo.model.service;
            import org.springframework.beans.factory.annotation.Autowired;
            import org.springframework.stereotype.Service;
            import com.example.demo.model.domain.TestDB;
            import com.example.demo.model.repository.TestRepository;
            import lombok.RequiredArgsConstructor;

            @Service // 서비스 등록, 스프링 내부 자동 등록됨
            @RequiredArgsConstructor // 생성자 생성
            public class TestService {
                @Autowired // 객체 의존성 주입 DI(컨테이너 내부 등록)
                private TestRepository testRepository;
                public TestDB findByName(String name) { // 이름 찾기
                return (TestDB) testRepository.findByName(name);
                }
            }
            ```
    - <b>컨트롤러 클래스 (Controller)</b> (제어 계층)
        - 사용자 요청/응답 기능 작성
        - 기존의 `DemoController.java` 에 추가
            ```java
            import com.example.demo.model.service.Testservice; // 최상단 서비스 클래스 연동 추가
            // 클래스 하단 작성

            @Autowired
            Testservice testService; // DemoController 클래스 아래 객체 생성

            // 하단에 맵핑 이어서 추가
            @GetMapping("/testdb")
            public String getAllTestDBs(Model model) {
                TestDB test = testService.findByName("홍길동");
                model.addAttribute("data4", test);
                System.out.println("데이터 출력 디버그 : " + test);
                return "testdb";
            }
            ```
    - <b>뷰 클래스 (View)</b>
        - `Templetes` 와 동일 기능
        - 프론트
     
## 5주차
**블로그 게시판 작성(1)**
---
- Rest API
- HTTP 메서드 - CRUD
- 블로그 게시판 (조회) 구현
- 블로그 게시판 (글쓰기) 구현

### Rest API
- <b>Rest API</b>
    - Representational State Transfer 기반 API
    - 통신에 자원을 통해 식별
        - 자원: 객체, 데이터 등을 의미하며 `URI(엔드포인트)`로 표현
        - 행위: `HTTP` 메서드를 사용하며, 자원에 대한 행위를 나타냄
        - 표현: `JSON` 또는 `XML` 형식이며, 행위의 구체적 내용을 담는 페이로드를 의미
    - REST API를 사용하는 이유: 확장성(무상태성), 유연성(모든 플랫폼), 운용성(언어 상관 없음)

### HTTP 메서드 - CRUD
- <b>CRUD Operation 동작(주요 메소드 기능)</b>
    - HTTP Method 표준
        - Create: 데이터 생성 (`POST`) Ex) `/movies`에 `POST`로 새 영화 생성
        - Read: 데이터 조회 (`GET`) Ex) `/movies`로 목록 조회, `/movies/:id`로 특정 영화 조회
        - Update: 데이터 수정 (`PUT`, `PATCH`) Ex) `/movies`에 PUT으로 기존 영화 수정
        - Delete: 데이터 삭제 (`DELETE`) Ex) `/movies`에 DELETE로 기존 영화 삭제
    - URI 설계 원칙
        - 명사(동사 X)
        - 경로는 소문자
        - 하이픈(언더바 X)
        - 상태 코드 포함
        - 파일은 확장자 X

## 6주차
**블로그 게시판 작성(2)**
---
- ORM, ORM 매핑과 영속성
- 블로그 게시판 (수정) 구현
- 블로그 게시판 (삭제) 구현

### ORM, ORM 매핑과 영속성
- <b>ORM(Object-Relational Mapping)</b>
    - 객체와 관계형 데이터를 매핑하기 위한 기술
    - 복잡한 SQL 쿼리를 직접 개발할 필요가 줄어
    - 구조: Spring Data JPA(JPA) <-> Hibernate <-> JDBC <-> DB
    - 객체, DB는 ORM 매핑을 통해 연결
        - <b>EntityManager</b>가 SQL 생명주기를 관리
