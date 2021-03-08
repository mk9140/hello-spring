package hello.hellowspring;

import hello.hellowspring.repository.MemberRepository;
import hello.hellowspring.repository.MemoryMemberRepositoryNotAno;
import hello.hellowspring.service.MemberServiceNotAno;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* 자바코드로 직접 스프링빈 등록하는 방법
* */

@Configuration // 스프링이 기동될 때 @Configuration을 읽고 @Bean의 로직을 실행하고 등록함
public class SpringConfig {

	@Bean // 직접 스프링빈을 등록하겠다
	public MemberServiceNotAno memberServiceNotAno() {
		return new MemberServiceNotAno(memberRepository());
		//memberRepository() 의 생성자는 외부로부터 주입받은 MemberRepository의 객체(구현체)가 필요하므로
		// memberRepository()메서드를 통해 얻은 (구현체)MemoryMemberRepositoryNotAno 오브젝트를 넣어줌
	}

	@Bean
	// 인터페이스명 변수명 = new 구현체
	public MemberRepository memberRepository() {
		return new MemoryMemberRepositoryNotAno();
	}

 }
