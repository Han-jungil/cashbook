<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insertMember</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">	
	<h1>회원가입</h1>
	<form method="post" action= "<%=request.getContextPath()%>/InsertMemberController">
		<table class="table">
			<tr>
				<td>memberId</td>
				<td>
					<input name="memberId" type="text" class="form-control">
				</td>
			</tr>
			<tr>
				<td>memberPw</td>
				<td>
					<input name="memberPw" type="password" class="form-control"></input>
				</td>
			</tr>
			<tr>
				<td>name</td>
				<td>
					<input name="name" type="text" class="form-control">
				</td>
			</tr>
			<tr>
				<td>gender</td>
				<td><input type='radio' name='gender' value='남'>남
				<input type='radio' name='gender' value='여'>여</td>
			</tr>
			<tr>
				<td>age</td>
				<td>
					<input name="age" type="number" class="form-control">
				</td>
			</tr>
			<tr>
				<td>
					<button type="submit" class="btn-dark">가입</button>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>