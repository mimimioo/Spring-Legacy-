package com.spring.ex01;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mem.do")
// 현재, 이구조는 jsp 모델2 형식이니, 
// 우리는 스프링 MVC 구조로 , 마이바티스와 연동해서 작업할 예정이니, 
// 이구조는 참고만 해주세요.
public class MemberServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		MemberDAO dao = new MemberDAO();
		// 만약, 마이바티스라는 디비 프레임 워크를 사용을 안하게 되면, 
		// 여기에, 1)디비에 연결하는 코드, 2) 디비를 불러오는 sql 3) 닫는 코드 
//		List<MemberVO> membersList = dao.selectAllMemberList();
		/* HashMap 타입 확인하기. */
		// 타입 이 틀림 수정 -> 설정해서, 모델 클래스 설정. : 
		// <typeAlias type="com.spring.ex01.MemberVO" alias="memberVO"/>
//		List<HashMap<String, String>> membersList2 = dao.selectAllMemberList();
		
		//수정 된 부분. 
		List<MemberVO> membersList = dao.selectAllMemberList();
		request.setAttribute("membersList", membersList);
		RequestDispatcher dispatch = request.getRequestDispatcher("test01/listMembers.jsp");
		dispatch.forward(request, response);
	}
}
