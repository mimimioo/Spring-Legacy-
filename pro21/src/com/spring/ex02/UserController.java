package com.spring.ex02;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class UserController extends MultiActionController {
	// login 메서드 , 반환 타입: ModelAndView
	// 매개변수 , 반환 타입: HttpServletRequest, HttpServletResponse
	// 클라이언트, 서버 간에 통신상 주고 받기 위한 전달 매개체.
	/*
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
	*/
	
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userID = "";
		String passwd = "";
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("utf-8");
		userID = request.getParameter("userID");
		passwd = request.getParameter("passwd");
		// 앞에서는, 해당 뷰를 result 라고 해서, 그대로 결과뷰를 알려줬음. 
		// getViewName , 메서드가 , 특정 파일명을 추출하는 메서드
		// viewName = /login
		String viewName=getViewName(request);
		
		// 서버 -> 결과 뷰에 데이터 전달
		// 키: userID , 값 : userID 라는 변수에 담긴 값.
		mav.addObject("userID", userID);
		mav.addObject("passwd", passwd);
		//mav.setViewName("result");
		mav.setViewName(viewName);
	    System.out.println("ViewName:"+viewName);
		return mav;
	}

	//	<prop key="/test/memberInfo.do">memberInfo</prop> 
	// 동일한 메서드를 사용하게끔 해주는 도구. 
	public ModelAndView memberInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		// 모델, 뷰를 같이 전달하는 인스턴스 
	    ModelAndView mav=new ModelAndView();
	    // 지금은, 각각 개별로 , 클라이언트로 부터 받은 데이터를 받지만, 
	    // 조금 있다, 특정 DTO라는 모델 인스턴스에 한번에 받을 예정. 
	    String id=request.getParameter("id");
	    String pwd=request.getParameter("pwd");
	    String name=request.getParameter("name");
	    String email=request.getParameter("email");

	    // 서버 -> 결과 뷰 , 데이터 전달. 
	    mav.addObject("id",id);
	    mav.addObject("pwd",pwd);
	    mav.addObject("name",name);
	    mav.addObject("email",email);
	    //서버 -> 결과 뷰 , 뷰를 선택하는 항목. 
	    mav.setViewName("memberInfo");
	    return mav;
	}
	
	// getViewName : 특정 파일명을 추출하는 메서드
	// 입력 값: /pro21/test/login.do
	// 결과 : /login 
	private  String getViewName(HttpServletRequest request) throws Exception {
		// 서버 주소 및 (프로토콜, 서버의 주소 , 포트 번호, 프로젝트 명)
	      String contextPath = request.getContextPath();
	      // 결과 : /pro21
	      System.out.println("contextPath의 내용이 뭐야? : " +contextPath );
	      String uri = (String)request.getAttribute("javax.servlet.include.request_uri");
	      // 결과 : null
	      System.out.println("uri의 내용이 뭐야? : " +uri );
	      if(uri == null || uri.trim().equals("")) {
	         uri = request.getRequestURI();
	      }
	      // 결과 : pro21/test/login.do
	      System.out.println("uri2 의 내용이 뭐야? : " +uri );

	      //
	      int begin = 0;
	      // contextPath 1) null , 2) 공백 이 아니면 실행한다.
	      if(!((contextPath==null)||("".equals(contextPath)))){
	    	  // contextPath 의 문자열의 길이 
	    	  System.out.println("contextPath 의 내용이 뭐야? : " +contextPath );
	    	  System.out.println("contextPath 의 길이 뭐야? : " +contextPath.length() );
	    	  // 결과 : 6
	    	  // begin = 6
	         begin = contextPath.length();
	      }

	      int end;
	      // 결과 uri : pro21/test/login.do
	      // ; 의 값이 있다면 수행하겠다.
	      if(uri.indexOf(";")!=-1){
	    	  
	         end=uri.indexOf(";");
	         // ? 의 값이 있다면 수행하겠다.
	         System.out.println("uri.indexOf(\";\")  end뭐야? : " +end );
	      }else if(uri.indexOf("?")!=-1){
	         end=uri.indexOf("?");
	         System.out.println("uri.indexOf(\"?\")  end뭐야? : " +end );
	      }else{
	    	  //그 밖에 
	         end=uri.length();
	         System.out.println("그 밖에 end=uri.length(); end뭐야? : " +end );
	         
	      }

	      // 특정 문자열에서, 특정 길이 만큼 추출하는 작업 
	      // begin : 6 , end : 20 
	      // 인덱스 위치가 6부터 19까지의 문자열을 가져오기. 
	      String fileName=uri.substring(begin,end);
	      System.out.println("substring전 결과, uri.substring(begin,end); 뭐야? : " +uri );
	      System.out.println("substring후 결과, fileName 뭐야? : " +fileName );
	      
	      // 결과 uri.substring(6,20); : /test/login.do
	      if(fileName.indexOf(".")!=-1){
	    	  // . 있다면 수행함. 
	    	  System.out.println("=======fileName.indexOf(\".\")!=-1==============");
	    	  System.out.println("fileName.lastIndexOf(\".\")  결과, 뭐야(숫자)? : " +fileName.lastIndexOf(".") );
	         fileName=fileName.substring(0,fileName.lastIndexOf("."));
	         System.out.println("fileName.substring(0,fileName.lastIndexOf(\".\")) 실행 후 결과, 뭐야? : " +fileName );
	         
	      }
	      if(fileName.lastIndexOf("/")!=-1){
	    	// / 있다면 수행함. 
	    	  System.out.println("=======fileName.lastIndexOf(\"/\")!=-1==============");
	    	  System.out.println("fileName.lastIndexOf(\"/\")  결과, 뭐야(숫자)? : " +fileName.lastIndexOf("/") );
	    	  System.out.println("=========fileName.lastIndexOf(\"/\")!=-1=============");
	         fileName=fileName.substring(fileName.lastIndexOf("/"),fileName.length());
	         System.out.println("fileName.substring(fileName.lastIndexOf(\"/\"),fileName.length()); 실행 후 결과, 뭐야? : " +fileName );
	      }
	      System.out.println("최종 결과 fileName : " +fileName );
	      return fileName;
	   }

	
	
}
