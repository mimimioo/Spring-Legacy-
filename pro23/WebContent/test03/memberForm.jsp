<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 백 (서버)의 주소를 , 다 입력보다, taglib 주소를 쉽게 알아냄. 미리 변수에 등록해서 사용함 -->
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
   request.setCharacterEncoding("UTF-8");
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입창</title>
<style>
   .text_center{
     text-align:center;
   }
</style>
</head>
<body>
<!-- get, post 방식의 차이점, get 길이 제한, 보안상 url 주소다 남음. 
이런 부분 고려해서 작업, 회원가입 id,pw 민감정보 있어서, post 한다.  -->
<!-- 뷰 -> 서버에 전달하는데 -> 수신호를 action 문자열에 담아서 서버에 전달.
action=insertMember -->
<!-- name 별로 서버에서 일단 각각 가져오는 부분에 이용됨. -> id,pwd,name,email. --> 
	<%-- <form method="post"   action="${contextPath}/mem4.do?action=insertMember"> --%>
	<form method="post"   action="${contextPath}/mem4.do?action=insertMember2">
	<h1  class="text_center">회원 가입창</h1>
	<table  align="center">
	   <tr>
	      <td width="200"><p align="right">아이디</td>
	      <td width="400"><input type="text" name="id"></td>
	   </tr>
	   <tr>
	      <td width="200"><p align="right">비밀번호</td>
	      <td width="400"><input type="password" name="pwd"></td>
	    </tr>
	    <tr>
	       <td width="200"><p align="right">이름</td>
	       <td width="400"><p><input type="text" name="name"></td>
	    </tr>
	    <tr>
	       <td width="200"><p align="right">이메일</td>
	       <td width="400"><p><input type="text" name="email"></td>
	    </tr>
	    <tr>
	       <td width="200"><p>&nbsp;</p></td>
	       <td width="400"><input type="submit" value="가입하기"><input type="reset" value="다시입력"></td>
	    </tr>
	</table>
	</form>
</body>
