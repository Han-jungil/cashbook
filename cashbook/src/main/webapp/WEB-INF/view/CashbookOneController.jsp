<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<%
	String sessionMemberId = (String)session.getAttribute("sessionMemberId");
	if(sessionMemberId == null) {
	   // 로그인 되지 않은 경우
	   response.sendRedirect(request.getContextPath()+"/LoginController");
	   return;
	}
	
	Cashbook cashbook = (Cashbook)request.getAttribute("cashbook");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CashbookOneController</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<div>
		<a href= "<%=request.getContextPath()%>/SelectMemberOneControllerSelectMemberOneController"><%=session.getAttribute("sessionMemberId") %></a>님 반갑습니다.
		<a href="<%=request.getContextPath()%>/LogoutController">로그아웃</a>
	</div>
	<h1>일정 상세보기</h1>
	<table class = "table">
		<tr>
			<td>cashbookNo</td>
			<td><%=cashbook.getCashbookNo()%></td>
		</tr>
		<tr>
			<td>cashDate</td>
			<td><%=cashbook.getCashDate()%></td>
		</tr>
		<tr>
			<td>kind</td>
			<td><%=cashbook.getKind()%></td>
		</tr>
		<tr>
			<td>cash</td>
			<td><%=cashbook.getCash()%></td>
		</tr>
		<tr>
			<td>updateDate</td>
			<td><%=cashbook.getUpdateDate()%></td>
		</tr>
		<tr>
			<td>createDate</td>
			<td><%=cashbook.getCreateDate()%></td>
		</tr>
		<tr>
			<td>memo</td>
			<td><%=cashbook.getMemo()%></td>
		</tr>
	</table>
	<div class="btn-group">
		<li class="page-item"><a class="btn bg-dark text-white" href="<%=request.getContextPath()%>/CashbookListByMonthController">뒤로</a></li>
		<li class="page-item"><a class="btn bg-dark text-white" href="<%=request.getContextPath()%>/UpdateCashbookController?cashbookNo=<%=cashbook.getCashbookNo()%>&cashDate=<%=cashbook.getCashDate()%>&kind=<%=cashbook.getKind()%>&cash=<%=cashbook.getCash()%>&memo=<%=cashbook.getMemo()%>">수정</a></li>&nbsp&nbsp&nbsp
		<li class="page-item"><a class="btn bg-dark text-white" href="<%=request.getContextPath()%>/DeleteCashbookController?cashbookNo=<%=cashbook.getCashbookNo()%>">삭제</a></li>
	</div>
</div>
</body>
</html>