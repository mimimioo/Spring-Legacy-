package com.spring.ex02;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class UserController extends MultiActionController {
	// login 메서드 , 반환 타입: ModelAndView
	// 매개변수 , 반환 타입: HttpServletRequest, HttpServletResponse
	// 클라이언트, 서버 간에 통신상 주고 받기 위한 전달 매개체.
	
	//추가, loginForm 에서 action에서 지정된 값으로 호출이되면, 여기 
	// login 메서드로 갑니다. 
	// 
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 임시 저장하기 위한 변수, 로그인 폼에서 입력받은 내용을 
		// 클라이언트로 부터 받아와서 담을 변수. 
		String userID2 = "";
		String passwd2 = "";
		// 인스턴스, 뷰 + 데이터를 같이 처리 해주는 인스턴스 
		ModelAndView mav = new ModelAndView();
		// 클라이언트로부터 받은 데이터를 해당 utf-8로 인코딩, 한글 깨짐 방지. 
		request.setCharacterEncoding("utf-8");
		// 중요한 부분, 제일 기본, 입력폼에서, 파라미터 이름 기억나요?
		// 예) userID = lsy , passwd = 1234 담아서, 서버로 넘어옴. 
		// 서버에서는 request 인스턴스에 담겨진 해당 파라미터의 속성의 값을 가져오기. 
		//
		userID2 = request.getParameter("userID");
		passwd2 = request.getParameter("passwd");

		// mav : 뷰 + 데이터를 처리하는 인스턴스, 
		// result -> /test/result.jsp 를 가리킴 : 뷰 
		// 해당 뷰에서, 데이터를 사용하기 위한 목적으로 : addObject
		// userID2 이름으로, 값 userID=lsy
		// passwd2 이름으로, 값 passwd=1234
		mav.addObject("userID2", userID2);
		mav.addObject("passwd2", passwd2);
		mav.setViewName("result");
		return mav;
	}
	
	/*
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userID = "";
		String passwd = "";
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("utf-8");
		userID = request.getParameter("userID");
		passwd = request.getParameter("passwd");
		String viewName=getViewName(request);
		
		mav.addObject("userID", userID);
		mav.addObject("passwd", passwd);
		//mav.setViewName("result");
		mav.setViewName(viewName);
	    System.out.println("ViewName:"+viewName);
		return mav;
	}

	public ModelAndView memberInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
	    ModelAndView mav=new ModelAndView();
	    String id=request.getParameter("id");
	    String pwd=request.getParameter("pwd");
	    String name=request.getParameter("name");
	    String email=request.getParameter("email");

	    mav.addObject("id",id);
	    mav.addObject("pwd",pwd);
	    mav.addObject("name",name);
	    mav.addObject("email",email);
	    mav.setViewName("memberInfo");
	    return mav;
	}
	
	private  String getViewName(HttpServletRequest request) throws Exception {
	      String contextPath = request.getContextPath();
	      String uri = (String)request.getAttribute("javax.servlet.include.request_uri");
	      if(uri == null || uri.trim().equals("")) {
	         uri = request.getRequestURI();
	      }

	      int begin = 0;
	      if(!((contextPath==null)||("".equals(contextPath)))){
	         begin = contextPath.length();
	      }

	      int end;
	      if(uri.indexOf(";")!=-1){
	         end=uri.indexOf(";");
	      }else if(uri.indexOf("?")!=-1){
	         end=uri.indexOf("?");
	      }else{
	         end=uri.length();
	      }

	      String fileName=uri.substring(begin,end);
	      if(fileName.indexOf(".")!=-1){
	         fileName=fileName.substring(0,fileName.lastIndexOf("."));
	      }
	      if(fileName.lastIndexOf("/")!=-1){
	         fileName=fileName.substring(fileName.lastIndexOf("/"),fileName.length());
	      }
	      return fileName;
	   }

	*/
	
}
