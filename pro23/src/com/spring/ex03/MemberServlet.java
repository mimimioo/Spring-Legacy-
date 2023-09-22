package com.spring.ex03;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.ex01.MemberVO;

@WebServlet("/mem3.do")
// 검색 조건에서, 키워드를 서버에서 -> 디비 까지 
// 어떻게 전달하는지 부분 확인. 요점. !!
public class MemberServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		MemberDAO dao = new MemberDAO();
		MemberVO memberVO = new MemberVO();
		
		//action : 검색의 조건, 1) 전체 2) 아이디 3) 비밀번호  연습용.
//		  <option value="listMembers" >전체</option>
//		     <option value="selectMemberById" >아이디</option>
//		     <option  value="selectMemberByPwd">비밀번호</option>
		String action = request.getParameter("action");
		String nextPage = "";

		// action== null , 검색 조건 없어서, 전체 조회. 
		if (action== null || action.equals("listMembers")) {
			// 전체 조회를 하는 dao 메서드, 디비에서 전체 값 조회를 가지고 오기.
			List<MemberVO> membersList = dao.selectAllMemberList();
			// request  인스턴스에, 해당 조회된 값을 담아두기. 
			request.setAttribute("membersList", membersList);
			// 결과 뷰에 보여주기. 
			nextPage = "test02/listMembers.jsp";
			// 아이디 조건으로 검색하기. 
		} else if (action.equals("selectMemberById")) {
			String id = request.getParameter("value");
			// value -> 사용자가 검색하기 위한 키워드를 -> id 라는 변수에 담아서 전달하구나.
			memberVO = dao.selectMemberById(id);
			request.setAttribute("member", memberVO);
			nextPage = "test02/memberInfo.jsp";
		} else if (action.equals("selectMemberByPwd")) {
			int pwd = Integer.parseInt(request.getParameter("value"));
			List<MemberVO> membersList = dao.selectMemberByPwd(pwd);
			request.setAttribute("membersList", membersList);
			nextPage = "test02/listMembers.jsp";
			// 위에 패스워드를 이용해서 검색하는 조건을 벤치마킹
		}else if (action.equals("selectMemberByName")) {
			String id = request.getParameter("value");
			System.out.println("확인1 : 넘어온 키워드 값 value의 값 조회 : "+ id);
			// value -> 사용자가 검색하기 위한 키워드를 -> id 라는 변수에 담아서 전달하구나.
			// 변경
			List<MemberVO> membersList = dao.selectMemberByName(id);
			// 갔다와서 다시 보자.
			request.setAttribute("membersList", membersList);
			nextPage = "test02/listMembers.jsp";
		}
		
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);  
		dispatch.forward(request, response);


	}
}
