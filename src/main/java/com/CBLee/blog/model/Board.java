package com.CBLee.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false, length=100)
	private String title;

	@Lob//대용량 데이터
	private String content;//섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨.
	
	@ColumnDefault("0")
	private int count;//조회수
	
	@ManyToOne(fetch=FetchType.EAGER)// Many=Board, User=One 한명의 user가 많은 board를 만들 수 있다는 의미(연관관계를 의미)
	//DB에 보면 userID는 user table의 id를 참조하고있다고 나와있는데 이는 @ManyToOne때문이다.
	@JoinColumn(name="userId")//DB에는 이렇게 저장할거다.
	private User user;//DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다. 따라서 userId라는 이름의 foreignkey로 저장하는 것이다.
	
	@OneToMany(mappedBy="board",fetch=FetchType.EAGER)//mappedBy 연관관계의 주인이 아니다(난 foreignkey가 아니에요)DB에 column을 만들지 마세요
	//OneToMany는 원래는 fetchType이 LAZY로 설정되어있음
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}
