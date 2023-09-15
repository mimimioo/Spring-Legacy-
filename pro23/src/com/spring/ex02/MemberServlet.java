package com.spring.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mem2.do")
public class MemberServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		System.out.println("순서1: doGet 메서드 호출 ");
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)	throws  ServletException, IOException {
		System.out.println("순서2: doHandle 메서드 호출 ");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 웹 브라우저에서, 맵핑 주소: /mem2.do 
		// 이 파일 서블릿(컨트롤러)처럼 역할 중.
		// 의존성 주입(DI)-> 가져오기, 
		// 느스한 결합. 
		// 차이점, 시스템에 등록해서 쓰는 것이아니라.
		// 해당 파일에서만 실행이 되는 부분임. 
		
		MemberDAO dao = new MemberDAO();
		// 더미 디미 아이디 a1 이름 가져오는 경우
		// 정방향, 여기는 약식임. 
		// servlet(controller) -> dao -> mapper(mybatis)
		
		String name = dao.selectName();
		
		// 더미 디미 아이디 a2 패스워드 가져오는 경우
		int pwd = dao.selectPwd();
		
		// 더미 디미 아이디 a3 등록일 가져오기. 
		//selectDate() -> 없음. 임의로 이름을 설정.
		
		System.out.println("순서3: dao.selectDate() 메서드 호출전 ");
		String testDate = dao.selectDate();
		System.out.println("순서8: 역방향 시작, dao.selectDate() 메서드 호출후 ");
		
		System.out.println("순서9: 현재 뷰가 없어서, 자바스크립트 alert보여주기. ");
		PrintWriter pw = response.getWriter();
		pw.write("<script>");
		pw.write("alert(' 이름: " + name +"');");
		pw.write("alert(' 패스워드 : "+ pwd+"');");
		pw.write("alert(' 등록일 : "+ testDate+"');");
		pw.write("</script>");

	}
}
