package com.spring.member.service;


import java.util.List;

import org.springframework.dao.DataAccessException;
// 인터페이스 - 이 걸 구현한 클래스 
public interface MemberService {
	public List listMembers() throws DataAccessException;
}
