package com.spring.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import com.spring.member.vo.MemberVO;

public interface MemberService {
	// 어떤 기능들이 있는지 한눈에 보기 쉽다. 
	//추가, 한 회원의 정보 가져오기 메서드 추가. : getOneMember
	 public MemberVO getOneMember(String id) throws DataAccessException;
	 // 추가. updateMember
	 public int updateMember(MemberVO membeVO) throws DataAccessException;
	 //
	 public List listMembers() throws DataAccessException;
	 public int addMember(MemberVO membeVO) throws DataAccessException;
	 public int removeMember(String id) throws DataAccessException;


}
