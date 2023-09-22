package com.spring.ex02;

public class LoginVO {
	private String userID;
	private String userName;
	private String email;
	
	//지금은, 수동 오버라이딩해서, 
	// 게터 세터를 설정하지만, 
	// 롬복 라이브러리 이용해서, 
	// 메모리상에, 게터,세터를 자동을 설정 기능.
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
