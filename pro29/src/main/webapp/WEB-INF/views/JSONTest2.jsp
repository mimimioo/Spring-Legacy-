<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<title>JSONTest2</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>  
<script>
  $(function() {
      $("#checkJson").click(function() {
      	var article = {articleNO:"777", 
	               writer:"이상용",
	               title:"레스트연습하기, 게시판", 
	               content:"POST 글 추가해보기. 피카츄"
	              };
  
  	$.ajax({
  	    /* type:"POST",
        url:"${contextPath}/boards", */
        /* type:"PUT",
        url:"${contextPath}/boards/1234", */
        type:"DELETE",
        url:"${contextPath}/boards/1234",
        contentType: "application/json",
        data :JSON.stringify(article),
      success:function (data,textStatus){
    	  // 서버로 부터 응답 받은 메세지를 , 자바스크립트의 경고창에 띄우는 방법,
          alert(data);
      },
      error:function(data,textStatus){
        alert("에러가 발생했습니다.");ㅣ
      },
      complete:function(data,textStatus){
      }
   });  //end ajax	

   });
});
</script>
</head>
<body>
  <input type="button" id="checkJson" value="새글 쓰기"/><br><br>
  <div id="output"></div>
</body>
</html>