package com.spring.ex04;

import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.spring.ex01.MemberVO;

public class MemberDAO {
	// 2번째 동네 
	public static SqlSessionFactory sqlMapper = null;

	// 디비에 접근하는 기능을 리팩토링, 분리 작업했다. 자주 반복적으로 사용되니.
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
	      sqlMapper=getInstance();
	SqlSession session=sqlMapper.openSession();
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

	// 실제 2번째 동네에서 하는 업무인, 추가 작업. 
	// 포인트, 넘어온 데이터의 타입 문자열이 아니라 -> 인스턴스(객체), 참조형이라는 부분. 
	public int insertMember(MemberVO memberVO) {
		// 공통 업무, 디비 접근 하는 기능. 
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		// 상태변수, 값이 추가되었다면, 상태를 표시하는 변수. 
		int result = 0;
		// 디비에 sql 문장을 넘기는 업무, -> 3번째 동네인, member.xml 에서 외주를 맡기기
		// 포인트 , 2번째 매개변수, memberVO , 인스턴스라는 부분. 
		result = session.insert("mapper.member.insertMember", memberVO);
		session.commit();
		return result;
	}
	public int insertMember2(Map<String,String> memberMap){
        sqlMapper=getInstance();
        SqlSession session=sqlMapper.openSession();
        int result= result=session.insert("mapper.member.insertMember2",memberMap);
        session.commit();	
        return result;		
	}

	public int updateMember(MemberVO memberVO) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = session.update("mapper.member.updateMember", memberVO);
		session.commit();
		return result;
	}   

	
    public int deleteMember(String id) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = 0;
		result = session.delete("mapper.member.deleteMember", id);
		session.commit();
		return result;
    } 
    
    public List<MemberVO>  searchMember(MemberVO  memberVO){
        sqlMapper=getInstance();
        SqlSession session=sqlMapper.openSession();
        List list=session.selectList("mapper.member.searchMember",memberVO);
        return list;		
    } 

    public List<MemberVO>  foreachSelect(List nameList){
        sqlMapper=getInstance();
        SqlSession session=sqlMapper.openSession();
        // 실제 디비에 접근해서 작업 하는 부분, member.xml , 외주 보내기. 
        List list=session.selectList("mapper.member.foreachSelect",nameList);
        return list;		
    }
    // 동네 2번이고, 여기서는 , member.xml에 sql 문장을 매핑 하는 곳. 
    public int  foreachInsert(List memList){
        sqlMapper=getInstance();
        SqlSession session=sqlMapper.openSession();
        // 실제 작업, 외주 맡김. 
        int result = session.insert("mapper.member.foreachInsert",memList);
        session.commit();
        return result ;		
     }
    
    
    public List<MemberVO>  selectLike(String name){
        sqlMapper=getInstance();
        SqlSession session=sqlMapper.openSession();
        List list=session.selectList("mapper.member.selectLike",name);
        return list;		
    }


}
