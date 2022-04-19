<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	int y = Integer.parseInt(request.getParameter("y"));
	int m = Integer.parseInt(request.getParameter("m"));
	int d = Integer.parseInt(request.getParameter("d"));
	
	String cashbookDate = y + "-" + m + "-" + d;
	System.out.println(cashbookDate);
	
	request.setAttribute("cashbookDate", cashbookDate);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InsertCashBookController</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container">	
	<h1>일정 입력</h1>
	<form method="post" action= "<%=request.getContextPath()%>/InsertCashBookController">
		<table class="table">
			<tr>
				<td>cashbookDate</td>
				<td>
					<div><input type="text" name="cashbookDate" value="<%=cashbookDate%>" readonly="readonly"></div>
				</td>
			</tr>
			<tr>
				<td>cash</td>
				<td>
					<input name="cash" type="number" class="form-control"></input>
				</td>
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
				<td>memo</td>
				<td>
					<textarea name="memo"rows="5" cols="80" class="form-control"></textarea>
				</td>
			</tr>
			<tr>
				<td>
					<button type="submit" class="btn-dark">입력</button>
					<a class="btn btn-dark" href="<%=request.getContextPath()%>/CashBookListByMonthController?y=<%=y%>&m=<%=m%>">뒤로</a>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>