package com.CBLee.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//ORM->Java(다른언어도 포함)Object-> 테이블로 매핑해주는 기술
@Entity//User 클래스가 밑 변수를 읽어서 MYSQL에 테이블을 생성한다.
//@DynamicInsert//데이터베이스로 insert할때 자바에서 null인 필드 제외
public class User {
	
	@Id//primary key 
	@GeneratedValue(strategy=GenerationType.IDENTITY)//프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.(MYSQL을 사용하므로 auto_increment를 사용한다.)
	private int id;//auto_increment
	
	@Column(nullable=false,length=30)//밑의 변수는 null이 될 수 없고 length는 30이 최대다.
	private String username;//아이디
	
	@Column(nullable=false,length=100)
	private String password;
	
	@Column(nullable=false,length=50)
	private String email;
	
	//@ColumnDefault("USER") DB에 defualt값으로 user을 넣음
	@Enumerated(EnumType.STRING)//DB는 RoleType이란것이 없으므로 DB에게는 String이라고 알려준다.
	private RoleType role;//Enum을 쓰는게 좋다. ADMIN,USER
	
	@CreationTimestamp//시간이 자동 입력
	private Timestamp createDate;
	//따라서 id와 createDate는 비워놔도 된다.

}
