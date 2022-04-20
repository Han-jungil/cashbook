<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<%
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
		<li class="page-item"><a class="btn bg-dark text-white" href="<%=request.getContextPath()%>/UpdateCashbookController?cashbookNo=<%=cashbook.getCashbookNo()%>&cashDate=<%=cashbook.getCashDate()%>&kind=<%=cashbook.getKind()%>&cash=<%=cashbook.getCash()%>&memo=<%=cashbook.getMemo()%>">수정</a></li>&nbsp&nbsp&nbsp
		<li class="page-item"><a class="btn bg-dark text-white" href="<%=request.getContextPath()%>/DeleteCashbookController?cashbookNo=<%=cashbook.getCashbookNo()%>">삭제</a></li>
	</div>
</div>
</body>
</html>