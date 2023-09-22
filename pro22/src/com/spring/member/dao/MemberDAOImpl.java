package com.spring.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;



import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.member.vo.MemberVO;

// 구현한 클래스, 
public class MemberDAOImpl implements MemberDAO {
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
//	일단 패스 -> https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/JdbcTemplate.html
// 공식 문서, 생성자에 dataSource 부분이 정의가 되어 있음. 버그라서, 잠시 보류.
		// 대신에 마이 바티스에서 디비 작업을 진행 .
		this.jdbcTemplate = new JdbcTemplate(dataSource);
//		this.jdbcTemplate = new JdbcTemplate();
	}

	// 실제 디비에 접근해서 , 데이터 가져오는 부분.
	@Override
	public List selectAllMembers() throws DataAccessException {
		String query = "select id,pwd,name,email,joinDate" + " from t_member " + " order by joinDate desc";
		List membersList = new ArrayList();

		// 데이터 베이스를 가지고와서, 
		// 모델 객체에 담아서, 전달하기위한 작업. 
		membersList = this.jdbcTemplate.query(query, new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				// 모델 임시 객체를 만들어서 
				MemberVO memberVO = new MemberVO();
				// 각 데이터 4개를 해당 인스턴스에 담는 작업. 
				memberVO.setId(rs.getString("id"));
				memberVO.setPwd(rs.getString("pwd"));
				memberVO.setName(rs.getString("name"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setJoinDate(rs.getDate("joinDate"));
				return memberVO;
			}
		});
		return membersList;
	}

	@Override
	public int addMember(MemberVO memberVO) throws DataAccessException {
		String id = memberVO.getId();
		String pwd = memberVO.getPwd();
		String name = memberVO.getName();
		String email = memberVO.getEmail();
		String query = "insert into t_member(id,pwd, name,email) values  ("
		                 + "'" + id + "' ,"
	 	                 + "'" + pwd + "' ,"
		                 + "'" + name + "' ,"
		                 + "'" + email + "') ";
		System.out.println(query);
		int result = jdbcTemplate.update(query);
		System.out.println(result);
		return result;
	}

}
