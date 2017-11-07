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
  			margin-left:450px;
  			margin-top:150px;
  			background-color:#e8e8e8;
  			height:420px;
  			width:350px;
  		}
  		.div{
  			margin-left:550px;
  		}
  		div{
  			margin-top:20px;
  		}
  		#d{
  			margin-left: 30px;
  		}
  	</style>

  </head>
  
  <body style="background-color:#6699ff">
   
  
  	
  	<div id="div1">
  		
  		<form action="${pageContext.request.contextPath }/servlet/UserServlet?method=regist" method="post">
  			<center>
  			<div id="div2">
  				<h3>学生后台注册系统</h3>
  			</div>
  			</center>
   			<div id="d" >
   				<b>账号：</b><input  type="text" name="username" onkeyup="spChange(this)"  placeholder="请输入6-15位作为账号"/><span id="sp" ></span>
   			</div>
   			<div id="d" >
   				<b>密码：</b><input  type="password" name="password" placeholder="请输入6-15位作为密码" />
   			</div>
   			<div id="d">
   				<b>姓名:</b><input type="text" name="name"  placeholder="请输入你的名字" >
   			</div>
   			<div id="d">
   				<b>性别:</b><input type="radio" name="sex" value="男" >男
   				<input type="radio" name="sex" value="女" >女
   			</div >
   			<div id="d">
   				<b>身份证:</b><input type="text" name="identity"  placeholder="身份证号"  >
   			</div>
   			<div id="d">
   				<b>学校:</b><input type="text" name="school" placeholder="就读学校"  >
   			</div>
   			<div id="d">
   				<b>email:</b><input type="text" name="email" placeholder="默认：huxia@huxia.com"  >
   			</div>
   			<center>
   			<div >
   				<a href="/work1"><input type="button"  value="返回" ></a>
   				
   				&nbsp;&nbsp;&nbsp;&nbsp;
   				
   				<input type="submit"  name="zhece"  value="提交" >
   			</div> 
   			<div>${msg}</div>
   			</center>
   		</form>	
   	</div>
  <div class="div">欢迎来到jyj019-2017@</div>
   		
  </body>

  <script type="text/javascript">
  	 	var sp = document.getElementById("sp");
  	 	function spChange(ip){
  	 		// 1.获取input 输入的内容
  	 		var value = ip.value;
  	 		
  	 		if(value!=null){
  	 			// 2.发送ajax请求
  	 			 //获取ajax对象
  	 			 var ajax = getAjax();
  	 			// 设置监听
  	 			ajax.onreadystatechange = function(){
  	 				// 监听状态
  	 				if(ajax.readyState==4 && ajax.status==200){
  	 					// 获取数据
  	 					var result = ajax.responseText;
  	 					// alert(result);
  	 					if(result == "1"){
  	 						sp.innerHTML="<font color='green'>用户名有效</font>";
  	 					}else{
  	 						sp.innerHTML="<font color='red'>非法用户名</font>";
  	 					}
  	 				}
  	 			}
  	 			// 设置路径
  	 			ajax.open("GET","/work1/servlet/UserServlet?method=zhuce&data="+value);
  	 			// 发送  为什么要传值    需要监听的就是input输入框里面的内容
  	 			// ajax.setRequestHeader("Content-type","application/x-www-form-urlencoded");
  	 			ajax.send();
  	 		}
  	 		
  	 	}
  
  	 </script>
</html>