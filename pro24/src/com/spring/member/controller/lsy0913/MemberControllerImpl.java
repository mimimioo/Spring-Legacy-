package com.spring.member.controller.lsy0913;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

// 임포트 변경
import com.spring.member.service.lsy0913.MemberService;
import com.spring.member.service.lsy0913.MemberServiceImpl;
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

	// 수정 적용하기. 
	// 수정폼 -> 변경된 내용을 가져와서 -> MemberVO 타입의 박스에 담아서 -> 디비 전달. 
	@Override
	public ModelAndView updateMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		MemberVO memberVO = new MemberVO();
		bind(request, memberVO);
		int result = 0;
		// 실제 업데이트를 반영하는 로직, 외주주기. 동네 2번 보내기 
		// 이름 : updateMember
		result = memberService.updateMember(memberVO);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
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
	// 회원목록 조회 -> 회원가입 클릭시 -> 서버의 form 메서드가 받아서 처리하는 구조. 
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		System.out.println("viewName(회원가입폼)이 뭐야? : " + viewName);
		ModelAndView mav = new ModelAndView();
		// 결과 뷰로 가게끔, 설정. 
		mav.setViewName(viewName);
		return mav;
	}
	// 수정하는 폼으로 가기. 
	public ModelAndView modMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 수정하는 폼에서, id를 get 방식으로 전송해서, 서버측에 받을 수 있음. 
		// id를 가져오는 구조는, 삭제에서 복붙. 재사용.
		String id=request.getParameter("id");
		
		
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
