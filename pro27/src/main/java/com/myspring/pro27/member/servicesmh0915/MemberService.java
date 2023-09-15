package com.myspring.pro27.member.servicesmh0915;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.pro27.member.vo.MemberVO;

public interface MemberService {
		 public MemberVO getOneMember(String id) throws DataAccessException;
		 public int updateMember(MemberVO membeVO) throws DataAccessException;
		 public List listMembers() throws DataAccessException;
		 public int addMember(MemberVO membeVO) throws DataAccessException;
		 public int removeMember(String id) throws DataAccessException;
	 public MemberVO login(MemberVO memberVO) throws Exception;
}
