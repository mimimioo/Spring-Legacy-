package com.spring.ex04;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.ex01.MemberVO;

@WebServlet("/mem4.do")
public class MemberServlet extends HttpServlet {
	//첫번째 동네, 클라이언트(웹 브라우저)로 부터, 입력된 정보를 4개를 -> 하나의 모델 클래스(박스)
	// 에 담아서, 2번째 동네에 전달 할 예정. 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		// 2번째 동네의 기능을 사용하기위한 인스턴스.
		MemberDAO dao = new MemberDAO();
		// 클라이언트로 부터 받은 정보를 하나의 모델 클래스에 담기 위한 임시 인스턴스.
		MemberVO memberVO = new MemberVO();
		// 클라이언트로 부터 넘어온 동작이 무엇을 할 것인가를 판단하는 변수. 
		String action = request.getParameter("action");
		// 디비에 원하는 기능을 다 수행후, 결과를 어디에 보여줄것인지를 정하는 뷰설정부분. 
		String nextPage = "";

		// 앞단에서 넘어온 행위를 분기문. 조회, 입력, 검색, 수정, 삭제 등등. 
		// 검색
		if (action == null || action.equals("listMembers")) {
			List<MemberVO> membersList = dao.selectAllMemberList();
			request.setAttribute("membersList", membersList);
			nextPage = "test03/listMembers.jsp";
			// 아이디 조건으로 검색.
		} else if (action.equals("selectMemberById")) {
			String id = request.getParameter("value");
			memberVO = dao.selectMemberById(id);
			request.setAttribute("member", memberVO);
			nextPage = "test03/memberInfo.jsp";
			// 패스워드 조건으로 검색.
		} else if (action.equals("selectMemberByPwd")) {
			int pwd = Integer.parseInt(request.getParameter("value"));
			List<MemberVO> membersList = dao.selectMemberByPwd(pwd);
			request.setAttribute("membersList", membersList);
			nextPage = "test03/listMembers.jsp";
			// 4가지 정보를 입력하는 기능. 
		}else if(action.equals("insertMember")) {
			// 입력폼에서 넘어온 4가지 정보를 각각 , 가져와서, 다시 임시 변수에 재할당(메모리)
			String id=request.getParameter("id");
            String pwd=request.getParameter("pwd");
            String name=request.getParameter("name");
            String email = request.getParameter("email");
            // 임시 모델 클래스의 인스턴스에 담기 (박스에 담기 )
            memberVO.setId(id);
            memberVO.setPwd(pwd);
            memberVO.setName(name);
            memberVO.setEmail(email);
            // 실제로 , 다른 동네에 외주 맡기기, 디비에 저장하는 기능은 
            // 다른 동네의 할일이라서, 
            dao.insertMember(memberVO);
            // 다른 동네에 외주를 맡겨서, 디비(오라클) 입력을 다하고 돌아온 후, 
            // 결과 뷰를 할당하는 내용. 
            nextPage="/mem4.do?action=listMembers";
       }else if(action.equals("insertMember2")) {
    	   // 똑같은 구조인데, 타입만 HashMap 타입. 
           String id=request.getParameter("id");
           String pwd=request.getParameter("pwd");
           String name=request.getParameter("name");
           String email = request.getParameter("email");    
           // 모델 클래스를 , HashMap 타입으로 변경해서, 디비로 전달하는 과정. 
           // 포인트, 전달하는 박스의 타입이 변경이 되었다. 내용물은 그대로. 
           Map<String, String> memberMap=new HashMap<String, String>();
           memberMap.put("id", id);
           memberMap.put("pwd", pwd);
           memberMap.put("name", name);
           memberMap.put("email", email);
           // 실제 데이터를 전달하는 작업, 본인 작업을 못해서, 외주 동네2번. 
           dao.insertMember2(memberMap);
           nextPage="/mem4.do?action=listMembers";
      } // 수정폼으로 이동할 로직
       else if(action.equals("modMember")){
           String id=request.getParameter("id");
         
           // 뷰에서, 수정 하는 폼 가기위해서, 해당 유저의 아이디를 받아서 왔고, 
           request.setAttribute("user_id",id);
           
           nextPage="/test03/modMember.jsp";     
       }
		// 회원가입폼으로 이동할 로직
       else if(action.equals("memberForm")){

           
           nextPage="/test03/memberForm.jsp";     
       }
       else if(action.equals("updateMember")){
          String id=request.getParameter("id");
          String pwd=request.getParameter("pwd");
          String name=request.getParameter("name");
          String email = request.getParameter("email");
          memberVO.setId(id);
          memberVO.setPwd(pwd);
          memberVO.setName(name);
          memberVO.setEmail(email);
          dao.updateMember(memberVO);
          nextPage="/mem4.do?action=listMembers";     
      }else if(action.equals("deleteMember")){
  	      String id=request.getParameter("id");
	      dao.deleteMember(id);
	      nextPage="/mem4.do?action=listMembers";
      }else if(action.equals("searchMember")){
          String name=request.getParameter("name");
          String email=request.getParameter("email");
          memberVO.setName(name);
          memberVO.setEmail(email);
          List<MemberVO> membersList =dao.searchMember(memberVO);
          request.setAttribute("membersList",membersList);
          nextPage="test03/listMembers.jsp";
       }else if(action.equals("foreachSelect")) {
    	   // foreachSelect 라는 id 이름으로 , 검색 조건의 In 연산자를 활용하는 연습. 
    	   // 검색 조건의 목록을 낱개로 보내는게 아니라, 리스트 타입의 ArrayList 형으로 
    	   // 전달함. 
		  List<String> nameList = new ArrayList<String>();
		  nameList.add("abc1");
		  nameList.add("abc2");
		  nameList.add("abc3");
		  // 외주 맡기기, 동네2번으로 
		  List<MemberVO> membersList=dao.foreachSelect(nameList);
		  request.setAttribute("membersList",membersList);
		  nextPage="test03/listMembers.jsp";
	   }else if(action.equals("foreachInsert")) {
		   // foreachInsert 이용해서, 전달시, 앞에서는 문자열을 요소로 , 다음 동네에 전달.
		   // 목록의 요소로 인스턴스를 담아서 전달합니다. 
          List<MemberVO> memList = new ArrayList<MemberVO>();
          memList.add(new MemberVO("m1", "1234", "m1", "m1@test.com"));
          memList.add(new MemberVO("m2", "1234", "m2", "m2@test.com"));
          memList.add(new MemberVO("m3", "1234", "m3", "m3@test.com"));
          // 실제 작업이지만, 외주 맡기고 
          int result=dao.foreachInsert(memList);
          nextPage="/mem4.do?action=listMembers";
	    }else if(action.equals("selectLike")) {
	      String name="�浿";
		  List<MemberVO> membersList=dao.selectLike(name);
		  request.setAttribute("membersList",membersList);
		  nextPage="test03/listMembers.jsp";
	   }
		
	   RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);  
	   dispatch.forward(request, response);


	}
}
