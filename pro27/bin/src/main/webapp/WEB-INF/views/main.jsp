<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"   isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>


<%
  request.setCharacterEncoding("UTF-8");
%>    

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <!-- 이 부분이 공통레이아웃 부품 교체 사용될 예정 -->
  <title>메인 페이지</title>
  <script src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>
<!-- 이 부분이 공통레이아웃 부품 교체 사용될 예정 -->
   <h1>메인 페이지입니다!!</h1>
   <P>  The time on the server is ${serverTime}. </P>
</body>
</html>