<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		.tab  li{
			list-style: none;
			
		}
		a:link{
			text-decoration:none;
			color: red;
		}
		a:hover{
			color:skyblue;
		}
		ul li{
			margin-top:20px;
			font-size:20px;
			padding: 0px;
		}
	</style>
  </head>
  	
  <body style="background-color:#6699ff">
   		<%-- --%>
   		
   		<h1 >操作程序</h1>
			<ul class="tab" >
				<li>
					<a href="/work1/jsp/nono.jsp" target="right">匿名投诉</a>
				</li>
				<li>
					<a href="/work1/jsp/nono.jsp" target="right">技术管理</a>
				</li>
				<li>
					<a href="/work1/jsp/myinfo.jsp" target="right">我的资料</a>
				</li>
				<li>
					<a href="/work1/jsp/nono.jsp" target="right">交费明细</a>
				</li>
				<li>
					<a href="/work1/jsp/nono.jsp" target="right">参加考试</a>
				</li>
				<li>
					<a href="/work1/jsp/nono.jsp" target="right" >学员评价</a>
				</li>
				<c:if test="${user.username ==000000 }">
				<li>
					
  						<a href="${pageContext.request.contextPath }/servlet/UserServlet?method=findPage&currentPage=1" target="right">用户信息</a>
  					
				 </li>
				</c:if>
			</ul>
		

  </body>
</html>