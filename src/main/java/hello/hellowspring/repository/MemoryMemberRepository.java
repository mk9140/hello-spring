package hello.hellowspring.repository;

import hello.hellowspring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;



/* 인터페이스 MemberRepository의 구현체 */

@Repository //컴포넌트스캔방법! 스프링이 관리할수 있도록(빈) 어노테이션 설정
// @Component 라고 해도 됨. -> @Repository어노테이션에는 내부에 이미 @Component가 등록되어있음
public class MemoryMemberRepository implements MemberRepository{

	//실무에서는 동시성 문제가 있을 수 있으므로
	//공유되는 변수의 경우에는 ConcurrentHashMap 을 사용,
	//변수 sequencee도 마찬가지로 thread-safe로 구현된 AtomicLong 타입이 있지만
	//예제이므로 단순하게 위해 보통의 HashMap, long사용
	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L;


	@Override
	public Member save(Member member) {
		//name은 유저가 회원가입시 입력한다.

		member.setId(++sequence); //id는 시스템이 정하는 값
		store.put(member.getId(), member); // HashMap의 변수 store에 저장
		return member; // 저장결과 반환

	}

	@Override
	public Optional<Member> findById(Long id) {
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<Member> findByName(String name) {
		/*
		* stream 이란?
		* java8에서 추가된 기능. 함수형 인터페이스인 람다(lambda) 활용할 수 있음.
		* 이전에는 배열이나 컬렉션을 반복문을 통해 요소를 하나씩 꺼내 if문 등으로
		* 조작했었던 것을 더 간결하게 작성 가능하게 됨
		* 데이터를 병렬로 처리 가능
		* */

		return store.values().stream() // for문이라고 보면 됨
				// filter -> 조건에 의한 선택. if문
				.filter(member -> member.getName().equals(name)) //java8의 람다식 표현
				// findAny -> 찾은 순서에 관계 없이 일치하는 값 하나를 반환
				.findAny();

	}

	@Override
	public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}


	// 저장된 회원 다 지우기
	// 테스트위해 @AfterEach 에서 사용하기위해 작성
	public void clearStore() {
		store.clear();
	}
}
