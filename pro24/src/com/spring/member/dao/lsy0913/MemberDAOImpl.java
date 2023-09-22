package com.spring.member.dao.lsy0913;

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
