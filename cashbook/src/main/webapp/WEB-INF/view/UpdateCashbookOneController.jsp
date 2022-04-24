<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import = "vo.*" %>
<%
	String sessionMemberId = (String)session.getAttribute("sessionMemberId");
	if(sessionMemberId == null) {
	   // 로그인 되지 않은 경우
	   response.sendRedirect(request.getContextPath()+"/LoginController");
	   return;
	}
	
	request.setCharacterEncoding("utf-8");
	// 뷰에 입력하기위해 요청값 넣기
	Cashbook cashbook = new Cashbook();
	cashbook = (Cashbook)request.getAttribute("cashbook");	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UpdateCashbookController</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div>
		<a href= "<%=request.getContextPath()%>/SelectMemberOneController"><%=session.getAttribute("sessionMemberId") %></a>님 반갑습니다.
		<a href="<%=request.getContextPath()%>/LogoutController">로그아웃</a>
	</div>
	<div class="container">
	<h1>일정 수정</h1>
	<form method="post" action="<%=request.getContextPath()%>/UpdateCashbookController">
		<table class="table">
			<tr>
				<td>cashbookNo</td>
				<td><input type="text" name="cashbookNo" value="<%=cashbook.getCashbookNo()%>" readonly="readonly"></td>
			</tr>
						<tr>	
				<td>cashDate</td>
				<td><input type="date" name="cashDate" class="form-control">원래 날짜 : <%=cashbook.getCashDate() %></td>
			</tr>
			<tr>
				<td>kind</td>
				<td>
					<select name="kind" class="form-select">
							<option value="수입">수입</option>
							<option value="지출">지출</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>cash</td>
				<td><input type="number" name="cash"  class="form-control" value=<%=cashbook.getCash()%>></td>
			</tr>
			<tr>
				<td>memo</td>
				<td>
					<textarea rows="5" cols="50" name="memo" class="form-control"><%=cashbook.getMemo()%></textarea>
				</td>
		</table>
		<a class="btn bg-dark text-white" href="<%=request.getContextPath()%>/CashbookOneController?cashbookNo=<%=cashbook.getCashbookNo()%>">뒤로</a>
		<button type="submit" class="btn btn-dark">수정</button>
	</form>
</div>
</body>
</html>