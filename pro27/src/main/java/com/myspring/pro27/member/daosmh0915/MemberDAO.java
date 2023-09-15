package com.myspring.pro27.member.daosmh0915;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.pro27.member.vo.MemberVO;

public interface MemberDAO {
	// ì¶”ê?, ?•œ ?šŒ?›?˜ ? •ë³? ê°?? ¸?˜¤ê¸? ë©”ì„œ?“œ ì¶”ê?
	 public MemberVO selectOneMember(String id) throws DataAccessException;
	 
	 //ì¶”ê?, ?ˆ˜? • ë°˜ì˜?•˜ê¸?.
	 public int updateMember(MemberVO memberVO) throws DataAccessException ;
	 
	 public List selectAllMemberList() throws DataAccessException;
	 public int insertMember(MemberVO memberVO) throws DataAccessException ;
	 public int deleteMember(String id) throws DataAccessException;
	 // ? ?‹œ ?‘ê³? ?‘?—…
	 public MemberVO loginById(MemberVO memberVO) throws DataAccessException;

}
