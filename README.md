# hello-spring - 스프링 입문
## 2021-03-08 진척
* 환경설정
  * OS : Win10
  * Java: 11
  * 빌드툴 : Gradle 
  * 스프링 : Spring Boot 2.4.3 (스프링 이니셜라이저를 이용한 프로젝트 생성)
  * IDE : IntelliJ IDEA Ultimate
  * 기타 라이브러리 : Lombok
  * DB : 미정

* 강의시청-연습
  * 환경설정 (IDE) 및 프로젝트 생성 (스프링부트, gradle, thymeleaf, java11)
  * 최초 빌드 및 실행
  * 컨트롤러 추가해보기
    * @Controller
    * @GetMapping
  * 동적 컨텐츠 연습 : thymeleaf 템플릿 사용해보기 
    * @RequestParam
    * View로 Model 전달하여 데이터 표시
  * 동적 컨텐츠 연습 : API
    * @ResponseBody
    * 단순 데이터인 경우, 오브젝트인 경우
  * 백엔드 -회원관리- 간단예제 실습
    * 서비스 / 리포지토리 / 도메인 의 구분
      * 리포지토리(Repository) -> DAO역할. DB에 접근하여 데이터 관리해주는 역할
      * 도메인(Domain) -> VO역할(read only경우)(또는 DTO. getter setter모두 가진경우). 비즈니스 로직을 가지지 않은 순수한 데이터의 객체
      * 서비스(Service) -> 비지니스 로직의 구현
    * View는 추후에 연습
    * 간단한 테스트 케이스의 작성
    * 컴포넌트 스캔과 자동 의존관계 설정
     * 스프링빈? 스프링이 관리하는 객체 
     * @Component 어노테이션이 있으면 스프링 빈으로 자동 등록 됨
     * @Controller, @Service, @Repository 어노테이션은 내부에 @Component가 포함되어있다
    * 자바코드로 직접 스프링빈 등록
     * @Configuration
     * @Bean  
