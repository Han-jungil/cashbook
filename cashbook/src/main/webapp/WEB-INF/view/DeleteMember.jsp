<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<%
	String sessionMemberId = (String)session.getAttribute("sessionMemberId");
	if(sessionMemberId == null) {
	   // 로그인 되지 않은 경우
	   response.sendRedirect(request.getContextPath()+"/LoginController");
	   return;
	}
	
	Member member = (Member)request.getAttribute("member");
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
		<a href= "<%=request.getContextPath()%>/SelectMemberOneController"><%=session.getAttribute("sessionMemberId") %></a>님 반갑습니다.
		<a href="<%=request.getContextPath()%>/LogoutController">로그아웃</a>
	</div>
	<h1>회원 탈퇴</h1>
	<form method="post" action="<%=request.getContextPath()%>/DeleteMemberController">
		<table class="table">
			<tr>
				<td>memberId</td>
				<td><input type="text" name="memberId" value="<%=member.getMemberId()%>"></td>
			</tr>
			<tr>
				<td>(필수 입력)memberPw</td>
				<td><input type="password" name="memberPw"></td>
			</tr>
			<tr>	
		</table>
		<a class="btn bg-dark text-white" href="<%=request.getContextPath()%>/SelectMemberOneController">뒤로</a>
		<button type="submit" class="btn btn-dark">탈퇴</button>
	</form>
</div>
</body>
</html>