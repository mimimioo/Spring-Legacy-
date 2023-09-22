package com.spring.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.member.service.MemberService;

// 인터페이스를 구현한 클래스, 
public class MemberControllerImpl extends MultiActionController implements MemberController {
	
	//DI, 스프링 프레임워크, 느슨한 결합으로, 인터페이스 참조형 변수를 가지고있다. 또는 
	// 포함 관계로 가지고 있다. 서버가 동작시, 시스템(스프링)에 메모리에 로드가 되어서 
	// 사용이 가능하다.(언제든)
	private MemberService memberService;

	// 초기화를 하는 부분, 빈 생성 후 초기화 작업. 
	// 자동으로 @Autowired 라는 어노테이션이 이용해서, 편하게 작업할 예정. 
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

// ModelAndView : 데이터와 뷰를 전달한다. 
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// getViewName, 특정 문자열을 추출하는 로직, pro21 를 참고 하면 됩니다. 
		String viewName = getViewName(request);
		// 실제 작업 -===================================중요함. 
		// 순서1 , controller -> service 
		// 디비에 접근해서, 데이터를 가지고 온다. 
		List membersList = memberService.listMembers();
		// 데이터를 전달하는 로직. 
		ModelAndView mav = new ModelAndView(viewName);
		//뷰를 전달하는 로직. 
		mav.addObject("membersList", membersList);
		return mav;
	}
	
	// pro21 참고.
	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String fileName = uri.substring(begin, end);
		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		if (fileName.lastIndexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/"), fileName.length());
		}
		return fileName;
	}
}