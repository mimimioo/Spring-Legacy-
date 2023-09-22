package com.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

// 인터페이스의 장점, 추상화, 다형성 . 자바에서 : 4대 지향점, APIE
public interface MemberController {
	// 추상 메서드가 선언.
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
