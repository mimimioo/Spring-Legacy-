package com.myspring.pro27.member.daosmh0915;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.pro27.member.vo.MemberVO;

public interface MemberDAO {
	// 추�?, ?�� ?��?��?�� ?���? �??��?���? 메서?�� 추�?
	 public MemberVO selectOneMember(String id) throws DataAccessException;
	 
	 //추�?, ?��?�� 반영?���?.
	 public int updateMember(MemberVO memberVO) throws DataAccessException ;
	 
	 public List selectAllMemberList() throws DataAccessException;
	 public int insertMember(MemberVO memberVO) throws DataAccessException ;
	 public int deleteMember(String id) throws DataAccessException;
	 // ?��?�� ?���? ?��?��
	 public MemberVO loginById(MemberVO memberVO) throws DataAccessException;

}
