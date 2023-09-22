package com.spring.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.member.dao.MemberDAO;

public class MemberServiceImpl implements MemberService {
	// DI, 느슨한 결합, 인터페이스 형으로 하는 참조형 변수를 포함관계
	// 선언만 
	private MemberDAO memberDAO;

	// 초기화를 , 세터 메서드를 이용해서. 
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public List listMembers() throws DataAccessException {
		List membersList = null;
		// 서비스 -> DAO, 데이터에 접근하기 위한 작업. 
		membersList = memberDAO.selectAllMembers();
		return membersList;
	}

}
