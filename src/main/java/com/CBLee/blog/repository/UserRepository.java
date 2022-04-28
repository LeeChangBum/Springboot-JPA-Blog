package com.CBLee.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.CBLee.blog.model.User;

//DAO
//JpaRepository사용 시 자동으로 bean으로 등록이 되므로 @Repository생략 가능하다.

public interface UserRepository extends JpaRepository<User, Integer>{//generic의 의미는 user table이 관리하는 repository, primary key는 integer이다라는 의미
//JpaRepository가 왠만한 함수들을 다 가지고 있으므로 extend한다.
	
}
