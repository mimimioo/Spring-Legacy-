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
		// ?��?��?��?��?�� -> ?���? : ?��?��?�� ?��?��?��?? ?��?��?��?�� 
		  MemberVO vo = sqlSession.selectOne("mapper.member.loginById",memberVO);
		  // ?��?��?�� ?��?���?, ?��?�� ?��?��?�� ?��보�?? ?�� �?�?�? ?��. 
		return vo;
	}

	// ?��?��?�� ?��보�?? 조회 ?�� ?�� ?��?��?�� 메서?�� : selectOne, ?��?��?�� 메서?�� 
			// 조건, 첫번�? ?��?��: member.xml ?�� sql 문장?�� ?���? ?��?��?�� 
			// ?��번째 ?��?��: ?��비에 ?���? 조건 ?��?��미터(문자?��), ?�� ?��?��?�� ?��?��?���? ?��?��. 
			// where id = "?��기에 ?��?��?�� ?��?��"
			@Override
			public MemberVO selectOneMember(String id) throws DataAccessException {
				 MemberVO membervo = null;
				 membervo = (MemberVO) sqlSession.selectOne("mapper.member.selectMemberById", id);
				return membervo;
			}

			// ?��?�� ?��?��?���?.
			@Override
			public int updateMember(MemberVO memberVO) throws DataAccessException {
				int result = sqlSession.update("mapper.member.updateMember", memberVO);
				return result;
			}

}
