<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
   request.setCharacterEncoding("UTF-8");
%>     
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
<title>로그인 창</title>
</head>

<body>
<form name="frmLogin" method="post"  action="${contextPath}/test/join.do">
   <table border="1"  width="80%" align="center" >
      <tr align="center">
         <td>이름</td>
         <td>이메일</td>
         <td>패스워드</td>
      </tr>
      <tr align="center">
         <td>
	   		 <input type="text" name="userName" value="" size="20">
		 </td>
	  	<td>
	   		 <input type="text" name="email" value="" size="20">
		 </td>
         <td>
	  		 <input type="password" name="passwd" value="" size="20">
	 	</td>
      </tr>
      <tr align="center">
         <td colspan="2">
            <input type="submit" value="로그인" > 
            <input type="reset"  value="다시입력" > 
         </td>
      </tr>
   </table>
</form>
</body>
</html>
