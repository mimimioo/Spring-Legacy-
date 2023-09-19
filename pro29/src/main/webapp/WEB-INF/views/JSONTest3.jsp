<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<title>JSONTest3</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>  
<script>
  $(function() {
      $("#checkJson").click(function() {
      	var article = {id:"777", 
	               name:"이상용 더미데이터 웹으로 전달중.",
	               pwd:"1234", 
	               email:"lsy@naver.com"
	              };
  
  	$.ajax({
  	    /* type:"POST",
        url:"${contextPath}/boards2", */
         type:"PUT",
        url:"${contextPath}/boards/1234",
        /* type:"DELETE",
        url:"${contextPath}/boards/1234", */
        contentType: "application/json",
        data :JSON.stringify(article),
        // 서버로 부터 잘받았다는 응답 객체에 메세지가 포함됨. 
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