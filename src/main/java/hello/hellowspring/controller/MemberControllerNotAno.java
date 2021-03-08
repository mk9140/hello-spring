package hello.hellowspring.controller;

import hello.hellowspring.service.MemberService;
import hello.hellowspring.service.MemberServiceNotAno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/*
* 자바코드로 직접 스프링빈 등록하는 방법
* */

@Controller // 컨트롤러쪽은 그대로 둔다. 컨트롤러는 어쩔 수 없음
public class MemberControllerNotAno {


	private final MemberServiceNotAno memberService;

	@Autowired
	public MemberControllerNotAno(MemberServiceNotAno memberService) {

		this.memberService = memberService;
	}

}
