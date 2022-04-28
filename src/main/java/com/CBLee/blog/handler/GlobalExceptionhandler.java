package com.CBLee.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice//모든 @Controller, 전역에서 발생할 수 있는 예외를 잡아 처리해주는 annotation이다.
@RestController
public class GlobalExceptionhandler {
	
	@ExceptionHandler(value=IllegalArgumentException.class)//@Controller, @RestController가 적용된 Bean내에서 발생하는 예외를 잡아서 하나의 메서드에서 처리해주는 기능
	public String handleArgumentException(IllegalArgumentException e) {
		return e.getMessage();
	}
}
