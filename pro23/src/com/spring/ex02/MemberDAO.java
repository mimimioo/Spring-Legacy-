package com.spring.ex02;

import java.io.Reader;
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

	public String  selectName() {
		// 현재, 순서2번, -> member.xml 에 접근해서, 디비에 값에 접근 하고, 
		// 역순으로 가져와서, servlet에 전달하고, 
		// 뷰에 다시, 결과를 보여주는 역할. 
		
		// 반복, 디비 접근 위한 인스턴스
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		//session의 많은 메서드 중에서, 이번에는 하나의 값만 조회하기. 
		// 이름, 패스워드처럼 하나의 값만. 
		String name = session.selectOne("mapper.member.selectName");
		return name;
	} 
		
	public int  selectPwd() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int pwd = session.selectOne("mapper.member.selectPwd");
		return pwd;
	}
	
	//selectDate : 데이터 조회 해보기. 연습.
	public String  selectDate() {
		System.out.println("순서4: dao.selectDate() 메서드 호출중 ");
//		=================공통================
		// 디비에 접근 하기위한 도구로 사용됨.
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
//		=================공통================
		
		// 받는 날짜 변수 , 
		// member.xml 에 이동해서, selectDate 아이디가 없죠? , 만들기. 
		System.out.println("순서5: session.selectOne(\"mapper.member.selectDate\") 메서드 호출전 ");
		String testDate = session.selectOne("mapper.member.selectDate");
		System.out.println("순서6: session.selectOne(\"mapper.member.selectDate\") 메서드 호출후 ");
		System.out.println("순서7: 역방향 시작, MemberDAO ");
		return testDate;
	}

}
