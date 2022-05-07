package com.CBLee.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller//해당 어노테이션을 붙힘으로써 파일을 리턴함
public class BoardController {
	
	@GetMapping("/")
	public String index() {
		//yml파일에서 prefix랑 suffix를 적어놓았으므로 알아서 경로를 찾아감
		return "index";
	}

}
