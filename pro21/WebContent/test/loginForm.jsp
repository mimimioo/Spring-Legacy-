<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
    <!-- taglib -> 조금더 간결히 로직을 처리하기 위한 임시 방편, 
    왜? 자바로 html 안에 로직을 다 작성하기 불편해서, 편의성고려함 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 변수명 :contextPath , 값 : url 주소의 특정 앞부분을 가져오는 명령어 --> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<!-- 한글 깨짐 방지 하기위한, UTF-8 설정, request 들어오는 값을 인코딩하기 -->
<%
   request.setCharacterEncoding("UTF-8");
%>     
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
<title>로그인창</title>
</head>

<body>
<!-- contextPath , http://localhost:8080/pro21 의미  -->
<!-- action -> 값이 : 폼에 내용을 다 작성 후, 전송을 눌렀을 때, 서버의 어느 주소로 매핑한건지 여부 -->
<%-- ${contextPath} : http://localhost:8080/pro21 --%>
<!-- http://localhost:8080/pro21/test/login.do -->
<form name="frmLogin" method="post"  action="${contextPath}/test/login.do">
   <table border="1"  width="80%" align="center" >
      <tr align="center">
         <td>아이디</td>
         <td>비밀번호</td>
      </tr>
      <tr align="center">
         <td>
         <!-- 입력 받은 값을 어디에 할당 userID : 예 lsy -->
	    <input type="text" name="userID" value="" size="20">
	 </td>
         <td>
         <!-- 입력 받은 값을 어디에 할당 passwd 예 1234 -->
	    <input type="password" name="passwd" value="" size="20">
	 </td>
      </tr>
      <tr align="center">
         <td colspan="2">
         <!-- 입력된 모든 값들이 서버에 전부다 전송 -->
            <input type="submit" value="로그인" > 
            <input type="reset"  value="다시입력" > 
         </td>
      </tr>
   </table>
</form>
</body>
</html>
