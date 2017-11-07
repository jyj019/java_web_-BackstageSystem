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
	<script type="text/javascript" src="ajax.js"></script>
  	<style type="text/css">
  		#div1{
  			margin-top:150px;
  			background-color:#e8e8e8;
  			height:200px;
  			width:400px;
  		}
  		#div2{
  			margin-top:30px;
  		}
  		#div4{
  			margin-top:10px;
  		}
  		#div5{
  			margin-top:10px;
  		}
  		#div6{
  			margin-top:10px;
  			color:red;
  		}
  	</style>
  </head>
  <body style="background-color:#6699ff">
  	
  	<form action="${pageContext.request.contextPath }/servlet/UserServlet?method=login"  method="post">
  		<center>
  		<div id="rs"></div>
  		<div id="div1" >
  			
  			<div id="div2">
  				<h3>学生后台登录系统</h3>
  			</div>
   			<div>
   				<b>账号：</b><input id="username" type="text" name="username"  />
   			</div>
   			<div id="div4">
   				<b>密码：</b><input id="password" type="password" name="password" />
   			</div>
   			<div>
   			<b>验证码：</b><input type="text" name="yzm"  style="width: 50;" />
   			<img src="/work1/servlet/UserServlet?method=yanzm" onclick="changeCodes()" alt="验证码" title="验证码"/>
   			<a href="javascript:changeCodes()">看不清</a>
   			</div>
   			<div id="div5">
   				<a href="${pageContext.request.contextPath }/jsp/zhuce.jsp"><input type="button" value="注册" ></a>
   				&nbsp;&nbsp;
   				<input type="submit" value="登录" >
   				<input type="checkbox" name="auto" value="auto" />自动登录
   			</div> 
   			<div id="div6" >${msg }	</div>
   		</div>
   		<div>欢迎来到jyj019-2017@</div>
   		</center>
   	</form>	
  </body>
	<script type="text/javascript">
		function changeCodes(){
			var img = document.getElementsByTagName("img")[0];
			// 随机数
			
			img.src = "/work1/servlet/UserServlet?method=yanzm&code="+Math.random();
			
		}
	</script>
</html>



