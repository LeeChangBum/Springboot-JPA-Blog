package com.CBLee.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CBLee.blog.dto.ResponseDto;
import com.CBLee.blog.model.RoleType;
import com.CBLee.blog.model.User;
import com.CBLee.blog.repository.UserRepository;
import com.CBLee.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	private UserRepository userRepository;
	
	@PostMapping("/api/user")
	public ResponseDto<String> save(@RequestBody User user) {
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		return new ResponseDto<String>(HttpStatus.OK,"회원가입이 정상적으로 되었습니다.");//결국 jsp파일의 response로 이것을 날릴거임, 또한 자바오브젝트를 JSON으로 Jackson이 알아서 변환해서 리턴해줌
	}
	
	
	

}
