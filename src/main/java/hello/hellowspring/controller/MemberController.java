package hello.hellowspring.controller;

import hello.hellowspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/*
  스프링 빈과 의존과계
  스프링 빈? 스프링이 관리하는 객체
 */


/*
 * 회원 컨트롤러가 회원서비스 및 회원 리포지토리를 사용할 수 있게
 * 의존관계 준비
 * -> MemberController 에 의존관계 추가해보기
 */
@Controller //컴포넌트스캔방법!  @Controller내부에 이미 @Component 어노테이션이 있음
public class MemberController {
	/*
	 private MemberService memberService = new MemberService();
	기존의 방법 -> 직접 new 해줬음
	-> MemberController이외의 다른 컨트롤러들이 가져다 사용해버릴 수 있는 문제 있음, 하나만 생성해도 될 것이
	불필요한 객체가 많아질 수 있음
	--> 스프링 컨테이너에 "딱 하나만" 오브젝트를 등록해서, 그것을 받아오도록 하자
	 */

	private final MemberService memberService; // 직접 new 하지 않고
	// 생성자에 @Autowired 어노테이션?
	// -> 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입함함	// -> 스프링 컨테이너가 가지고 있는 MemberService를 가져다 연결해줌!
	//생성자를 통해서 외부로부터 받아도록 하자
	@Autowired
	public MemberController(MemberService memberService) {
		// 주의! MemberService에 @Service 어노테이션이 없으면
		// 그저 자바코드일 뿐이므로 스프링 컨테이너가 찾아주지 못한다.

		this.memberService = memberService;
	}

}
