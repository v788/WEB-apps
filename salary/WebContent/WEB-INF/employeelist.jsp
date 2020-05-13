<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>従業員一覧</title>
</head>
<body>
	<pre><a href="${pageContext.request.contextPath }/controlregist.jsp">登録・修正フォーム</a></pre>
	<br>
	<h4>パート・アルバイト一覧</h4>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>名前</th>
			<th>基本時給</th>
			<th>交通費（日額）</th>
			<th>特別時給</th>
		</tr>

		<c:forEach var="employeeInfo" items="${ employeeInfoList }">
			<tr>
				<td>${ employeeInfo.no }</td>
				<td>${ employeeInfo.name }</td>
				<td>${ employeeInfo.hourlywage }
				<td>${ employeeInfo.carfare }</td>
				<td>${ employeeInfo.sphourly }</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<br>

	<h4>割増率</h4>
	※深夜時間帯は22:00～4:59の間で設定しています
	<table border="1">
		<tr>
			<th>深夜勤務</th>
			<th>休日勤務</th>
			<th>残業勤務</th>
		</tr>
		<c:forEach var="ex" items = "${extra}">
		<tr>
			<td>${ ex.midnight }</td>
			<td>${ ex.holiday }</td>
			<td>${ ex.overtime }</td>
		</tr>
		</c:forEach>
	</table>
	<br>
</body>
</html>