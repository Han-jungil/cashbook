<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TagCategoryController</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<%
	String sessionMemberId = (String)session.getAttribute("sessionMemberId");
	if(sessionMemberId == null) {
	   // 로그인 되지 않은 경우
	   response.sendRedirect(request.getContextPath()+"/LoginController");
	   return;
	}
	
	Map<String,Object> map = (Map<String,Object>)request.getAttribute("list");
	System.out.println("TagCategoryController.jsp listSize : " + map.size());
	
%>
<div class="container">
	<div>
		<a href= "<%=request.getContextPath()%>/SelectMemberOneController"><%=session.getAttribute("sessionMemberId") %></a>님 반갑습니다.
		<a href="<%=request.getContextPath()%>/LogoutController">로그아웃</a>
	</div>
	<h2>tagOne</h2>
	<table border="2" class="table table-hover">
		<tr>
			<td>tag<td>
			<td><%=map.get("tag")%></td>
		</tr >
		<tr>
			<td>categoryNo<td>
			<td><%=map.get("cashbookNo")%></td>
		</tr>
		<tr>
			<td>kind<td>
			<td><%=map.get("kind")%></td>
		</tr>
		<tr>
			<td>cash<td>
			<td><%=map.get("cash")%>원</td>
		</tr>
		<tr>
			<td>memo<td>
			<td><%=map.get("memo") %></td>
		</tr>
		<tr>
			<td>cashDate<td>
			<td><%=map.get("cashDate") %></td>
		</tr> 
	</table>
	<a class="btn btn-dark" href="<%=request.getContextPath()%>/TagController">tag rank로 돌아가기</a>
</div>
</body>
</html>