package com.spring.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.member.service.MemberService;
import com.spring.member.vo.MemberVO;




@Controller("memberController")
public class MemberControllerImpl   implements MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	MemberVO memberVO ;
	
	@Override
	@RequestMapping(value="/member/listMembers.do" ,method = RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
	@RequestMapping(value="/member/addMember.do" ,method = RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("member") MemberVO member, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		int result = 0;
		System.out.println("뷰로부터 데이터 확인 : member.getId() "+member.getId());
		result = memberService.addMember(member);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
		
	}
	
	@Override
	@RequestMapping(value="/member/removeMember.do" ,method = RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("id") String id, 
			           HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("utf-8");
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	//동일, -> 수정폼 이름이 다름. 
	// 재사용 안함, 이유? 폼은 단순 폼만 불러오면 상관이 없는데. 
	// 수정하는 폼은 기존의 회원의 정보를 가져오는 로직이 추가로 필요함
	// 여기에 추가 할려면, 구조를 변경해야함, 분기를 사용해야함. 
	// 기존에 정규식 *Form 끝나는 부분을 받는 로직이 존재 재사용 방법2-> 기존 뷰 이름 변경.
	
	/*@RequestMapping(value = { "/member/loginForm.do", "/member/memberForm.do" }, method =  RequestMethod.GET)*/
	@RequestMapping(value = "/member/*Form.do", method =  RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	
// 동일
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

		String viewName = uri.substring(begin, end);
		if (viewName.indexOf(".") != -1) {
			viewName = viewName.substring(0, viewName.lastIndexOf("."));
		}
		if (viewName.lastIndexOf("/") != -1) {
			viewName = viewName.substring(viewName.lastIndexOf("/"), viewName.length());
		}
		return viewName;
	}

	// pro26 맞게끔 교체 작업. 다음 시간. 
	// 애너테이션 기법으로 교체 작업
	// 현재 pro26, requestMapping으로 교체되어서, *Form 방식으로 변경할 예정.
//	원래대로, 해당 매핑 주소를 추가해서, 해당 폼으로 가게 하는 방법1-> 추가
	@Override
	@RequestMapping(value = "/member/modMember.do", method =  RequestMethod.GET)
	public ModelAndView modMember(@RequestParam("id") String id,HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 수정하는 폼에서, id를 get 방식으로 전송해서, 서버측에 받을 수 있음. 
				// id를 가져오는 구조는, 삭제에서 복붙. 재사용.
//				String id=request.getParameter("id");
				
				
				String viewName = getViewName(request);
				System.out.println("viewName(수정폼)이 뭐야? : " + viewName);
				ModelAndView mav = new ModelAndView();
				
				// mav 에 데이터를 넣는 구조, 회원가입에서 복붙. 재사용.
				// 결과 뷰에, 아이디만 전달함. 
				// 만약, 이 아이디에 관련된 모든 정보를 결과 뷰에 재사용할려면
				// 이 아이디로 하나의 회원의 정보를 디비에서 가져고 와서, 
				// 이 하나의 회원의 정보를 결과 뷰에 넣으면됨. 
				mav.addObject("user_id", id);
				
				// 추가, 해당 아이디로, 정보를 가져오기. 
				// 조회된 한 회원의 정보를 담을 임시 인스턴스 : memberOne
				// getOneMember : 서비스에 아직 없는 메서드 임. 임의로 추가. 
				// 외주, 서비스로 동네2번 가기. 인터페이스도 추상메서드 추가. 
				// 구현한 클래스에도 재정의 하기. 
				MemberVO memberOne = memberService.getOneMember(id);
				
				// 디비에서 , 회원 정보를 가져왔으면 뷰에 데이터 전달하기. 
				mav.addObject("member", memberOne);
				
				// 결과 뷰로 가게끔, 설정. 
				mav.setViewName(viewName);
				return mav;
	}

	//애너테이션 기법으로 교체 작업
	@Override
	@RequestMapping(value = "/member/updateMember.do", method =  RequestMethod.POST)
	public ModelAndView updateMember(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
//		MemberVO memberVO = new MemberVO();
//		bind(request, memberVO);
		int result = 0;
		// 실제 업데이트를 반영하는 로직, 외주주기. 동네 2번 보내기 
		// 이름 : updateMember
		result = memberService.updateMember(memberVO);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}


}
