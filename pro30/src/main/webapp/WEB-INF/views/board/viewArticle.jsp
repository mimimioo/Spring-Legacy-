<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%-- 다중 이미지의 상세보기시 사용할 예정.--%> 
<!-- 일반 데이터가 담겨 있고 -->
<c:set var="article"  value="${articleMap.article}"  />
<!-- 파일 이미지 이름 목록 담겨져 있음. -->
<c:set var="imageFileList"  value="${articleMap.imageFileList}"  />

 
<%
  request.setCharacterEncoding("UTF-8");
%> 

<head>
   <meta charset="UTF-8">
   <title>글보기</title>
   <style>
     #tr_file_upload{
       display:none;
     }
     #tr_btn_modify{
       display:none;
     }
     #preview{
      width: 30%
     }
   
   </style>
   <script  src="http://code.jquery.com/jquery-latest.min.js"></script> 
   <script type="text/javascript" >
   /* 목록보기 함수 */
     function backToList(obj){
	    obj.action="${contextPath}/board/listArticles.do";
	    obj.submit();
     }
 
   /* 해당 아이디의 속성을 한번에 변경하는 함수 */
   // 수정하기 눌렀을 때, 각 디브 태그 아이디의 활성화 상태
	 function fn_enable(obj){
		 document.getElementById("i_title").disabled=false;
		 document.getElementById("i_content").disabled=false;
		 document.getElementById("i_imageFileName").disabled=false;
		 document.getElementById("tr_btn_modify").style.display="block";
		 document.getElementById("tr_file_upload").style.display="block";
		 document.getElementById("tr_btn").style.display="none";
	 }
	 
   /* 수정폼으로 이동하는 함수 */
	 function fn_modify_article(obj){
		 obj.action="${contextPath}/board/modForm.do";
		 obj.submit();
	 }
	 
   /* 삭제시 사용할 함수 */
   /* 삭제시, 1)삭제할 게시글번호와 2)삭제를 처리하는 서버의 맵핑 주소전달 */
   /* 기존에는 html 폼에 작성이 다된 상태로 작업을 했다면, 
   지금 inner HTML이라고 해서, 동적으로 개발작, 폼 요소, 속성, 임의로 만들어서 
   html 문서에 주입을 하는 형식. */
	 function fn_remove_article(url,articleNO){
		  var shouldDelete = confirm("정말 삭제 할까요?");
		  if (shouldDelete){
	   /* 동적으로 form 태그를 만들기 */
		 var form = document.createElement("form");
	   /* form 요소에, 속성으로, 전달 방식 post, action : 전송 폼에서 submit 클릭시
	   서버로 데이터를 전부 다 전달 위치. */
		 form.setAttribute("method", "post");
		 form.setAttribute("action", url);
		 /* input 태그를 동적으로 생성하고, 속성도 추가하는 로직.  */
	     var articleNOInput = document.createElement("input");
	     articleNOInput.setAttribute("type","hidden");
	     articleNOInput.setAttribute("name","articleNO");
	     articleNOInput.setAttribute("value", articleNO);
		 
	     /* form 태그 및 하위 요소를 추가한 부분을 적용 */
	     /* form 태그 하위에 input 속성 추가 */
	     form.appendChild(articleNOInput);
	     /* form 태그를 html body 태그에 추가하는 부분 */
	     document.body.appendChild(form);
	     /* 서버에 전달을 한다. action의 속성의 값의 위치로 이동 */
	     form.submit();
		  }
	 
	 }
	 
   /* 답장을하는 폼도, 동적으로 폼을 구성, 하위에 입력 태그라든지, 요소를 추가해서 
   바디라는 태그에 붙이는 작업. */
	 function fn_reply_form(url, parentNO){
		 var form = document.createElement("form");
		 form.setAttribute("method", "get");
		 form.setAttribute("action", url);
		 
	     var parentNOInput = document.createElement("input");
	     parentNOInput.setAttribute("type","hidden");
	     parentNOInput.setAttribute("name","parentNO");
	     parentNOInput.setAttribute("value", parentNO);
		 
	     form.appendChild(parentNOInput);
	     
	     document.body.appendChild(form);
	     
		 form.submit();
	 }
	 
   /* 이미지를 뷰에 출력하기 위한 함수 */
	 function readURL(input) {
	     if (input.files && input.files[0]) {
	    	 /* 파일 이름이 존재한다면 */
	         var reader = new FileReader();
	    	 
	         /* #preview div 태그 아이디로 사용하는 되는 태그 */
	         /* 속성 src <img src = 에 값으로 전달 */
	         /* 읽은 이미지 파일을 출력을 한다. */
	         reader.onload = function (e) {
	             $('#preview').attr('src', e.target.result);
	         }
	         /* reader 인스턴스를 이용해서 해당 경로의 이미지를 읽기 */
	         reader.readAsDataURL(input.files[0]);
	     }
	 }  
 </script>
