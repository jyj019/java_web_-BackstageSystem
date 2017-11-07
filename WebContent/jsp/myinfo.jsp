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
			float: left;
			width: 200px;
			height: 250px;
			margin-left:100px; 
			margin-top: 100px;
		}
		#div2{
			float:right;
			margin-right: 250px;
		}
		
		img{
			width: 200px;
			height: 200px;
		}
	</style>

  </head>
  
  <body style="background-color: #B0B0B0">
  	<div id="div1">
  	<img alt="" src="image/${img }">
  	<form action="${pageContext.request.contextPath }/servlet/FileUpServlet" method="post" enctype="multipart/form-data">
   			<input id="fl" type="file" name="file" /><br />
   			<input id="btn" type="submit" value="点击上传">
   	</form>
  	</div>
  		<div id="div2" >
  		<center>
   		<table border="1" cellspacing="3">
   			<caption><h1>个人资料</h1></caption>
   			<tr><td style="width: 130px;">编号</td><td style="width: 330px;">${user.uid }</td></tr>
   			<tr><td>账号</td><td>${user.username }</td></tr>
   			<tr><td>密码</td><td>${user.password }</td></tr>
   			<tr><td>姓名</td><td>${user.name }</td></tr>
   			<tr><td>性别</td><td>${user.sex }</td></tr>
   			<tr><td>身份证号</td><td>${user.identity }</td></tr>
   			<tr><td>学校</td><td>${user.school }</td></tr>
   			<tr><td>电子邮箱</td><td>${user.email }</td></tr>
   			<tr><td>状态</td><td>${user.state }</td></tr>			
   		</table>
   		</center>
   		</div>
  </body>
</html>