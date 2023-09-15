<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");
%>    


<html>
<head>
<meta charset=UTF-8">
<title>회원 정보 출력창</title>
</head>
<body>
<table border="1"  align="center"  width="80%">
    <tr align="center"   bgcolor="lightgreen">
      <td ><b>아이디</b></td>
      <td><b>비밀번호</b></td>
      <td><b>이메일</b></td>
   </tr>
   
   		<!-- List<MemberVO> membersList2 = dao.selectAllMemberList2();
   		한 요소들이 각각 멤버의 정보를 담은 인스턴스들의 모음집
   		 -->
   		 <!-- 각 리스트의 요소를 하나씩 꺼내서 반복 처리하는 부분 -->
 <c:forEach var="member" items="${membersList2}" >     
   <tr align="center">
      <td>${member.id}</td>
      <td>${member.pwd}</td>
      <td>${member.email}</td>
    </tr>
  </c:forEach>   
</table>
<a  href="${contextPath}/member/memberForm.do"><h1 style="text-align:center">회원가입</h1></a>
</body>
</html>
