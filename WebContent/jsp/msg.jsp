<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP '1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		#div1{
			
			width:350px;
			height:150px;
			background-color:#f8f8f8;
			color: red;
			font-size: 30px;
		}
		#div2{
			margin-top:150px;
		}
	</style>
  </head>
  
  <body bgcolor="#00FFFF">
  		<center>
  		<div id="div2"><h1>信息提示</h1></div>
   		<div id="div1">${msg }</div>
   		<a href="${pageContext.request.contextPath }/jsp/user_list.jsp" >点击重新或继续操作</a>
   		</center>
   		
  </body>
</html>