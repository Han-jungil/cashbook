<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TagCategoryController</title>
</head>
<body>
<%
	HashMap<String,Object> map = (HashMap<String,Object>)request.getAttribute("tagCategory");
	
	
%>
	<h1>tagOne</h1>
	<table border="1">
	
<%-- 		<tr>
			<td>tag<td>
			<td><%=tagCategory.getTag()%></td>
		</tr --%>>
		<tr>
			<td>categoryNo<td>
			<td><%=map.get("cashbookNo") %></td>
		</tr>
<%-- 		<tr>
			<td>kind<td>
			<td><%=tagCategory.getKind() %></td>
		</tr>
		<tr>
			<td>memo<td>
			<td><%=tagCategory.getMemo() %></td>
		</tr>
		<tr>
			<td>cashDate<td>
			<td><%=tagCategory.getCashDate() %></td>
		</tr> --%>
	</table>
	<a href="<%=request.getContextPath()%>/TagController">tag rank로 돌아가기</a>
</body>
</html>