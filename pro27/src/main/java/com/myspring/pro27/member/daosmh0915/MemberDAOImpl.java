package com.myspring.pro27.member.daosmh0915;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.pro27.member.vo.MemberVO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllMemberList() throws DataAccessException {
		List<MemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		return membersList;
	}

	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.member.insertMember", memberVO);
		return result;
	}

	@Override
	public int deleteMember(String id) throws DataAccessException {
		int result = sqlSession.delete("mapper.member.deleteMember", id);
		return result;
	}
	
	@Override
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException{
		// ?´?¼?´?–¸?Š¸ -> ?„œë²? : ?°?´?„° ?•„?´?””?? ?Œ¨?Š¤?›Œ?“œ 
		  MemberVO vo = sqlSession.selectOne("mapper.member.loginById",memberVO);
		  // ?šŒ?›?´ ?ˆ?œ¼ë©?, ?›?˜ ?šŒ?›?˜ ? •ë³´ë?? ?‹¤ ê°?ì§?ê³? ?˜´. 
		return vo;
	}

	// ?•˜?‚˜?˜ ? •ë³´ë?? ì¡°íšŒ ?•  ?•Œ ?•„?š”?•œ ë©”ì„œ?“œ : selectOne, ?‹œ?Š¤?…œ ë©”ì„œ?“œ 
			// ì¡°ê±´, ì²«ë²ˆì§? ?¸?: member.xml ?˜ sql ë¬¸ì¥?˜ ?‹ë³? ?•„?´?”” 
			// ?‘ë²ˆì§¸ ?¸?: ?””ë¹„ì— ?„˜ê¸? ì¡°ê±´ ?ŒŒ?¼ë¯¸í„°(ë¬¸ì?—´), ?•œ ?šŒ?›?˜ ?•„?´?””ë¥? ? „?‹¬. 
			// where id = "?—¬ê¸°ì— ?‚¬?š©?  ?˜ˆ? •"
			@Override
			public MemberVO selectOneMember(String id) throws DataAccessException {
				 MemberVO membervo = null;
				 membervo = (MemberVO) sqlSession.selectOne("mapper.member.selectMemberById", id);
				return membervo;
			}

			// ?ˆ˜? • ? ?š©?•˜ê¸?.
			@Override
			public int updateMember(MemberVO memberVO) throws DataAccessException {
				int result = sqlSession.update("mapper.member.updateMember", memberVO);
				return result;
			}

}
