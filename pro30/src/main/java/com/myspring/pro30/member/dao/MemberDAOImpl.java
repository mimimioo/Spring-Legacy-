package com.myspring.pro30.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.pro30.member.vo.MemberVO;

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
		// 클라이언트 -> 서버 : 데이터 아이디와 패스워드 
		  MemberVO vo = sqlSession.selectOne("mapper.member.loginById",memberVO);
		  // 회원이 있으면, 원래 회원의 정보를 다 가지고 옴. 
		return vo;
	}

	// 하나의 정보를 조회 할 때 필요한 메서드 : selectOne, 시스템 메서드 
			// 조건, 첫번째 인자: member.xml 의 sql 문장의 식별 아이디 
			// 두번째 인자: 디비에 넘길 조건 파라미터(문자열), 한 회원의 아이디를 전달. 
			// where id = "여기에 사용될 예정"
			@Override
			public MemberVO selectOneMember(String id) throws DataAccessException {
				 MemberVO membervo = null;
				 membervo = (MemberVO) sqlSession.selectOne("mapper.member.selectMemberById", id);
				return membervo;
			}

			// 수정 적용하기.
			@Override
			public int updateMember(MemberVO memberVO) throws DataAccessException {
				int result = sqlSession.update("mapper.member.updateMember", memberVO);
				return result;
			}

}
