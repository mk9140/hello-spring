package hello.hellowspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/*
* 웹 브라우저 -> 서버주소/Mapping주소 입력 -> (스프링부트 내장) 톰켓 서버
* -> 스프링 컨테이너 -> @Controller -> @(Get,Post,Request)Mapping("Mapping주소")
* */


/* 정적 컨텐츠
* 웹 브라우저에서의 요청(주소) -> 컨트롤러 검색 -> 해당 컨트롤러 없음
* -> 스프링부트가 resources > static 폴더에서 주소와 동일한 이름의 html파일 찾아서 표시
* */




@Controller
public class HelloController {

	/* 템플릿엔진 방식 */
	@GetMapping("hello")
	public String hello(Model model) {
		model.addAttribute("data", "hello!!");
		return "hello";
	}
	/* 템플릿엔진 방식 - 파라미터 받아보기 */
	@GetMapping("hello-mvc")
	public String helloMvc(@RequestParam(value = "nameParam", required = false) String name, Model model) {
		/*
		 * MVC와 템플릿 엔진
		 * @RequestParam
		 * value로 지정한 문자열을 url의 파라미터(www.naver.com?param1=123&param2=456...)에서
		 * 찾아내어 바로 다음 선언된 파라미터에 저장
		 * required가 true이면 해당 파라미터는 무조건 있어야함(없으면 에러발생)
		 * default로 파라미터 전달받지 않았을 때의 기본값 설정가능
		 *
		 * 이 helloMvc함수에서는
		 * url로부터 파라미터:nameParam의 값을 받아온다(없어도 됨)
		 * 받아온 값을 파라미터 String name에 격납
		 * name을 모델형 model에 "nameAttr"라는 이름으로 담는다.
		 * 뷰 리졸버가 hello-template.html을 찾아서 브라우저에 표시
		 * (model이 전달된다)
		 * */

		model.addAttribute("nameAttr", name);
		return "hello-template";
	}

	/////////////////////////////////////////////////////
	/////////////////////////////////////////////////////

	/* API방식 - 데이터를 그대로 넘겨보기*/
	@GetMapping("hello-string")
	@ResponseBody // http 응답의 body부에 데이터를 그대로 넣어주겠다.
	public String helloString(@RequestParam(value = "urlParam", required = false) String param1) {

		return "hello " + param1;
		//view가 없이 직접 화면에 뿌려진다
		//소스보기 해 보면 html 태그도 없음
	}


	/* API방식 - 오브젝트를 넘겨보기 (일반적인 API방식의 사용법)*/
	static class Hello {
		/* class의 내부라도 static을 붙이면 또 class 선언 가능 */

		private String name;

		/* getter, setter 등의 보일러코드 -> Lombok 으로 어노테이션화 가능*/
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
	@GetMapping("hello-api")
	@ResponseBody
	public Hello helloApi(@RequestParam("urlParam") String param1) {
		//객체생성
		Hello hello = new Hello();
		//필드 셋
		hello.setName(param1);
		//객체를 "json 제이슨"방식으로 넘긴다(@ResponseBody의 디폴트 방식)
		return hello;

		/*
		 * 객체를 넘기는 경우
		 * HttpMessageConverter가 동작한다. (템플릿방식의 뷰 리졸버 대신)
		 * 단순 문자인 경우	StringHttpMessageConverter가 가 처리
		 * 객체인 경우 		MappingJackson2HttpMessageConverter 가 처리
		 * (Jackson ? -> 객체를 json으로 바꿔주는 라이브러리 중 하나(대표적으로 Jackson2 , 구글의 gson))
		 * 외에도 단순 byte처리 등을 위한 HttpMessageConverter가 기본으로 등록되어있다.
		 * 설정을 변경하는 것도 가능한데, 보통은 그대로 쓴다.
		 */
	}
	/////////////////////////////////////////////////////
	/////////////////////////////////////////////////////




}
