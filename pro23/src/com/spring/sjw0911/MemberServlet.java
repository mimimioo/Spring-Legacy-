package com.spring.sjw0911;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/memSjw.do")
//매핑 주소가 중복된 상태에서, 서버를 시작를 하니, 오류가 나는 것 같음. 
public class MemberServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//response.setContentType("text/html;charset=utf-8");
		MemberDAO dao = new MemberDAO();
		MemberVO memberVO = new MemberVO();
		
		
		// 만약, 마이바티스라는 디비 프레임 워크를 사용을 안하게 되면, 
		// 여기에, 1)디비에 연결하는 코드, 2) 디비를 불러오는 sql 3) 닫는 코드
		//원본
		//List<MemberVO> membersList = dao.selectAllMemberList();
		//과제
		//List<MemberVO> membersList2 = dao.selectAllMemberList2();
		
		
		String action = request.getParameter("action");
		String nextPage = "";

		// action== null , 검색 조건 없어서, 전체 조회. 
		if (action== null || action.equals("listMembers")) {
			// 전체 조회를 하는 dao 메서드, 디비에서 전체 값 조회를 가지고 오기.
			List<MemberVO> membersList = dao.selectAllMemberList();
			// request  인스턴스에, 해당 조회된 값을 담아두기. 
			request.setAttribute("membersList", membersList);
			// 결과 뷰에 보여주기. 
			nextPage = "test02_sjw/listMembers.jsp";
			// 아이디 조건으로 검색하기. 
		} else if (action.equals("selectMemberById")) {
			String id = request.getParameter("value");
			// value -> 사용자가 검색하기 위한 키워드를 -> id 라는 변수에 담아서 전달하구나.
			memberVO = dao.selectMemberById(id);
			request.setAttribute("member", memberVO);
			nextPage = "test02_sjw/memberInfo.jsp";
		} else if (action.equals("selectMemberByPwd")) {
			int pwd = Integer.parseInt(request.getParameter("value"));
			List<MemberVO> membersList = dao.selectMemberByPwd(pwd);
			request.setAttribute("membersList", membersList);
			nextPage = "test02_sjw/listMembers.jsp";
		}else if (action.equals("selectMemberByName")) {
			String name = request.getParameter("value");
			List<MemberVO> membersList = dao.selectMemberByName(name);
			request.setAttribute("membersList", membersList);
			nextPage = "test02_sjw/listMembers.jsp";
		}
		else if (action.equals("selectMemberByEmail")) {
			String email = request.getParameter("value");
			List<MemberVO> membersList = dao.selectMemberByEmail(email);
			request.setAttribute("membersList", membersList);
			nextPage = "test02_sjw/listMembers.jsp";
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);  
		dispatch.forward(request, response);
		
		
		//List<HashMap<String, String>> membersList = dao.selectAllMemberList();
		/*
		 * request.setAttribute("membersList2", membersList2); RequestDispatcher
		 * dispatch = request.getRequestDispatcher("test01/listMembersLsy0911.jsp");
		 * dispatch.forward(request, response);
		 */
	}
}
