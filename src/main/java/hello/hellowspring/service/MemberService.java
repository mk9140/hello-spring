package hello.hellowspring.service;

import hello.hellowspring.domain.Member;
import hello.hellowspring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

/*
* MemoryMemberRepository는 단순히 데이터를 입/출력하는 기능만 있었다.
*
* 서비스에서는 비즈니스로직을 구현
* ex)
* 회원가입 -> 회원가입시 id중복 체크, 비밀번호 재확인
* 회원조회 -> 조회 결과에 따른 메세지 출력
* 등.
*
* */

/*
* TIP !
* 클래스의 테스트파일 간단하게 작성하는 방법
* 클레스이름 선택 -> 상단 Navigate 메뉴 -> Test -> Create New Test
* 혹은
* 클레이름에 Caret(커서) 이동 후 Alt+Enter -> Create Test
*
* */

public class MemberService {
//	private final MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

	// new 하는 것이 아니라 외부에서 받아오도록
	// --> 의존성 주입(DI)
	private final MemberRepository memberRepository;
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}


	/**
	 * 회원가입
	 */
	public Long join(Member member) {
		/* 같은 이름의 중복회원은 허용X */
		validateDuplicateMember(member); // 중복회원 검증 메소드


		memberRepository.save(member);
		return member.getId(); //일단 임의로 등록된 id를 반환
	}

	private void validateDuplicateMember(Member member) {
		// 레퍼클래스 Optional을 썼으므로 관련 메서드 사용가능
		// ifPresent:null이 아니라 어떤 값이 있으면 ()안의 로직 동작
		memberRepository.findByName(member.getName())
				.ifPresent(
						lambdaFunc ->{
							throw new IllegalStateException("이미 존재하는 회원");
						}
				);
	}

	/**
	 * 전체 회원 조회
	 */
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	/**
	 * id를 통한 회원조회
	 */
	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}

}
