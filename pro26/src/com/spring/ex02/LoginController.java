package com.spring.ex02;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("loginController")
public class LoginController {
	@RequestMapping(value = { "/test/loginForm.do", "/test/loginForm2.do" }, method = { RequestMethod.GET })
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginForm");
		return mav;
	}
	
    @RequestMapping(value = "/test/login.do", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		String userID = request.getParameter("userID");
		String userName = request.getParameter("userName");
		mav.addObject("userID", userID);
		mav.addObject("userName", userName);

		return mav;
	}

    // 데이터 가져오기 형식 1, 단수, 낱개로 가져오기. 
    // 확인이 로그인 폼에서 확인을 하니, 일단, 뷰를 서버에 올리기. 
    // jsp 파일을 단순 실행하기. 틀렸음
    // 수정, 스프링구조이니, 폼도 , 무조건 서버에서 등록된 주소로 가야함. 
    // http://localhost:8090/pro26/test/loginForm.do
	/*
    @RequestMapping(value = "/test/login2.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login2(@RequestParam("userID") String userID2, 
			                  @RequestParam("userName") String userName2,
			                  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		
		// 기존 데이터 가져오기 방식, 변경함. 앞으로.
		// String userID = request.getParameter("userID");
		// String userName = request.getParameter("userName");
		
		System.out.println("userID: "+userID2);
		System.out.println("userName: "+userName2);
		mav.addObject("userID2", userID2);
		mav.addObject("userName2", userName2);

		return mav;
	} */
	

	@RequestMapping(value = "/test/login2.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login2(@RequestParam("userID") String userID, 
                               @RequestParam(value="userName", required=true) String userName,
			                   @RequestParam(value="email", required=false) String email,
			                  HttpServletRequest request, HttpServletResponse response) throws Exception {
		// @RequestParam 의 속성 기본값 true -> null -> 오류
		// @RequestParam 의 속성 false -> null -> 정상동작(null 처리함.)
		// 회원가입시, 프로필 이미지 , 유저마다 설정할 때있고, 안하는 경우도 많음. 
		// 이 때 이미지 변수의 조건을 false 하면, null 도 허용을 한다. 
		// 만약, true 오류가 발생함. 
		
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		
		// String userID = request.getParameter("userID");
		// String userName = request.getParameter("userName");
		
		// 결과 확인은 콘솔로 확인하기. 
		System.out.println("userID: "+userID);
		System.out.println("userName: "+userName);
		System.out.println("email: "+ email);
		mav.addObject("userID", userID);
		mav.addObject("userName", userName);
		return mav;
	}
	
// Map 타입의 참조형 변수 , info에 , 
	// 클라이언트에서 입력된 정보가 자동으로 매핑이된다, 연결이된다. 
	@RequestMapping(value = "/test/login3.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login3(@RequestParam Map<String, String> info,
			                   HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		// 클라이언트로 부터 데이터 받아서 
		String userID = info.get("userID");
		String userName = info.get("userName");
		String userEmail = info.get("email");
		System.out.println("userID: "+userID);
		System.out.println("userName: "+userName);
		System.out.println("userEmail: "+userEmail);
		// 결과 뷰에 데이터 전달
		mav.addObject("info", info);
		// 결과 뷰를 전달.
		mav.setViewName("result");
		return mav;
	}
	
	@RequestMapping(value = "/test/login4.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login4(@ModelAttribute("info") LoginVO loginVO,
			                   HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		System.out.println("/test/login4.do -> userID: "+loginVO.getUserID());
		System.out.println("/test/login4.do -> userName: "+loginVO.getUserName());
		System.out.println("/test/login4.do -> email: "+loginVO.getEmail());
		mav.setViewName("result");
		// 모델엔뷰의 인스턴스에 설정된 뷰와 데이터를 이용해서, 결과 뷰에 전달이된다. 
		return mav;
	}
	   
	@RequestMapping(value = "/test/login5.do", method = { RequestMethod.GET, RequestMethod.POST })
	// 메서드의 반환 타입이 모델엔뷰가 아니라, 단순 문자열로 되어 있고,
	// 매개변수는 모델이라는 클래스를 이용중.
	// 서버에서 임의로 설정한 데이터를 , 결과 뷰에 전달함.
	public String login5(Model model,
			                   HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		// 클라이언트의 뷰를 받아서 작업도 가능한데, 일단, 임의로 서버에서 
		// 설정한 더미 데이터 테스트 하고 있음. 
		model.addAttribute("userID", "lsy");
		model.addAttribute("userName", "이상용");
		// 뷰 리졸버에 등록이된 , 결과뷰 임. 
		return "result";
	}	
}
