package com.spring.member.service.lsy0913;

import java.util.List;

import org.springframework.dao.DataAccessException;
import com.spring.member.vo.MemberVO;

//동네 2번, 서비스, 실제적인 비지니스 로직들의 모음. 
// 인터페이스, 앞의 컨트롤러에서 인터페이스 장점등과 같은 이유. 참고.
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
