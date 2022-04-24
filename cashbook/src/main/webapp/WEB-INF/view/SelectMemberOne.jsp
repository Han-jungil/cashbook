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
	<h1>회원 상세보기</h1>
	<table class = "table">
		<tr>
			<td>memberId</td>
			<td><%=member.getMemberId()%></td>
		</tr>
		<tr>
			<td>name</td>
			<td><%=member.getName()%></td>
		</tr>
		<tr>
			<td>gender</td>
			<td><%=member.getGender()%></td>
		</tr>
		<tr>
			<td>age</td>
			<td><%=member.getAge()%></td>
		</tr>
		<tr>
			<td>createDate</td>
			<td><%=member.getCreateDate()%></td>
		</tr>
	</table>
	<div class="btn-group">
		<li class="page-item"><a class="btn bg-dark text-white" href="<%=request.getContextPath()%>/CashbookListByMonthController">뒤로</a></li>&nbsp;&nbsp;
		<li class="page-item"><a class="btn bg-dark text-white" href="<%=request.getContextPath()%>/UpdateMemberController?memberId=<%=member.getMemberId()%>&memberPw=<%=member.getMemberPw()%>&name=<%=member.getName()%>&gender=<%=member.getGender()%>&age=<%=member.getAge()%>">회원수정</a></li>&nbsp&nbsp&nbsp
		<li class="page-item"><a class="btn bg-dark text-white" href="<%=request.getContextPath()%>/DeleteMemberController?memberId=<%=member.getMemberId()%>">회원탈퇴</a></li>
	</div>
</div>
</body>
</html>