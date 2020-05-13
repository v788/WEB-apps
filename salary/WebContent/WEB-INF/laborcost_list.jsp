<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>人件費管理コンソール</title>
</head>
<body>
	<br>	<h4>人件費入力フォーム</h4>
	<h4>今月の総人件費  ${thisMonthCost} 円</h4>
	<br>※休憩時間は通常勤務時間から優先して控除します
	<table border="1">
		<tr>
			<th>ID</th>
			<th>氏名</th>
			<th>日付</th>
			<th>時給</th>
			<th>勤務</th>
			<th>出勤</th>
			<th>退勤</th>
			<th>休憩</th>
			<th>決定</th>
		</tr>
		<c:forEach var="cost" items="${employeeInfoList }">
			<tr>
				<form action="${pageContext.request.contextPath }/salarycalc">
					<td><input type="hidden" name="name_id" value="${ cost.no }">${ cost.no }</td>
					<td><input type="hidden" name="name" value="${ cost.no }">${ cost.name }</td>
					<td><input type="date" name="date" required></td>
					<td><select name="hourly">
							<option value="1" selected>基本時給</option>
							<option value="2">特別時給</option>
					</select></td>
					<td><select name="attend">
							<option value="1" selected>通常勤務
							<option value="2">休日勤務</option>
					</select></td>
					<td><input type="time" name="begin" required></td>
					<td><input type="time" name="finish" required></td>
					<td><input type="time" name="rest"></td>
					<td><input type="hidden" name="hourlywage"
						value="${cost. hourlywage}"> <input type="hidden"
						name="sphourly" value="${ cost.sphourly }"> <input
						type="hidden" name="carfare" value="${ cost.carfare }"> <input
						type="hidden" name="midnight" value="${ cost.midnight }">
						<input type="hidden" name="holiday" value="${ cost.holiday }">
						<input type="hidden" name="overtime" value="${ cost.overtime }">
						<input type="submit" value="${ cost.name }"></td>
				</form>
			</tr>
		</c:forEach>
	</table>
	<br>
	<table>
		<tr>
			<th>深夜勤務</th>
			<th>休日勤務</th>
			<th>残業勤務</th>
		</tr>
		<tr>
			<c:forEach var="ex" items="${ extrasettingInfoList }">
				<td><input type="hidden" value="${ ex.midnight }"
					name="ex.midnight">${ ex.midnight }%</td>
				<td><input type="hidden" value="${ ex.holiday }"
					name="ex.holiday">${ ex.holiday }%</td>
				<td><input type="hidden" value="${ ex.overtime }"
					name="ex.overtime">${ ex.overtime }%</td>
			</c:forEach>
		</tr>
	</table>
	<br>
	<br>
	<br>今月期リスト一覧
	<form action="${ pageContext.request.contextPath }/searchlaborcost"
		method="post">
		<p>
			<label>検索・削除コンソール:<input type="month" name="search_month"></label>
			<select name="search_name">
				<option value="0">名前を選択してください</option>
				<c:forEach var="select_name" items="${ employeeInfoList }">
					<option value="${ select_name.no }">${ select_name.name }</option>
				</c:forEach>
			</select> <input type="submit" value="search">
		</p>
	</form>
	<table border="1">
		<tr>
			<th>管理ID</th>
			<th>日付</th>
			<th>名前</th>
			<th>時給</th>
			<th>出勤</th>
			<th>退勤</th>
			<th>休憩</th>
			<th>勤務時間</th>
			<th>深夜</th>
			<th>残業時間</th>
			<th>日額交通費</th>
			<th>日額給与</th>
		</tr>

		<c:forEach var="cost" items="${ laborcostInfoList }" >
			<tr>
				<td>${ cost.id }</td>
				<c:choose>
					<c:when test="${cost.attend eq '2'}">
						<td><span style="color: fuchsia;">${ cost.date }</span></td>
					</c:when>
					<c:otherwise>
						<td>${ cost.date }</td>
					</c:otherwise>
				</c:choose>
				<td>${ cost.name }</td>
				<td>${ cost.hourly }</td>
				<td>${ cost.begin }</td>
				<td>${ cost.finish }</td>
				<td>${ cost.rest }</td>
				<td>${ cost.total_work }</td>
				<td>${ cost.late }</td>
				<td>${ cost.overTimeWork }</td>
				<td>${ cost.fare }</td>
				<td>${ cost.total_cost}</td>
			</tr>
		</c:forEach>
	</table>
	(日付が赤文字は休日出勤を表します)
	<br>
</body>
</html>