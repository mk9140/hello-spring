package hello.hellowspring.domain;

public class Member {

	private Long id; // 시스템이 정하는 임의의 id
	private String name; // 유저이름

	/* 보일러코드 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
