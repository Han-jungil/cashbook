<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
%>
	<h1>tag rank</h1>
	
	<form method="post" action="<%=request.getContextPath()%>/TagKindDateController">
			<td>kind : </td>
			<select name="kind">
				<option value="">전체</option>
				<option value="수입">수입</option>
				<option value="지출">지출</option>
			</select>
			<td>Date : </td>
			<input type="date" name="startDate"></input>
			<td>~</td>
			<input type="date" name="endDate" value=<%=sf.format(nowTime)%>></input>
			<td><button type="submit">검색</button></td>>
	</form>
	<table border="1">
		<tr>
			<th>rank</th>
			<th>tag</th>
			<th>count</th>
			<th>kind</th>
		</tr>
		<%
			for(Map<String, Object> map : list) {
		%>
				<tr>
					<td><%=map.get("rank")%></td>
					<td><a href="<%=request.getContextPath()%>/TagCategoryController?tag=<%=map.get("tag")%>&cashbookNo=<%=map.get("cashbookNo")%>"><%=map.get("tag")%></a></td>
					<td><%=map.get("cnt")%></td>
					<%
						if(map.get("kind") != null && !map.get("kind").equals("")) {
					%>
							<td><%=map.get("kind")%></td>	
					<%
						} else {
					%>
							<td></td>
					<%
						}
					%>
				</tr>
		<%			
			}
		%>
	</table>
</body>
</html>