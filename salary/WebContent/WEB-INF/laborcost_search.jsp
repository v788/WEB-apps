<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>履歴検索・消去コンソール</title>
<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=M+PLUS+Rounded+1c:wght@300&display=swap');
h3 {
	text-align: center;
	margin-left: 50%;
	color: #333333;
	border: solid 2px #008080;
	padding: 0em;
	border-radius: 2em;
}
#span1{
margin-left: 160px;
color:#202020;
size: 160%;
font-family: 'M PLUS Rounded 1c', sans-serif;
}

#table1 {
	width: 80%;
	margin-left: 160px;
	background: #EEFFFF;
	text-align: center;
	font-family: "游ゴシック体", YuGothic, "游ゴシック", "Yu Gothic", "メイリオ",
		"Hiragino Kaku Gothic ProN", "Hiragino Sans", sans-serif;
	font-weight: 500;
}

#table1 td, table1 td {
	padding: 10px 0;
	text-align: center;
}

#table1 tr:nth-child(even) {
	background-color: #eee
}
</style>
</head>
<body>
	<%--ヘッダーの挿入 --%>
	<header>
	<jsp:include page="/WEB-INF/header.jsp" />
	</header>
	<br>
		<span id="span1">検索結果  ※削除後は元に戻せません</span>
	<%--検索結果から今月の個人の給与総額と削除操servletへの送信ボタンの実装 --%>
	<h3>
	<c:forEach var="personal" items="${ personalCost }">
		<c:out value="${ personal.name }"/>  さんの<c:out value="${ search_month }"/>月分の支給額
		<c:out value="${ personal.total_cost }" />円
	</c:forEach>
	</h3>
	<table id="table1">
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
			<th></th>
		</tr>
		<c:forEach var="result" items="${ searchCostInfoList }" end="350">
			<tr>
				<td>${ result.id }</td>
				<td>${ result.date }</td>
				<td id="name">${ result.name }</td>
				<td>${ result.hourly }</td>
				<td>${ result.begin }</td>
				<td>${ result.finish }</td>
				<td>${ result.rest }</td>
				<td>${ result.total_work }</td>
				<td>${ result.late }</td>
				<td>${ result.overTimeWork }</td>
				<td>${ result.fare }</td>
				<td>${ result.total_cost}</td>
				<td><form
						action="${ pageContext.request.contextPath }/deletelaborcost"
						method="post">
						<input type="hidden" name="id" value="${ result.id }"> <input
							type="submit" value="削除">
					</form>
			</tr>
		</c:forEach>
	</table>
</body>
</html>