package hello.hellowspring.repository;


import hello.hellowspring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/* 테스트 케이스 작성 */

/*
* 클레스 (MemoryMemberRepositoryTest)를 한 번에 테스트 실행할 수 있다
* but! 테스트메소드의 동작 순서는 보장 안 됨!
* 이전의 테스트 메소드가 다음의 테스트 메소드에 영향을 미치는 경우가 왕왕있으므로
* 테스트메소드가 끝나면 데이터를 클리어 해주자
* --> 테스트는 서로 의존관계 없이 설계가 되어야 한다 -> 하나의 테스트가 끝날 때 마다 저장소, 공용데이터 등을 초기화
*
* */


class MemoryMemberRepositoryTest {
	// 테스트 대항 객체를 만들고
	MemoryMemberRepository repository = new MemoryMemberRepository();


	@AfterEach // 하나의 테스트메소드가 끝날때마다 실행되는 콜백 메소드
	public void afterEach() {
		repository.clearStore(); // store 초기화 메소드 실행
	}


	/*테스트할 기능*/
	@Test
	public void save() { //save메서드 테스트
		//Member에
		Member member = new Member();

		//유저이름을 설정해주고
		member.setName("spring");

		//저장기능을 통해 member에 넣어준다 -> 테스트 할 기능
		repository.save(member);

		//Member의 save메서드 -유저이름(유저가 설정), id(시스템이 0부터 1씩 증가시킴) 둘을 해시멥에 put-
		//결과를 확인하기위해
		// Member의 findById메서드 통해서 해시맵에 저장된 결과를 가져옴
		Member result = repository.findById(member.getId()).get();

		/* 테스트결과 확인 방법 */
		System.out.printf("result = " + (result == member)); // 방법1. 콘솔창에 하나하나 출력해보기
		Assertions.assertThat(member).isEqualTo(result);  //방법2. Assertions활용(assertj.core 의 것)
		// member는 result와 같다! 고 주장(assert) -> 같은게 맞으면 테스트 성공. 다르면 테스트실패 뜬다.

	}

	@Test
	public void findByName() { //findByName 메서드 동작확인
		// spring1 회원 등록
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);

		// spring2 회원 등록
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);

		Member result = repository.findByName("spring1").get();

		Assertions.assertThat(result).isEqualTo(member1); // 기대 : 성공
//		Assertions.assertThat(result).isEqualTo(member2); // 기대 : 테스트 실패
	}


	@Test
	public void findAll() {
		// spring1 회원
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);

		// spring2 회원 등록
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);

		List<Member> result = repository.findAll();

		Assertions.assertThat(result.size()).isEqualTo(2); // List의 사이즈 = 등록된 회원수

	}
}
