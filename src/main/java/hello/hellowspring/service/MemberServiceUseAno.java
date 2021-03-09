package hello.hellowspring.service;

import hello.hellowspring.domain.Member;
import hello.hellowspring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/*
* 자바빈 등록방법:컴포넌트 스캔방법
* */

@Service // 컴포넌트스캔방법! 스프링이 관리하도록(빈) @Autowired가 될 수 있도록!
// @Component 라고 해도 됨. -> @Service어노테이션에는 내부에 이미 @Component가 등록되어있음
public class MemberServiceUseAno {

	private final MemberRepository memberRepository;
	@Autowired 	// 생성자에 @Autowired 어노테이션? -> 스프링 컨테이너가 가지고 있는 MemberRepository를 가져다 연결해줌!
	public MemberServiceUseAno(MemberRepository memberRepository) {
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
