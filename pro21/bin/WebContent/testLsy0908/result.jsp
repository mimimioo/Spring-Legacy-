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
   <!-- 서버에서 설정한 데이터를 , 뷰에서 가져와서 사용하는 예 -->
<!--    		mav.addObject("resultViewUserName", userNameTemp);
		mav.addObject("resultViewEmail", emailTemp);
		mav.addObject("resultViewPassword", passwordTemp); -->
		<!-- 개발자 1큐에 끝이 안남. 
		엄청 많은 시행착오(단위 테스트를 진행을 함.) -->
		<!-- 한 번 콘솔 로그에, 화면에 한번 출력해서 확인을 해야합니다.  -->
		<!-- 디버그,하나씩 확인을해야함, Junit 단위 실행도 가능함.  -->
		
   	  <td>${resultViewUserName}</td>
      <td>${resultViewEmail}</td>
      <td>${resultViewPassword}</td>
   </tr>
</table>
</body>
</html>
