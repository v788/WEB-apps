<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

<style>
@import url('https://fonts.googleapis.com/css?family=Noto+Sans+JP');

header {
	background-color: white;
}

h6 {
	color: #999999;
}

#employee {
	display: flex;
	justify-content: space-around;
}

#employee>div {
	width: 45%;
}

table {
	border: 1px solid;
	width: 100%;
	font-size: 70%;
	font-family: 'Noto Sans JP', sans-serif;
}

table.table1 {
	border: 0;
	padding: 0.5em 1em;
	font-size: 110%;
	background: #EEFFFF;
	border-top: solid 6px #1dc1d6;
	box-shadow: 0 3px 4px rgba(0, 0, 0, 0.32);
	border-spacing: 0;
	line-height: 1em;
	font-family: "游ゴシック体", YuGothic, "游ゴシック", "Yu Gothic", "メイリオ",
		"Hiragino Kaku Gothic ProN", "Hiragino Sans", sans-serif;
	font-weight: 500;
}

table.table1 td, table1 td {
	padding: 10px 0;
	text-align: center;
}

table.table1 tr:nth-child(even) {
	background-color: #eee
}

table.table2 {
	border: 0;
	width: 40%;
	font-size: 110%;
	text-align: center;
	font-family: 'Noto Sans JP', sans-serif;
}
</style>

<title>アルバイトさん一覧</title>
</head>

<body>
	<%--ヘッダーの挿入 --%>
	<header>
		<jsp:include page="/WEB-INF/header.jsp" />
	</header>
	<br>
	<%--現在登録済みの従業員の一覧リスト --%>
	<div id="employee">
		<div>
			<h6>アルバイトさん一覧</h6>
			<table class="table1">
				<tr>
					<th>ID</th>
					<th>名前</th>
					<th>基本時給</th>
					<th>交通費（日額）</th>
					<th>特別時給</th>
				</tr>
				<td height="25"></td>
				<c:forEach var="employeeInfo" items="${ employeeInfoList }">
					<tr>
						<td align="center"><c:out value="${ employeeInfo.no }"/></td>
						<td><c:out value="${ employeeInfo.name }"/></td>
						<td align="right"><c:out value="${ employeeInfo.hourlywage }"/></td>
						<td align="right"><c:out value="${ employeeInfo.carfare }"/></td>
						<td align="right"><c:out value="${ employeeInfo.sphourly }"/></td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<div>
			<br>
			<br>
			<br>
			<%--超過割増等の一覧表示 --%>
			<h6 align="center">
				割増率
				<p>※深夜時間帯は22:00～4:59の間で設定しています</p>
			</h6>
			<table class="table2" align="center">
				<c:forEach var="ex" items="${extra}">
					<tr>
						<td height="50">深夜勤務</td>
						<td>${ ex.midnight }%</td>
					</tr>
					<tr>
						<td height="50">休日勤務</td>
						<td>${ ex.holiday }%</td>
					</tr>
					<tr>
						<td height="50">残業勤務</td>
						<td>${ ex.overtime }%</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<br>
</body>
</html>