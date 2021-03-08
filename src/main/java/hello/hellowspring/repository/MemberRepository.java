package hello.hellowspring.repository;

import hello.hellowspring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
	Member save(Member member); // 회원등록 기능

	/* Optional<TYPE>
	* java8부터 도입됨
	* null이 될 수 도 있는 객체를 감싸는, 래퍼클래스(Wrapper class)
	*
	* null 관련 문제
	* -> 런타임시 NPE(NullPointerException) 예외 발생가능
	* -> NPE를 방지하기 위한 null체크 로직이 추가로 필요하게 됨
	*
	* Optional로 감싸게 되면?
	* null을 직접 다루지 않아도 됨 -> 수고롭게 null 체크 불필요
	* 명시적으로 해당 변수가 null 일 수 있다는 가능성을 표현
	*  */
	Optional<Member> findById(Long id); // id로 회원찾기 기능

	Optional<Member> findByName(String name); // name으로 회원찾기 기능

	List<Member> findAll(); // 모든 회원 조회 기능
}
