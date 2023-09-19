package com.myspring.pro29.ex03.lsy0918;

public class ArticleVO {
	private int articleNO;
	private String writer;
	private String title;
	private String content;
	
	
	public int getArticleNO() {
		return articleNO;
	}
	public void setArticleNO(int articleNO) {
		this.articleNO = articleNO;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	@Override
	// 클라이언트로 부터 전달된 데이터 객체 -> JSON 변환
	// 서버에 데이터가 전달되었고, 
	// 전달된 데이터를 자동으로 매핑해서, 
	// 서버에 정의된 모델클래스의 toString 이용해서, 각 필드를 한번에 출력 한 예임. 
	public String toString() {
		String info = "";
		info += "\n"+articleNO+"\n "
			        +writer+"\n"
			        +title+"\n"
			        +content;
			   
		return info;
	}
}
