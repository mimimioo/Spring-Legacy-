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
      <td>이름</td>
      <td>이메일</td>
      <td>패스워드 </td>
   </tr>
   <tr align="center">
   	  <td>${resultViewUserName}</td>
      <td>${resultViewEmail}</td>
      <td>${resultViewPassword}</td>
   </tr>
</table>
</body>
</html>
