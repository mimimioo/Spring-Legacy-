<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>회원 검색창</title>
</head>
<body>
<!-- 입력 에서는 검색하고 싶은 키워드 value 변수에 담기.
검색 조건은 action 변수에 담기.
서버에 전달은, 서버 호스트 프로토콜 프로젝트 이름 등/mem3.do -->
 <form action="${pageContext.request.contextPath}/mem3.do">
   입력 : <input  type="text" name="value" />
   <select name="action">
     <option value="listMembers" >전체</option>
     <option value="selectMemberById" >아이디</option>
     <option  value="selectMemberByPwd">비밀번호</option>
     <option  value="selectMemberByName">이름</option>
   </select> <br>
   <input type="submit" value="검색"  />
</form>   
</body>
</html>
