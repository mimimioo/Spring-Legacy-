package com.myspring.pro29.ex02;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.pro29.ex01.MemberVO;

@Controller
// 앞에서는 형식은 완전 레스트 형식. @RestController 이런구조 라서
// 하위 메서드 전체가, 뷰 없이 데이터만 전달.

// 지금은 상위에 @Controller 형식으로 선언해서
// 기본은 : 데이터 + 뷰 기본 구조 
// 추가로 : 특정 메서드에서 뷰없이 , 데이터만 전달하는 구조도 혼합가능.
public class ResController {
	@RequestMapping(value = "/res1")
	@ResponseBody
	// 클라이언트 -> 서버 : 데이터 전달
	// @RequestBody MemberVO vo 비교, 클라이언트로 부터 넘어온 정보를 , 자동으로 
	// 잭스 라이브러리 이용해서, 서버에서 만들어 둔 모델 클래스 타입으로 자동 할당. 
	// @ResponseBody
	// 서버 -> 클라이어트에게 응답 :데이터 전달
	public Map<String, Object> res1() {
		// 서버 -> 클라이언트에게 데이터 전달, Map 컬렉션 담아서 전달
		// 잭슨 라이브러리 알아서, JSON 형태의 타입으로 변환해서 전달. 
		// @ResponseBody -> 자동으로 변환해서 전달해준다.
		// Map 형식, 자바스크립의 객체 형식이 동일한 구조,
		// JSON , 키가 문자열 타입으로 되어있다. 
		// 서로가, 변환하기 좋은 구조로 되어 있다. 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "hong");
		map.put("name", "홍길동");
		return map;
	}
	
	// res1 은 데이터만 전달하는 레스트 형식
	// res2 은 뷰로 전달하는 구조. 
	// ModelAndView 역할 : 데이터 + 뷰
	// home.jsp 
	@RequestMapping(value = "/res2")
	public ModelAndView res2() {
		return new ModelAndView("home");
	}
	
}
