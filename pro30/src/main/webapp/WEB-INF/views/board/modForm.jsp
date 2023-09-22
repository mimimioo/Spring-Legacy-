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
   <title>수정폼 페이지 </title>
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
   /* cnt , 파일 추가시 , 입력태그 구분 짓는 변수 */
   var cnt=1;
   /* cnt1 , 이미지 불러오는 태그를 구분 짓는 변수 */
   var cnt1=0; 
   /* cnt2 img 태그를 구분짓는 변수 */
   var cnt2=0;
   
   
   /* 추가1 */
   function readURL2(input) {
   	  console.log('readURL2 호출 여부 확인')
       if (input.files && input.files[0]) {
   	      var reader = new FileReader();
   	     
   	      reader.onload = function (e) {
   	    	  console.log('preview 호출 전 cnt2 : '+ cnt2)
   	        $('#preview'+cnt1).attr('src', e.target.result);
   	    	  cnt1++;
   	    	  console.log('preview 호출 후 cnt2 : '+ cnt2)
           }
          reader.readAsDataURL(input.files[0]);
       }
   } 
   
     function backToList(obj){
       obj.action="${contextPath}/board/listArticles.do";
       obj.submit();
     }
     
     function submit1(obj){
    	 var shouldDelete = confirm("정말 수정 할까요?");
		  if (shouldDelete){
         obj.action="${contextPath}/board/modArticle.do";
         obj.submit();
       }
     }
     
     function submit2(obj){
    	 var shouldDelete = confirm("정말 사진 업로드 할까요?");
		  if (shouldDelete){
         obj.action="${contextPath}/board/onlyImageUpload.do";
         obj.submit();
       }
     }
     
  
     /* 추가2 */
     function fn_addFile(){
   	  $("#d_file").append("<br>"+"<input type='file' name='file"+cnt+"+"+"' onchange="+"readURL2(this); />");
   	  
   	  cnt++;
   	   $("#previews").append("<br>"+"<img id='preview"+cnt2+"' src='#'"+ "width=200 height=200 />");

   	   cnt2++;
   	  console.log(cnt2);
     }  
  
   /* 이미지를 뷰에 출력하기 위한 함수 */
/* 글쓰기 부분 재사용 예정 */
 </script>
</head>
<body>
<!-- action 부분은 컨트롤로에서 추가할 예정, 처리 부분, 
사실은 폼 부분도 필요함.  -->
  <form name="frmArticle" method="post"  action="${contextPath}/board/modArticle.do"  enctype="multipart/form-data">
  <table  border=0  align="center">
  <tr>
   <td width=150 align="center" bgcolor=#FF9933>
      글번호
   </td>
   <td >
   <!-- disabled 속성 설정은, 서버에 데이터를 전달 못함 -->
    <input type="text"  value="${article.articleNO }"  readOnly />
    <input type="hidden" name="articleNO" value="${article.articleNO}"  />
   </td>
  </tr>
  <tr>
    <td width="150" align="center" bgcolor="#FF9933">
      작성자 아이디
   </td>
   <td >
    <input type=text value="${article.id }" name="writer"  readOnly />
   </td>
  </tr>
  <tr>
    <td width="150" align="center" bgcolor="#FF9933">
      제목 
   </td>
   <td>
    <input type=text value="${article.title }"  name="title"  id="i_title"  />
   </td>   
  </tr>
  <tr>
    <td width="150" align="center" bgcolor="#FF9933">
      내용
   </td>
   <td>
    <textarea rows="20" cols="60"  name="content"  id="i_content"   />${article.content }</textarea>
   </td>  
  </tr>
 	 
  
  <tr>
	   <td width="150" align="center" bgcolor="#FF9933">
	      등록일자
	   </td>
	   <td>
	    <input type=text value="<fmt:formatDate value="${article.writeDate}" />" readOnly />
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
			    <a href="${contextPath}/board/deleteImage.do?imageFileNO=${item.imageFileNO}&articleNO=${article.articleNO}&imageFileName=${item.imageFileName}"  class="no-underline">삭제</a><br>
			    
			   </td>   
			  </tr>  
			  <tr>
			    <td>
			    <!--    <input  type="file"  name="imageFileName " id="i_imageFileName"   disabled   onchange="readURL(this);"   /> -->
			    </td>
			 </tr>
		</c:forEach>
 </c:if>
 
 
 
 </table>
 <input type=button value="수정반영하기" onClick="submit1(frmArticle)" >
  <input type=button value="취소"  onClick="backToList(frmArticle)">
 </form>
 
 <!-- 이미지 파일 추가 부분 분리 -->
 <form name="onlyImageUpload" method="post"  action="${contextPath}/board/onlyImageUpload.do"  enctype="multipart/form-data">
 <table  border=0  align="center">
  <tr>
  <td align="center"> <input type="button" value="사진올리기"  onClick="submit2(onlyImageUpload)"/></td>
      <input type="hidden" name="articleNO" value="${article.articleNO}"  />
			  <td align="right">이미지파일 첨부:  </td>
			  
			  <!-- 추가3 -->
			  <td align="left"> <input type="button" value="파일 추가" onClick="fn_addFile()"/></td>
			  
			  <!-- <td> <input type="file" name="imageFileName"  onchange="readURL(this);" /></td> -->
			  <!-- <td><img  id="preview" src="#"   width=200 height=200/></td> -->
			  
	   </tr>
	   <tr>
	   <!-- 여기에 파일이 추가 되는 태그가 첨부되는 자리 -->
	      <td colspan="4"><div id="d_file"></div></td>
	      <!-- 추가4 -->
	      <!-- 첨부된 이미지를 불러오는 영역 -->
	       <td colspan="4"><div id="previews"></div></td>
	   </tr>
  </table>
  </form>
  
</body>
</html>