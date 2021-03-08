package hello.hellowspring.service;

import hello.hellowspring.domain.Member;
import hello.hellowspring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


/*TIP!
* 테스트 케이스 작성시
* 기본적으로는
* given - when - then 패턴을 기억하자.
* 무언가가 주어졌고 - 실행 했을 때 - 예상결과
* */

/*TIP!
* 테스트용 메서드의 이름은 꼭 영어가 아니어도 동작한다
* but, intelliJ의 콘솔창들에서는 문자깨짐(文字化け)되어버리니
* intelliJ의 vmoptions 파일에 다음과 같은 코드를 한 줄 추가해두고 재기동하자.
* -Dfile.encoding=UTF-8
* */
class MemberServiceTest {
	// 테스트 대상 오브젝트 생성
	// MemberService memberService = new MemberService(memberRepository);
	// 각 테스트 메서드 동작후 데이터 클리어 해줘야함.
	// 그런데 MemberService에는 클리어용 메서드가 없고
	// MemoryMemberRepository 있었다
	// MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository(); 해서
	// memoryMemberRepository.clearStore()를 사용해도 되는가...?
	// MemberService에서도 MemoryMemberRepository를 new 하고 있고
	// 여기서 또 MemoryMemberRepository를 new 하면
	// 서로 다른 객체가 될 수 있는게 아닌가?
	// --> 동일한 인스턴스를 사용하도록 해야함
	// --> MemberService에서 new 하지 말고 생성자를 통해 외부로부터 받아오게 하자
	// 그리고
	// 각 테스트메서드 동작전에
	// MemoryMemberRepository 와 MemberService를 생성하자
	// --> 테스트메서드 실행마다 다른 객체임이 보장됨(독립적)
	// MemberService입장에서, 직접 new 하지 않음.. 외부에서 주입이 됨 -> 의존성 주입(DI, Dependency Injection)
	MemberService memberService;
	MemoryMemberRepository memberRepository;

	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);

	}


	@Test
	void 会員登録() {
		// given
		Member member = new Member();
		member.setName("spring");

		// when
		Long saveId = memberService.join(member);

		// then
		Member findMember = memberService.findOne(saveId).get(); //join메서드 결과-등록된id-로 다시 유저를 찾음
		assertThat(member.getName()).isEqualTo(findMember.getName()); // 찾은 유저의 정보-이름-과 give에서 설정한 유저 이름이 같은지
		// --> 즉 제대로 join메서드 실행되어 정보가 등록되었는지 검증
	}

	@Test
	void 중복회원예외확인() {
		//given
		//중복이름의 회원을 준비
		Member member1 = new Member();
		member1.setName("spring");

		Member member2 = new Member();
		member2.setName("spring");

		//when
		//회원1 등록 후 똑같은 이름의 회원2가 등록할 때 예외가 발생해야 함! (validateDuplicateMember메서드)
		//예외가 잘 발생하는지 확인해보자
		memberService.join(member1); //회원1 등록

		//회원2 등록시 예외발생 확인
/*방법1 : try-catch
		try {
			memberService.join(member2);

			//catch부가 실행이 안됨 -> 예외가 발생 안 한 것 (기대한 동작이 아님)

		} catch (IllegalStateException e) {
			//정상적으로 예외가 발생했음 (기대한 동작이다)
			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");
			// 기대결과는 테스트 패쓰
		}
*/

		/* 방법2 junit의 assertThrows 활용 : Exception의 발생유무 체크 */
		// assertThrows(로직결과기대값, 로직)
		// validateDuplicateMember메서드에서 IllegalStateException를 발생시키니까
		// assertThrows에서도 IllegalStateException클래스가 발생하는지 확인해야한다.
		// 로직 : memberService.join(member2) -member1과 동일이름 member2 회원가입시-
		// 기대값 : IllegalStateException.class -IllegalStateException클래스가 발생-
		//
//		assertThrows(IllegalStateException.class, () -> memberService.join(member2)); // 기대동작 테스트 패스 = 예외가 잘 발생함
//		assertThrows(NullPointerException.class, () -> memberService.join(member2)); // 기대동작 테스트 실패 = 엉뚱한 예외가 발생함

		/*방법3 junit의 assertThrows 활용 : Exception의 메세지로 확인하는 방법*/
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// 기대동작 테스트 패스 = 예외가 잘 발생함
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");  // 기대동작 테스트 패스 = 예외메세지 리턴값이 미리 등록한 메세지와 일치
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원123123");  // 기대동작 테스트 실패 = 미리 등록한 메세지와 불일치

		//then
	}

	@Test
	void 全ての会員検索() {
	}

	@Test
	void 特定の会員検索() {
	}
}