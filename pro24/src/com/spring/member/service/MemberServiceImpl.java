package com.spring.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.member.dao.MemberDAO;
import com.spring.member.vo.MemberVO;

/*@Transactional(propagation=Propagation.REQUIRED) */
// 동네 2번-2 , 
public class MemberServiceImpl  implements MemberService{
	// 실제 작업은 외주 , 동네 3번으로 가기위한 인스턴스 작업.
	// DI, 세터 형식으로 초기화 했고, xml 설정파일 정의 된 대로 초기화 합니다. 
	   private MemberDAO memberDAO;
	   public void setMemberDAO(MemberDAO memberDAO){
	      this.memberDAO = memberDAO;
	   }

	   // 기능 
	   @Override
	   public List listMembers() throws DataAccessException {
		   // 임시 데이터를 담기 위한 인스턴스 
	      List membersList = null;
	      // 실제 작업, 외주  동네 3번으로 갑니다. 
	      membersList = memberDAO.selectAllMemberList();
	      // 동네 3번 , 4번, DB 찍고 돌아 옴. 
	      return membersList;
	   }

	   @Override
	   public int addMember(MemberVO memberVO) throws DataAccessException {
	     return memberDAO.insertMember(memberVO);
	   }


	   @Override
	   public int removeMember(String id) throws DataAccessException {
	      return memberDAO.deleteMember(id);
	   }
}
