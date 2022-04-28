package com.CBLee.blog.test;



import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.CBLee.blog.model.RoleType;
import com.CBLee.blog.model.User;
import com.CBLee.blog.repository.UserRepository;

@RestController//html파일이 아닌 data를 리턴해주는 controller
public class DummyControllerTest {
	
	@Autowired//아래 타입(UserRepository)으로 spring이 관리하는 객체가 있다면 userRepository변수에 넣어주라는 의미(즉, 의존성 주입(DI)의미)
	private UserRepository userRepository;
	
	//-------------------------------------------전체 데이터 받는 방법------------------------------------------------
	@GetMapping("/dummy/users")
	public List<User> list(){
		List<User> users=userRepository.findAll();
		return users;
	}
	//-------------------------------------------데이터 묶음으로 받는 방법------------------------------------------------
	// /dummy/userGroup?page=0, 1, 2, ...식으로 순서대로 받을 수 있다. 
	@GetMapping("/dummy/userGroup")
	public List<User> pageList(@PageableDefault(size=1, sort="id", direction=Sort.Direction.DESC)Pageable pageable){
		List<User> users=userRepository.findAll(pageable).getContent();
		return users;
	}

	
	//-------------------------------------------데이터베이스에 데이터 등록 및 데이터 조회------------------------------------
	
	//{id}주소로 parameter를 전달 받을 수 있음
	//http://localhost:8000/blog/dummy/user/2
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		//아래꺼 람다식으로 써보면
		//User user=userRepository.findById(id).orElseThrow(()->{
		//  return new IllegalArgumentException("~~");
		//}
		
		//user/4를 찾았을때 데이터베이스에 없으면 user가 null이 될것이므로 문제가 생긴다.
		//따라서 Optional로 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return하라.
		User user=userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {//만약 데이터가 없으면 orElseThrow부분이 실행된다.
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다.id:"+id);
			}
		});
		
		//웹상에서는 html,javascript만 읽을 수 있으므로 MessageConverter가 Jackson라이브러리를 사용하여 알아서 Json으로 convert해준다.
		return user;
	}

	@PostMapping("/dummy/join")
	public String join(@RequestBody User user){//key=value(약속된 규칙), @RequestBody쓰면 JSON데이터 받을 수 있다.
		System.out.println("username:"+user.getUsername());
		System.out.println("password:"+user.getPassword());
		System.out.println("email:"+user.getEmail());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);//이때 @CreateTimestamp는 자바에서 시간을 알아서 넣어서 데이터베이스에 보내준다.
		//하지만 role값은 @ColumnDefault로 데이터베이스에서 default값으로 user를 사용하겠다는 뜻이다.
		//다만 자바에서 role값에 null을 넣어서 보내므로 null이 들어가게 되어 데이터베이스에서는 null을 받게되어 적용이 안된다. 이때 @DynamicInsert를 이용하면 해결이 된다.
		return "회원가입이 완료되었습니다";
	}
	
	//---------------------------------------------데이터 UPDATE, DELETE------------------------------------------------
	@Transactional//함수 종료시에 자동 commit됨
	@PutMapping("/dummy/user/{id}")
	public User change(@PathVariable int id, @RequestBody User requestUser){
		User user=userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});//여기서 영속화(영속성 컨텍스트에 user객체를 가지고 옴) 진행
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		//save함수는 id를 전달하지 않으면 insert를 해주고
		//save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
		//save함수는 id를 전달하고 해당 id에 대한 데이터가 없으면 insert를 해준다.
		//userRepository.save(user); 따라서 이것도 가능하다.
		
		//@Transactional을 사용하면 더티 체킹을 한다. 따라서 save 함수를 사용하지 않고 update가 이루어진다.
		//더티 체킹- @Transactional이 종료될 때 영속성 컨텍스트의 1차 캐시의 user값과 controller의 user값을 비교한 후 변경을 감지하면 DB를 수정한다.
		return user;
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try{
			userRepository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {//모든 exception의 부모 Exception을 사용해도 됨
			return "해당 id는 없습니다.";
		}
		return "삭제되었습니다.";
	}
		
}
