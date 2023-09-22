package com.spring.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.member.service.MemberService;
import com.spring.member.service.MemberServiceImpl;
import com.spring.member.vo.MemberVO;
// 동네1번-2 , 
public class MemberControllerImpl extends MultiActionController implements MemberController {
	// 실제 작업은 외주 주자, 동네 2번으로,
	// 동네2번으로 보낼려면, 여기서는 해당 인스턴스가 필요.
	// 구조, spring mvc에서는 , 이처럼 xml 설정 정의의해서, 세터, 생성자로 초기화 함.
	// 세터 구조로 초기화하고 있음. 
	private MemberService memberService;

	public void setMemberService(MemberServiceImpl memberService) {
		this.memberService = memberService;
	}
	// 여기까지 동네2번에 외주 맡기려면, 필요한 인스턴스 초기화 설정. 
	// 하나만 연결 , DI , 의존성 주입, 느슨한 결합. 
	
	// 조회 : listMembers
	@Override
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request 인스턴스에서, 해당 뷰를 가져오는 메서드 : getViewName
		// pro21,22 상세히 주석 달았으니 참고. 
		String viewName = getViewName(request);
		//실제 작업, 외주 맡김. 동네 2번으로 보내기. 
		List membersList = memberService.listMembers();
		// 동제 2번, 3번, 4번, 디비 까지 가서, 다시 돌아온 결과 
		// ModelAndView  에, 데이터와 뷰를 같이 전달함. 
		// 현재는 바로 뷰를 설정.  
		ModelAndView mav = new ModelAndView(viewName);
		// 데이터를 설정하는 작업.
		mav.addObject("membersList", membersList);
		// 이 메서드의 반환은 결과뷰와 데이터를 반환 함. 기본적인 mvc의 패턴. 
		return mav;
	}

	@Override
	public ModelAndView addMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		MemberVO memberVO = new MemberVO();
		/*
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String name=request.getParameter("name");
		String email = request.getParameter("email");
		memberVO.setId(id);
		memberVO.setPwd(pwd);
		memberVO.setName(name);
		memberVO.setEmail(email);
		 */
		bind(request, memberVO);
		int result = 0;
		result = memberService.addMember(memberVO);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	@Override
	public ModelAndView removeMember(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	

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
