package com.myspring.pro29.ex01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//서버에서 클라이언트에게 JSON의 문자열 형태로 전달. - 데이터만 전달하겠다.
// 하위에 있는 모든 메서드는 , JSON의 문자열 형태로 전달. - 데이터만 전달하겠다.
// @Controller -> 뷰 + 데이터 , 데이터만 전달하는 메서드를 같이 혼합 구성 가능. 
@RequestMapping("/test/*")
public class TestController {
  static Logger logger = LoggerFactory.getLogger(TestController.class);
	
  // /pro29/test/hello
  @RequestMapping("/hello")
  //서버 -> 클라이언트 : 문자열 타입
  // 클라이언트 -> 서버 : 주소는 : /pro29/test/hello
  // 서버가 응답을 하는데, 이제는 데이터만 전달해서, 
  // 응답을 데이터만 함: Hello REST!!
  public String hello() {
	return "Hello REST!!";
  } 
  
  // 서버 -> 클라이언트 : MemberVO 타입의 인스턴스를, JSON 의 문자열로 응답할 예정. 
  // MemberVO 타입의 인스턴스 -> Jackson 라이브러리를 이용해서 -> 자동으로 JSON 문자열
  // 전달 할 예정. 
  // 클라이언트 측 : 주소 : /pro29/test/member
  @RequestMapping("/member")
  public MemberVO member() {
    MemberVO vo = new MemberVO();
    vo.setId("hong");
    vo.setPwd("1234");
    vo.setName("홍길동");
    vo.setEmail("hong@test.com");
    return vo;
  } 	
  
  // 복수개로 전달 함. 
  // 앞, 뒷단데 전달 형식 JSON 타입으로 데이터를 많이 주고 받는 경우가 생김.
  // 서버 -> 클라이언트에게 : 데이터 전달, 복수 , 요소로는 MemberVO 타입으로 전달. 
  @RequestMapping("/membersList")
  public List<MemberVO> listMembers () {
	  // 임시 리스트, 타입은 MemberVO 만 담겠다. 
    List<MemberVO> list = new ArrayList<MemberVO>();
    // 반복문 이용해서, 샘플 더미 데이터를 10개 정도만 작성.
	for (int i = 0; i < 10; i++) {
		// 임시 인스턴스 10개를 반복해서, 리스트에 담기.
	  MemberVO vo = new MemberVO();
	  vo.setId("hong"+i);
	  vo.setPwd("123"+i);
	  vo.setName("홍길동"+i);
	  vo.setEmail("hong"+i+"@test.com");
	  list.add(vo);
	}
	// 결과 리스트를 , 서버 -> 클라이어트로 전달하기. 
    return list; 
  }	
  
  // 반환타입 : 맵 타입
  @RequestMapping("/membersMap")
  public Map<Integer, MemberVO> membersMap() {
	  // 임시 담을 컬렉션 맵을 구성.
    Map<Integer, MemberVO> map = new HashMap<Integer, MemberVO>();
    // 더미 데이터를 담아서 , 전달 할 예정. 
    for (int i = 0; i < 10; i++) {
      MemberVO vo = new MemberVO();
      vo.setId("hong" + i);
      vo.setPwd("123"+i);
      vo.setName("홍길동" + i);
      vo.setEmail("hong"+i+"@test.com");
      map.put(i, vo); 
    }
    // 서버 -> 클라이어트에게 데이터만 전달. 전달 박스의 타입이 , 맵 형태. 
    return map; 
  } 	
  
  //@PathVariable : 서버의 url 주소에서, 특정의 매개변수를 서버로 가져올 때 사용. 
  //예) 게시판에서, 특정 게시글의 상세페이지를 보고 싶다. 
  // 해당 게시글의 번호를 , 서버에게 전달해야, 해당 게시글 번호의 담긴 내용을 찾을수 있다. 
  //예2) 수정폼 , 회원관리, 특정 회원의 수정을 할려면, 그 회원의 수정폼을 클릭하죠?
  // 클릭했을 때, 서버에 넘겼던 매개변수가, 아이디를 알려주는 것. 
  @RequestMapping(value= "/notice/{num}" , method = RequestMethod.GET)
  public int notice(@PathVariable("num") int num ) throws Exception {
	  
	  System.out.println("클라이언트로 부터 받은 매개변수 서버에서 확인 num : "+ num);
	  // 서버 -> 클라이언트 , 데이터만 전달, 클라이언트에게서 받은 매개변수를 다시 재전달.
	  return num;
  }	

  // @RequestBody -> 클라이언트 -> 서버에게 데이터만 전달 하는 중. 
  // 전달하는 타입을 MemberVO 인스턴스를, 잭슨이라고하는 기능을 통해서, JSON 문자열 로 전달. 
  // @Controller에서, 혼합해서 사용가능.
  // 클라이언트에서 특정 객체에 담아서 -> JSON 형태로 -> 서버에 전달하면.
  // 밑에 메서드가 동작
  // 서버에서, 전달받은 JSON 문자열을, 자동으로 MemberVO vo 타입으로 자동 할당.
  // 클라이언트에서 접근하는 주소 
  // : pro29/test/info
  @RequestMapping(value= "/info", method = RequestMethod.POST)
  public void modify(@RequestBody MemberVO vo ){
	  // 모델 클래스에서, toString 재정의를 했음. 
	  // 서버에서 넘어온 데이터 , 로그로 확인.
    logger.info(vo.toString());
    logger.info(vo.getEmail());
  }
  
  // 반환 타입 형식에서, ResponseEntity 타입을 보는데.
  // 데이터 만 주거니 받거니 하면, 잘 전달이 되었는지 여부 확인이 어려움.
  // 그래서, http status code , 전달을 잘 받았다. 200, 
  // 3xx 리다이렉트 관련
  // 4xx not found 
  // 5xx 서버관련 코드를 표시 해줌. 
  // 데이터 + 상태 , 같이 전달하자. 
  // 클라이언트: 주소 : /pro29/test/membersList2
  @RequestMapping("/membersList2")
  public  ResponseEntity<List<MemberVO>> listMembers2() {
	  // 서버 -> 클라이언트에게 더미 데이터를 전달 하는 데, 상태도 같이 전달하자.
	List<MemberVO> list = new ArrayList<MemberVO>();
	for (int i = 0; i < 10; i++) {
	  MemberVO vo = new MemberVO();
	  vo.setId("lee" + i);
	  vo.setPwd("123"+i);
	  vo.setName("이상용" + i);
      vo.setEmail("lee"+i+"@test.com");
	  list.add(vo);
	}
	//list : 데이터 전달, 
	// HttpStatus.INTERNAL_SERVER_ERROR: 상태값도 같이 전달하는 연습. 
//    return new ResponseEntity(list,HttpStatus.INTERNAL_SERVER_ERROR);
	return new ResponseEntity(list,HttpStatus.OK);
  }	
  
  
  //ResponseEntity -> 데이터 + 상태도 같이 전달. + 헤더에 속성도 추가해서 전달.
  // 클라이언트: 주소 : /pro29/test/res3
	@RequestMapping(value = "/res3")
	public ResponseEntity res3() {
		
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", "text/html; charset=utf-8");
	    String message = "<script>";
		message += " alert('res3 테스트중');";
		message += " location.href='/pro29/test/membersList2'; ";
		message += " </script>";
		return  new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	}
	
}
