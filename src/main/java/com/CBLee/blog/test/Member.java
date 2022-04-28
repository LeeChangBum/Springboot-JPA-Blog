package com.CBLee.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


 
@Data//Getter,Setter를 만드는 방법-알아서 객체생성까지 해준다.
//@AllArgsConstructor//변수 전체를 위한 생성자
//@NoArgsConstructor//아예 빈 생성자
//@RequiredArgsConstructor final붙은 애들을 위한 생성자
public class Member {
	private int id;//밑 변수 모두 나중에 데이터베이스에서 가져올것들임
	private String username;
	private String password;
	private String email;
	
	@Builder//생성자의 순서를 지키지않아도됨
	public Member(int id, String username, String password, String email){
	this.id=id;
	this.username=username;
	this.password=password;
	this.email=email;
	}
}
