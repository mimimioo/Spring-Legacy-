package com.spring.ex01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// SimpleUrlController -> Controller 라는 인터페이스를 구현해서 
// handleRequest 라는 메서드를 재정의를 했음. 
// 반환 타입 : ModelAndView ( 시스템 클래스)
// 매개변수로는 타입 , HttpServletRequest, HttpServletResponse
// 해당 인스턴스를 사용하고 있음. 
// 원래는 : 뷰 + 데이터를 , 클라이언트에게 전달한다. 
// 지금은, 단순 뷰만 전달. 
// url mapping 주소 : /test/index.do ->index.jsp : 연결 하고 있음. 
public class SimpleUrlController implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("index.jsp");
	}
}
