package com.spring.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;

import com.spring.member.vo.MemberVO;

// 동네 3-2 , 실제 sql 접근을 하기위한 작업. 
public class MemberDAOImpl implements MemberDAO {
	// 실제 작업 동네 4 외주, 사전 작업. 해당 인스턴스 초기화 작업, 설정 파일의 정의대로 작업함. 
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	// 기능 
	@Override
	public List selectAllMemberList() throws DataAccessException {
		// 임시로 담을 리스트 
		List<MemberVO> membersList = null;
		// sqlSession , 여러 다양한 메서드가 있다. 조회, 추가, 수정, 삭제 등. 
		// 실제 작업, 외주 , 동네 4번으로 
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		// 동네 4번, 디비 갔다가 돌와 온 결과. 
		return membersList;
	}

	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.member.insertMember", memberVO);
		return result;
	}

	@Override
	public int deleteMember(String id) throws DataAccessException {
		int result =  sqlSession.delete("mapper.member.deleteMember", id);
		return result;
	}
}
