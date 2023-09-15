package com.spring.ex01;

import java.sql.Date;

public class MemberVO {
	// 디비서버에 데이터를 가져오거나, 
	// 데이터를 입력을 하거나 했을 때, 
	// 하나의 인스턴스에 , 내용을 담아서 전달하기 위한
	// 용도의 인스턴스, 편의성을 위해서 사용함. 
	
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;

	public MemberVO() {
		
	}

	public MemberVO(String id, String pwd, String name, String email) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
	}

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
