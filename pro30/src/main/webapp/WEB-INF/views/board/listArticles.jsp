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
 <style>
   .cls1 {text-decoration:none;}
   .cls2{text-align:center; font-size:30px;}
  </style>
  <meta charset="UTF-8">
  <title>글목록창</title>
</head>
<script>
/* 매개변수가 3개이고, 첫번째 로그인 했을 경우를 알려주는 상태변수
2번째, 로그인시 이동할 페이지 
3번째, 로그인x 이동할 페이지 */
	function fn_articleForm(isLogOn,articleForm,loginForm){
	  if(isLogOn != '' && isLogOn != 'false'){
		  /* 게시글 작성 폼 */
	    location.href=articleForm;
	  }else{
		  // 로그인 안되면, 로그인후 글작성 해주세요. 경고창 알리고,
		  // 로그인 폼으로 이동. 
	    alert("로그인 후 글쓰기가 가능합니다.")
	    location.href=loginForm+'?action=/board/articleForm.do';
	  }
	}
</script>
<body>
<table align="center" border="1"  width="80%"  >
  <tr height="10" align="center"  bgcolor="lightgreen">
     <td >글번호</td>
     <td >작성자</td>              
     <td >제목</td>
     <td >작성일</td>
  </tr>
  <!-- 게시글 있다면  -->
  <!-- varStatus , 인덱스와 비슷한데, 갯수를 1부터 숫자세기 용도. -->
  <%-- ${articleNum.count} -> 1부터 시작함 --%>
  <!-- article.level 클수로 하위 계층으로 가고, 0이 부모글 , 레벨1, 
	   예) 게시글1의 0 -> 1번 답변글 레벨1 -> 1번 답변 답변글 레벨2 -->
	    <!-- 답글시 뷰에서 왼쪽으로 들여쓰기 20px 만큼 --> 
	    <%-- 클라이언트 뷰 목록에서, 해당 게시글의 제목 클릭시 이동할 링크에 ? 파라미터로 전달된 
	         요소 : articleNO=${article.articleNO} , 서버에 게시글 번호를 전달
	         그 게시글 번호의 정보를 , 동네 1 ~ 4 순회해서, 해당 뷰에 게시글 정보를 출력
	         예) 수정폼, 수정할 회원의 정보를 디비에서 가져와야함.  --%>
	         <!-- 게시글이 답글이 아닌경우, 부모글이라고 표현. -->
<c:choose>
  <c:when test="${articlesList ==null }" >
    <tr  height="10">
      <td colspan="4">
         <p align="center">
            <b><span style="font-size:9pt;">등록된 글이 없습니다.</span></b>
        </p>
      </td>  
    </tr>
  </c:when>
  
  <c:when test="${articlesList !=null }" >
  
    <c:forEach  var="article" items="${articlesList }" varStatus="articleNum" >
     <tr align="center">
     
	<td width="5%">${articleNum.count}</td>
	<td width="10%">${article.id }</td>
	<td align='left'  width="35%">
	  <span style="padding-right:30px"></span>
	   
	   <c:choose>
	  
	      <c:when test='${article.level > 1 }'>  
	     
	         <c:forEach begin="1" end="${article.level }" step="1">
	              <span style="padding-left:20px"></span>    
	         </c:forEach>
	         <span style="font-size:12px;">[답변]</span>
	         
                   <a class='cls1' href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a>
	          </c:when>
	          
	          <c:otherwise>
	            <a class='cls1' href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title }</a>
	          </c:otherwise>
	        </c:choose>
	  </td>
	  <td  width="10%">${article.writeDate}</td> 
	</tr>
    </c:forEach>
     </c:when>
    </c:choose>
</table>
<!-- <a  class="cls1"  href="#"><p class="cls2">글쓰기</p></a> -->
<a  class="cls1"  href="javascript:fn_articleForm('${isLogOn}','${contextPath}/board/articleForm.do', 
                                                    '${contextPath}/member/loginForm.do')"><p class="cls2">글쓰기</p></a>
</body>
</html>