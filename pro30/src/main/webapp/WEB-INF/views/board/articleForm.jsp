<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  /> 
<%
  request.setCharacterEncoding("UTF-8");
%> 

<head>
<meta charset="UTF-8">
<title>글쓰기창</title>
   <style>
     #preview{
      width: 30%
     }
   
   </style>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
/* 최초에 글쓰기 시 파일 추가 할 때 먼저 보이는 프리 뷰 화면은 cnt1으로 고정하고,
추가로 파일 첨부시 보이는 프리 뷰 화면의 아이디는 하나씩 증가 하게끔 cnt2 으로 설정.  
cnt 변수는 기존 파일 추가시 file1,file2 이런 형식으로 추가하게끔.
*/

/* 추가0 */
  /* 상태 변수로 사용이되고, 이미지를 업로드하는 아이디에 변수명뒤에 숫자를 붙이기 위한 용도. */
 
  /* cnt , 파일 추가시 , 입력태그 구분 짓는 변수 */
var cnt=1;
/* cnt1 , 이미지 불러오는 태그를 구분 짓는 변수 */
var cnt1=0; 
/* cnt2 img 태그를 구분짓는 변수 */
var cnt2=0;
/* 파일 이미지를 읽어서, 해당 아이디 previewFirst 인 곳에, src 로 추가하는 함수
-> 이미지 불러오기 */
/* 기존의 단일 이미지를 선택시 불러오는 로직 */
function readURL(input) {
    if (input.files && input.files[0]) {
	      var reader = new FileReader();
	     
	      reader.onload = function (e) {
	        $('#previewFirst').attr('src', e.target.result);
	        /* console.log('readAsDataURL 호출 여부 확인'+reader.readAsDataURL(input.files[0])) */
        }
       reader.readAsDataURL(input.files[0]);
       
    }
} 
/* 테스트 -> 파일 추가시 동적으로 할당 되게끔 함수를 조정. cnt 라는 변수를 추가해서 
 * 해당 아이디 부분을 숫자 증가하게끔해서 아이디를 구분 하게끔했음. 
 */
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
  
  /* 기존 */
/*   function fn_addFile(){
	  $("#d_file").append("<br>"+"<input type='file' name='file"+cnt+"' />");
	  cnt++;
  }   */
  /* 추가2 */
  function fn_addFile(){
	  $("#d_file").append("<br>"+"<input type='file' name='file"+cnt+"+"+"' onchange="+"readURL2(this); />");
	  
	  cnt++;
	   $("#previews").append("<br>"+"<img id='preview"+cnt2+"' src='#'"+ "width=200 height=200 />");
	  /* <td> <input type="file" name="imageFileName"  onchange="readURL(this);" /></td>
	  <td><img  id="preview" src="#"   width=200 height=200/></td> */
	   cnt2++;
	  console.log(cnt2);
  }  

</script>
 <title>글쓰기창</title>
</head>
<body>
<h1 style="text-align:center">글쓰기</h1>
<!-- 단일 이미지 글쓰기 처리 -->
  <%-- <form name="articleForm" method="post"   action="${contextPath}/board/addNewArticle.do"   enctype="multipart/form-data"> --%>
  <!-- 다중 이미지 글쓰기 처리 -->
  <form name="articleForm" method="post"   action="${contextPath}/board/addMultiImageNewArticle.do"   enctype="multipart/form-data">
    <table border="0" align="center">
      <tr>
					<td align="right"> 작성자</td>
					<td colspan=2  align="left"><input type="text" size="20" maxlength="100"  value="${member.name }" readonly/> </td>
			</tr>
	     <tr>
			   <td align="right">글제목: </td>
			   <td colspan="2"><input type="text" size="67"  maxlength="500" name="title" /></td>
		 </tr>
	 		<tr>
				<td align="right" valign="top"><br>글내용: </td>
				<td colspan=2><textarea name="content" rows="10" cols="65" maxlength="4000"></textarea> </td>
     </tr>
     <tr>
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
	    <tr>
	      <td align="right"> </td>
	      <td colspan="2">
	       <input type="submit" value="글쓰기" />
	       <input type=button value="목록보기"onClick="backToList(this.form)" />
	      </td>
     </tr>
    </table>
  </form>
</body>
</html>
