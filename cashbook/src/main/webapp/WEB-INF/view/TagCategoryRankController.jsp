<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
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
	
	List<HashMap<String, Object>> list = (List<HashMap<String, Object>>)request.getAttribute("list");
	Date nowTime = new Date();
%>
<div class="container">
	<div>
		<a href= "<%=request.getContextPath()%>/SelectMemberOneController"><%=session.getAttribute("sessionMemberId") %></a>님 반갑습니다.
		<a href="<%=request.getContextPath()%>/LogoutController">로그아웃</a>
	</div>
	<h1>tag rankOneList</h1>
		<a class="btn bg-dark text-white" href="<%=request.getContextPath()%>/CashbookListByMonthController">뒤로</a>
		<a class="btn bg-dark text-white" href="<%=request.getContextPath()%>/TagController">tag rank다시보기</a>
		<table border="2" class="table table-hover">
		<tr>
			<th>cashbookNo</th>
			<th>tag</th>
			<th>cash</th>
			<th>kind</th>
			<th>memo</th>
			<th>cashDate</th>
		</tr>
		<%
			for(Map<String, Object> map : list) {
		%>
				<tr>
					<td><%=map.get("cashbookNo")%></td>
					<td><a href="<%=request.getContextPath()%>/TagCategoryController?tag=<%=map.get("tag")%>&cashbookNo=<%=map.get("cashbookNo")%>"><%=map.get("tag")%></a></td>
					<td><%=map.get("cash")%></td>
					<%
						if(map.get("kind") != null && !map.get("kind").equals("")) {
					%>
							<td><%=map.get("kind")%></td>	
					<%
						} else {
					%>
							<td>랭크는 나오지 않습니다.</td>
					<%
						}
					%>
					<td><%=map.get("memo") %></td>
					<td><%=map.get("cashDate") %></td>
				</tr>
		<%			
			}
		%>
	</table>
</div>
</body>
</html>