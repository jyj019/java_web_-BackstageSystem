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

  </head>
  
 <body>
 
 <form id="fr" action="${pageContext.request.contextPath }/servlet/UserServlet?method=delAll" method="post">
			<div >
			<center>
			
			<table  border="1" cellpadding="3" cellspacing="0">
			<tr>
				<th><input type="checkbox" name="all" onclick="allclick(this)"/>全选</th>
  				<!-- <th>用户ID</th> -->
  				<th>用户名</th>
  				<th>姓名</th>
  				<th>性别</th>
  				<th>身份证号</th>
  				<th>学校</th>
  				<th>邮件</th>
  				<th>状态</th>
  				<th>修改|删除</th>
  			 <c:forEach items="${pageBean.list }" var="it">
  				<tr>
  					<td width="50"><input type="checkbox" name="uid" class="ip" value="${it.uid }"  /></td>
  					<%-- <td>${it.uid }</td> --%>
  					<td>${it.username }</td>
  					<td>${it.name }</td>
  					<td>${it.sex }</td>
  					<td>${it.identity }</td>
  					<td>${it.school }</td>
  					<td>${it.email }</td>
  					<td>${it.state }</td>
  					<td><a href="${pageContext.request.contextPath }/servlet/UserServlet?method=findOne1&uid=${it.uid}">修改</a> | <a href="javascript:void(0)" onclick="delChange('${it.uid}')">删除</a> </td>
  				</tr>
  			</c:forEach> 
  			<tr>
  				<td colspan="10"><a href="${pageContext.request.contextPath }/jsp/add.jsp">添加商品</a><a href="javascript:void(0)" onclick="alldel()">全部删除</a></td>
  			</tr>
  			
  		</table>
  		<center>
  		<c:if test="${pageBean.currentPage !=1 }">
  			<a href="${pageContext.request.contextPath }/servlet/UserServlet?method=findPage&currentPage=${pageBean.currentPage-1}">上一页</a>
  		</c:if>
  		
  		<c:forEach begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
  			
  			<c:if test="${pageBean.currentPage==i }">
  				${i }
  			</c:if>
  			<c:if test="${pageBean.currentPage!=i }">
  				<a href="${pageContext.request.contextPath }/servlet/UserServlet?method=findPage&currentPage=${i}">${i }</a>
  			</c:if>
  		</c:forEach>
  		<c:if test="${pageBean.currentPage != pageBean.totalPage}">
  			<a href="${pageContext.request.contextPath }/servlet/UserServlet?method=findPage&currentPage=${pageBean.currentPage+1}">下一页</a>
  		</c:if>
  		</center>
  	
	</center>
		
	</div>
	</form>
  </body>
  <script type="text/javascript">
  			function delChange(uid){
  				if(confirm("是否确定删除?")){
  					location.href = "${pageContext.request.contextPath}/servlet/UserServlet?method=delOne&uid="+uid;
  				}
  			}
  			
  			function allclick(all){
  				var ch = all.checked;
  				var ips = document.getElementsByClassName("ip");
  				for(index in ips){
  					ips[index].checked=ch;
  				}
  			}
  			
  			function alldel(){
  				if(confirm("是否全删?")){
  					var fr = document.getElementById("fr");
  					fr.submit();
  				}
  			}
  		
  		</script>
</html>