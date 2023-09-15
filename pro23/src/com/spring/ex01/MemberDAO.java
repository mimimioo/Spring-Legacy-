package com.spring.ex01;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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
	/* 모델 클래스 버전 타입으로 결과를 반화는 예제 */
	/* 결과 타입을 memberVO 사용하건, resultMap , HashMap 타입으로 하건 동일함. */
	public List<MemberVO> selectAllMemberList() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List<MemberVO> memlist = null;
		memlist = session.selectList("mapper.member.selectAllMemberList");
		return memlist;
	}
	/* 타입 확인 하기 잠깐 보류 */
	// 타입이 잘못됨. List<HashMap<String, String>> : 잘못됨. 
//	 public List<MemberVO> selectAllMemberList2() { 
//		sqlMapper = getInstance(); 
//     	SqlSession session = sqlMapper.openSession();
//		List<MemberVO> memlist = null; 
//   	memlist = session.selectList("mapper.member.selectAllMemberList2"); 
//		return memlist; 
//	 }
	
}
