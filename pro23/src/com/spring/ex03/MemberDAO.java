package com.spring.ex03;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.spring.ex01.MemberVO;

public class MemberDAO {
	public static SqlSessionFactory sqlMapper = null;

	private static SqlSessionFactory getInstance() {
		if (sqlMapper == null) {
			try {
				String resource = "mybatis/SqlMapConfig.xml";
				Reader reader = Resources.getResourceAsReader(resource);
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sqlMapper;
	}
	public List<MemberVO> selectAllMemberList() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List<MemberVO> memlist = null;
		memlist = session.selectList("mapper.member.selectAllMemberList");
		return memlist;
	}

	public MemberVO selectMemberById(String id){
		// selectMemberById 의 메서드에 인자값으로, 
		// 서블릿이라는 파일에서-> dao 라는 파일로 , 검색 키워드를 전달했다. 
	      sqlMapper=getInstance();
	SqlSession session=sqlMapper.openSession();
	// 디비에, 검색 조건을 같이 전달함. 
	// 앞에 비교하면, 앞에서, sql 조건문장만 전달 했고, 
	// 지금은 sql 문장 + 검색 조건(키워드) 같이 전달. 
	      MemberVO memberVO=session.selectOne("mapper.member.selectMemberById",id);
	      return memberVO;		
	   }

	public List<MemberVO> selectMemberByPwd(int pwd) {
	sqlMapper = getInstance();
	SqlSession session = sqlMapper.openSession();
	List<MemberVO> membersList = null;
	membersList= session.selectList("mapper.member.selectMemberByPwd", pwd);
	return membersList;
	}
	
	public List<MemberVO> selectMemberByName(String id){
		// selectMemberById 의 메서드에 인자값으로, 
		// 서블릿이라는 파일에서-> dao 라는 파일로 , 검색 키워드를 전달했다. 
	      sqlMapper=getInstance();
	SqlSession session=sqlMapper.openSession();
	// 디비에, 검색 조건을 같이 전달함. 
	// 앞에 비교하면, 앞에서, sql 조건문장만 전달 했고, 
	// 지금은 sql 문장 + 검색 조건(키워드) 같이 전달. 
		
			System.out.println("확인2 : 넘어온 키워드 값 vlaue : " + id);
	      List<MemberVO> memList = null;
	      memList=session.selectList("mapper.member.selectMemberByName",id);
	      return memList;		
	   }
	
	public List<MemberVO> selectMemberByEmail(String id){
		// selectMemberById 의 메서드에 인자값으로, 
		// 서블릿이라는 파일에서-> dao 라는 파일로 , 검색 키워드를 전달했다. 
	      sqlMapper=getInstance();
	SqlSession session=sqlMapper.openSession();
	// 디비에, 검색 조건을 같이 전달함. 
	// 앞에 비교하면, 앞에서, sql 조건문장만 전달 했고, 
	// 지금은 sql 문장 + 검색 조건(키워드) 같이 전달. 
		
			System.out.println("확인2 : 넘어온 키워드 값 vlaue : " + id);
	      List<MemberVO> memList = null;
	      memList=session.selectList("mapper.member.selectMemberByEmail",id);
	      return memList;		
	   }



}
