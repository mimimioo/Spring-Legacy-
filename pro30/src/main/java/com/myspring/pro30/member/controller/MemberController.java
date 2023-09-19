package com.myspring.pro30.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.pro30.member.vo.MemberVO;

public interface MemberController {
	// 인터페이스 장점. 
		// 해당 메서드들의 명세표를 확인 -> 어떤 메서드들이 있는지 한눈에 파악하기 좋음. 
		// 규모가 커지면 관리가 좋음. 대신에 복잡도는 조금 올라감. 
		// 수정이 없죠?>-> 실습 과제 ?modMember
		// 수정폼으로 가는 로직 -> 애너테이션 기법으로 교체 작업
		public ModelAndView modMember(@RequestParam("id") String id,HttpServletRequest request, HttpServletResponse response) throws Exception;
		// 수정하는 로직 추가. -> 애너테이션 기법으로 교체 작업
		public ModelAndView updateMember(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
		
		public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception;
		// pro26 그대로 재사용.
		public ModelAndView addMember(@ModelAttribute("info") MemberVO memberVO,HttpServletRequest request, HttpServletResponse response) throws Exception;
		public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView login(@ModelAttribute("member") MemberVO member,
                              RedirectAttributes rAttr,
                              HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;
}