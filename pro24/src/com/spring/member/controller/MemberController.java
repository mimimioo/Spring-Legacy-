package com.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.web.servlet.ModelAndView;
// 동네1번의 인터페이스 , 현재 구조가 세트, 1)인터페이스 2) 인터페이스 구현한 클래스 
public interface MemberController {
	// 인터페이스 장점. 
	// 해당 메서드들의 명세표를 확인 -> 어떤 메서드들이 있는지 한눈에 파악하기 좋음. 
	// 규모가 커지면 관리가 좋음. 대신에 복잡도는 조금 올라감. 
	// 수정이 없죠?>-> 실습 과제 ?
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView addMember(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView removeMember(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