</head>
<body>
  <form name="frmArticle" method="post"  action="${contextPath}"  enctype="multipart/form-data">
  <table  border=0  align="center">
  <tr>
   <td width=150 align="center" bgcolor=#FF9933>
      글번호
   </td>
   <td >
   <!-- disabled 속성 설정은, 서버에 데이터를 전달 못함 -->
    <input type="text"  value="${article.articleNO }"  disabled />
    <input type="hidden" name="articleNO" value="${article.articleNO}"  />
   </td>
  </tr>
  <tr>
    <td width="150" align="center" bgcolor="#FF9933">
      작성자 아이디
   </td>
   <td >
    <input type=text value="${article.id }" name="writer"  disabled />
   </td>
  </tr>
  <tr>
    <td width="150" align="center" bgcolor="#FF9933">
      제목 
   </td>
   <td>
    <input type=text value="${article.title }"  name="title"  id="i_title" disabled />
   </td>   
  </tr>
  <tr>
    <td width="150" align="center" bgcolor="#FF9933">
      내용
   </td>
   <td>
    <textarea rows="20" cols="60"  name="content"  id="i_content"  disabled />${article.content }</textarea>
   </td>  
  </tr>
 <!-- 다중 이미지 출력 부분 -->
 <c:if test="${not empty imageFileList && imageFileList!='null' }">
	  <c:forEach var="item" items="${imageFileList}" varStatus="status" >
		    <tr>
			    <td width="150" align="center" bgcolor="#FF9933"  rowspan="2">
			      이미지${status.count }
			   </td>
			   <td>
			     <input  type= "hidden"   name="originalFileName" value="${item.imageFileName }" />
			    <img src="${contextPath}/download.do?articleNO=${article.articleNO}&imageFileName=${item.imageFileName}" id="preview"  /><br>
			   </td>   
			  </tr>  
			  <tr>
			    <td>
			    <!--    <input  type="file"  name="imageFileName " id="i_imageFileName"   disabled   onchange="readURL(this);"   /> -->
			    </td>
			 </tr>
		</c:forEach>
 </c:if>
 	  
 	 
  <c:choose> 
	  <c:when test="${not empty article.imageFileName && article.imageFileName!='null' }">
	   	<tr>
		    <td width="150" align="center" bgcolor="#FF9933"  rowspan="2">
		      이미지
		   </td>
		   <td>
		     <input  type= "hidden"   name="originalFileName" value="${article.imageFileName }" />
		    <img src="${contextPath}/download.do?articleNO=${article.articleNO}&imageFileName=${article.imageFileName}" id="preview"  /><br>
		   </td>   
		  </tr>  
		  <tr>
		    <td ></td>
		    <td>
		       <!-- <input  type="file"  name="imageFileName " id="i_imageFileName"   disabled   onchange="readURL(this);"   /> -->
		    </td>
		  </tr> 
		 </c:when>
		 <c:otherwise>
		    <tr  id="tr_file_upload" >
				    <td width="150" align="center" bgcolor="#FF9933"  rowspan="2">
				      이미지
				    </td>
				    <td>
				      <input  type= "hidden"   name="originalFileName" value="${article.imageFileName }" />
				    </td>
			    </tr>
			    <tr>
				    <td ></td>
				    <td>
				       <img id="preview"  /><br>
				       <!-- <input  type="file"  name="imageFileName " id="i_imageFileName"   disabled   onchange="readURL(this);"   /> -->
				    </td>
			  </tr>
		 </c:otherwise>
	 </c:choose>
  <tr>
	   <td width="150" align="center" bgcolor="#FF9933">
	      등록일자
	   </td>
	   <td>
	    <input type=text value="<fmt:formatDate value="${article.writeDate}" />" disabled />
	   </td>   
  </tr>
  <tr   id="tr_btn_modify"  align="center"  >
	   <td colspan="2"   >
	       <input type=button value="수정반영하기"   onClick="fn_modify_article(frmArticle)"  >
           <input type=button value="취소"  onClick="backToList(frmArticle)">
	   </td>   
  </tr>
    
  <tr  id="tr_btn"    >
   <td colspan="2" align="center">
       <c:if test="${member.id == article.id }">
       
       <input type=button value="수정하기" onClick="fn_modify_article(this.form)">
	      <!-- <input type=button value="수정하기" onClick="fn_enable(this.form)"> -->
	      <input type=button value="삭제하기" onClick="fn_remove_article('${contextPath}/board/removeArticle.do', ${article.articleNO})">
	    </c:if>
	    <input type=button value="리스트로 돌아가기"  onClick="backToList(this.form)">
	     <input type=button value="답글쓰기"  onClick="fn_reply_form('${contextPath}/board/replyForm.do', ${article.articleNO})">
   </td>
  </tr>
 </table>
 </form>
</body>
</html>