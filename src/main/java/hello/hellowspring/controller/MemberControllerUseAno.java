package hello.hellowspring.controller;

import hello.hellowspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/*
 * 회원 컨트롤러가 회원서비스 및 회원 리포지토리를 사용할 수 있게
 * 의존관계 준비
 * -> MemberController 에 의존관계 추가해보기
 */
@Controller // @Controller내부에 이미 @Component 어노테이션이 있음
public class MemberControllerUseAno {


	private final MemberService memberService;

	@Autowired
	public MemberControllerUseAno(MemberService memberService) {

		this.memberService = memberService;
	}

}
