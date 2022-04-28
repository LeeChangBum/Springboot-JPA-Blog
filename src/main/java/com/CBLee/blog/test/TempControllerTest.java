package com.CBLee.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller//파일을 리턴하기 위한 controller어노테이션
public class TempControllerTest {
	
	//http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		//파일리턴 기본경로:src/main/resources
		return "/home.html";//이게 파일리턴 기본경로 뒤에 붙는것임
	}

}
