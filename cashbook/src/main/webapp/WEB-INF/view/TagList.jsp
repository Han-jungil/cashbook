<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

	List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
%>
<div class="container">
	<div>
		<a href= "<%=request.getContextPath()%>/SelectMemberOneController"><%=session.getAttribute("sessionMemberId") %></a>님 반갑습니다.
		<a href="<%=request.getContextPath()%>/LogoutController">로그아웃</a>
	</div>
	<h1>tag rank</h1>
	<form method="post" action="<%=request.getContextPath()%>/TagKindDateController">
			<td>kind : </td>
			<select class="form-select" name="kind">
				<option value="">전체</option>
				<option value="수입">수입</option>
				<option value="지출">지출</option>
			</select>
			<td>Date : </td>
			<input type="date" name="startDate"></input>
			<td>~</td>
			<input type="date" name="endDate" value=<%=sf.format(nowTime)%>></input>
			<td><button  class="btn btn-dark" type="submit">검색</button></td>
	</form>
	<table border="2" class="table table-hover">
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
</div>
</body>
</html>