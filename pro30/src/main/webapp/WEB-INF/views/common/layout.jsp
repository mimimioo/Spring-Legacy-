<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"
 %>
 <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <!-- 원래, css 공통 파일 만들어서 분리해서 작업, 실제 여기는 css 파일만 연결해서 사용함.
    당분간, 한 파일에 중복해서 사용함.  --> 
    <style>
      #container {
        width: 100%;
        margin: 0px auto;
          text-align:center;
        border: 0px solid #bcbcbc;
      }
      #header {
        padding: 5px;
        margin-bottom: 5px;
        border: 0px solid #bcbcbc;
         background-color: lightgreen;
      }
      #sidebar-left {
        width: 15%;
        height:700px;
        padding: 5px;
        margin-right: 5px;
        margin-bottom: 5px;
        float: left;
         background-color: yellow;
        border: 0px solid #bcbcbc;
        font-size:10px;
      }
      #content {
        width: 75%;
        padding: 5px;
        margin-right: 5px;
        float: left;
        border: 0px solid #bcbcbc;
      }
      #footer {
        clear: both;
        padding: 5px;
        border: 0px solid #bcbcbc;
         background-color: lightblue;
      }
      
    </style>
    <!-- tiles_member.xml 설정파일에서, 구성요소인 title 변수를 구성요소로 사용중 -->
    <!-- 이 요소의 값이 공백인데, 각 뷰의 요소로 대체 될 예정. -->
    <title><tiles:insertAttribute name="title" /></title>
  </head>
    <body>
    <div id="container">
      <div id="header">
      <!-- 이 요소를 공통 레이아웃의 값으로 사용됨. 기본값 사용. -->
         <tiles:insertAttribute name="header"/>
      </div>
      <div id="sidebar-left">
      <!-- 이 요소를 공통 레이아웃의 값으로 사용됨. 기본값 사용. -->
          <tiles:insertAttribute name="side"/> 
      </div>
      <div id="content">
      <!-- 이 요소의 값이 공백인데, 각 뷰의 요소로 대체 될 예정. -->
          <tiles:insertAttribute name="body"/>
      </div>
      <div id="footer">
         <!-- 이 요소를 공통 레이아웃의 값으로 사용됨. 기본값 사용. -->
          <tiles:insertAttribute name="footer"/>
      </div>
    </div>
  </body>
</html>