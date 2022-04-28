package com.CBLee.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답(HTML 파일)
//@Controller사용해야한다.

//사용자가 요청->응답(Data)
@RestController
public class HTTPControllerTest {
	
	//http://localhost:8080/http/get
	/*@GetMapping("/http/get")
	public String getTest(@RequestParam int id,@RequestParam String username) {
		return  "get요청";
	}*/
	public String lombokTest() {
		Member m=Member.builder().username("Peter").password("1234").email("lcb4843@naver.com").build();
		System.out.println(m.getUsername());
		m.setUsername("CBLee");
		System.out.println(m.getUsername());
		return ("lombok successful");
	}
	
	@GetMapping("/http/get")
	public Member getTest(Member m) {//query에서 id,password,email을 입력하여 넣으면 MessageConverter가 알아서 member객체에 넣어줌

		
		return  m;
//		return  "get요청:"+m.getId()+","+m.getUsername()+","+m.getEmail();
	}
	
	
	
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {//JSON형태로 보내도 MessageConverter가 알아서 mapping해줌
	//또한 RequestBody 어노테이션으로 HTTP의 body를 object로 mapping해서 받을 수 있다.
		return "post요청:"+m.getId()+","+m.getUsername()+","+m.getEmail();
	}
	
	@PutMapping("/http/put")
	public String putTest() {
		return "put요청";
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete요청";
	}

}
