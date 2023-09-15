<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
   request.setCharacterEncoding("UTF-8");
%> 
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>결과창</title>
</head>
<body>
<h1>result.jsp 페이지입니다.</h1>
<table border="1" width="50%" align="center" >

   <tr align="center">
      <td>아이디</td>
      <td>비밀번호</td>
   </tr>
   <tr align="center">
   <!-- 서버에서 설정한 데이터를 , 뷰에서 가져와서 사용하는 예 -->
      <td>${userID2}</td>
      <td>${passwd2}</td>
   </tr>
</table>
</body>
</html>
