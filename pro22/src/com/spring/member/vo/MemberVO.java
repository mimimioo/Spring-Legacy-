package com.spring.member.vo;

import java.sql.Date;

// 파일 간에 데이터를 한곳에 모아서, 한번에 전달하기위한 객체 
// DTO(Data Transfer Object) = VO(Value Object)
public class MemberVO {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;

	public MemberVO() {
		
	}

	// 지금은 , 수동으로 게터/세터를 만들어서 사용하지만, 
	// 롬복이라는 라이브러리를 사용해서, 메모리에 올려두고 사용할 예정. 
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
	


}
