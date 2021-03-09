package hello.hellowspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * 회원 웹 기능 - 간단한 홈 화면 추가
 * */
@Controller
public class HomeController {

	//URL:서버주소/ 만 입력하면 home.html을 반환
	@GetMapping("/")
	public String home() {
		// url "/"에 대한 멥핑이 스프링부트의 index.html보다 우선순위가 높음
		// 만약 "/" 컨트롤러 내부에서 멥핑이 없으면, 스프링부트는 resources > static >index.html 을 반환(웰컴페이지)
		return "home";
	}
}
