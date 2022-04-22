<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">	
	<form action="<%=request.getContextPath()%>/LoginController" method="post">
		<table border="1" class="table table-hover">
			<h1>로그인화면</h1>
			<tr>
				<td>memberId</td>
				<td><input type="text" name="memberId"></td>
			</tr>
			<tr>
				<td>memberPw</td>
				<td><input type="password" name="memberPw"></td>
			</tr>
		</table>
		<button class="btn-dark" type="submit">로그인</button>
		<a class="btn-dark" href="<%=request.getContextPath()%>/InsertMemberController">회원가입</a>
	</form>
</div>
</body>
</html>