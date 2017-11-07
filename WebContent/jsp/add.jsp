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

  </head>
  
  <body>
   		<form action="${pageContext.request.contextPath }/servlet/UserServlet?method=add" method="post">
   			<table border="1" width="600" align="center">
   			
   				<tr>
   					<td>账户名：</td>
   					<td><input type="text" name="username" /></td>
   				
   				</tr>
   				<tr>
   					<td>密码：</td>
   					<td><input type="password" name="password" /></td>
   				
   				</tr>
   				<tr>
   					<td>姓名：</td>
   					<td><input type="text" name="name" /></td>
   				
   				</tr>
   				<tr>
   					<td>性别：</td>
   					<td><input type="text" name="sex" /></td>
   				
   				</tr>
   				<tr>
   					<td>身份证：</td>
   					<td><input type="text" name="identity" /></td>
   				
   				</tr>
   				<tr>
   				<tr>
   					<td>学校：</td>
   					<td><input type="text" name="school" /></td>
   				
   				</tr>
   				<tr>
   					<td>email：</td>
   					<td><input type="text" name="identity"  value="huxia@huxia.com" /></td>
   				
   				</tr>
   				<tr>
   					<td>状态（填1成功）：</td>
   					<td><input type="text" name="state" value="1" /></td>
   				
   				</tr>
   				
   				<tr>
   					<td></td>
   					<td><input type="submit" value="添加" /></td>
   				
   				</tr>

   			</table>
   		
   		</form>
   		
  </body>
</html>