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
			float:right;
			margin-top: 7px;
		}
		#div2{
		float:left;
		}
	</style>
  </head>
  		
  <body style="background-color:#6699ff">
  		
  		<div id="div2">
  		<font color="#FFFFFF" size="5px">@学员系统</font>
  		</div>
  		<div id="div1">
   			<span style="font-size:20px" >欢迎<font color="red" >${user.name }</font>到来</span>
   			<a href="${pageContext.request.contextPath }/servlet/IndexServlet?method=getData" target="_parent"><input  type="button"  value="退出" /></a>	
   		</div>
   		
  </body>
</html>








