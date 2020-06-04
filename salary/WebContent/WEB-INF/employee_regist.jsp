<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" charset="UTF-8">
<title>登録・修正・削除</title>
<style type="text/css">
@import url('https://fonts.googleapis.com/css?family=Noto+Sans+JP');

body {

	font-family: 'Noto Sans JP', sans-serif;
}

h5 {
	color: #999999;
	size: 100%;
	margin-left: 160px;
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

#table1 {
	width: 60%;
	color: white;
	padding: 1.5em 1em;
	font-size: 100%;
	border-top: solid 6px #B6FF01;
	text-align: center;
	margin-left: 160px;
	background: #FF00FF;
	box-shadow: 0 3px 4px rgba(0, 0, 0, 0.32);
	padding: 1.5em 1em;
}

#table2 {
	background-color: #DDDDDD;
	width: 50%;
	padding: 1.5em 1em;
	font-size: 100%;
	color: #555555;
	text-align: center;
	margin-left: 160px;
	box-shadow: 0 3px 4px rgba(0, 0, 0, 0.32);
	padding: 1.5em 1em;
	border-spacing: 0;
}

table.table3 {
	width: 50%;
	font-size: 100%;
	padding: 1.5em 1em;
	text-align: center;
	margin-left: 300px;
	background: #EEFFFF;
	line-height: 2em;
	border-spacing: 0;
	border-top: solid 6px #00ECFF;
	box-shadow: 0 3px 4px rgba(0, 0, 0, 0.32);
	justify-content: space-around;
}
table.table3 td, table2 td {
	padding: 7px 0;
	text-align: center;
}
table.table3 tr:nth-child(even) {
	background-color: #EEEEEE;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/header.jsp" />
	<div id="employee">
		<div>
			<br>
			<h5>新規登録・修正・削除</h5>

			<%--入力文字数や金額桁数の不備がメッセージで帰ってきます --%>
			<c:out value="${ message1 }" />

			<%--新規従業員の登録情報をPost送信 --%>
			<form action="${pageContext.request.contextPath}/employeeregist"
				method="post">
				<table id="table1">
					<tr>
						<td>名前</td>
						<td><input type="text" name="name" required></td>
						<td></td>
					</tr>
					<tr>
						<td>基本時給</td>
						<td><input type="number" name="hourlywage" required></td>
						<td></td>
					</tr>
					<tr>
						<td>交通費</td>
						<td><input type="number" name="carfare"></td>
						<td></td>
					</tr>
					<tr>
						<td>特別時給</td>
						<td><input type="number" name="sphourly"></td>
						<td><input type="submit" value="登録" /></td>
					</tr>
				</table>
			</form>
		</div>
		<br>

		<div>
		<br>
		<br>
			<h5>割増率変更</h5>
			<table id="table2">
				<c:forEach var="ex" items="${extra}">
					<form
						action="${pageContext.request.contextPath}/extrasettingregist"
						method="get">
						<tr>
							<td>深夜割増率</td>
							<td><input type="text" name="ex_mid" size="10"
								value="${ ex.midnight }" /></td>
							<td></td>
						</tr>
						<tr>
							<td>休日割増率</td>
							<td><input type="text" name="ex_holiday" size="10"
								value="${ ex.holiday }" /></td>
							<td></td>
						</tr>
						<tr>
							<td>残業割増率</td>
							<td><input type="text" name="ex_overtime" size="10"
								value="${ ex.overtime }" /></td>
							<td><input type="submit" value="更新"></td>
						</tr>
					</form>
				</c:forEach>
			</table>
		</div>
	</div>

<br>
<br>
<br>
	<h5>
		修正・削除フォーム
		<c:out value="${ message2 }" />
	</h5>
	<table class="table3">
		<tr>
			<td>ID</td>
			<td>名前</td>
			<td>基本時給</td>
			<td>交通費（日額）</td>
			<td>特別時給</td>
		</tr>

		<c:forEach var="employeeInfo" items="${ employeeInfoList }">
			<tr>
				<form action="${ pageContext.request.contextPath }/updateregist" method="post">
					<td><input type="hidden" name="no"
						value="${ employeeInfo.no }" ><c:out value="${ employeeInfo.no }"/></td>
					<td><input type="text" name="name"
						value="${ employeeInfo.name }" required size="12"></td>
					<td align="right"><input type="text" name="hourlywage"
						size="10" value="${ employeeInfo.hourlywage }" required></td>
					<td align="right"><input type="text" name="carfare" size="10"
						value="${ employeeInfo.carfare }"></td>
					<td align="right"><input type="text" name="sphourly" size="10"
						value="${ employeeInfo.sphourly }"></td>
					<td><input type="submit" value="修正"></td>
				</form>
				<td><form action="${ pageContext.request.contextPath }/delete" method="post">
						<input type="hidden" name="delete" value="${ employeeInfo.no }" />
						<input type="submit" value="削除">
					</form></td>
			</tr>
		</c:forEach>
	</table>
	<script type="text/javascript">
		function check() {
			if (window.comfirm('削除してもよろしいですか？')) {
				return true;
			} else {
				window.alert('キャンセルしました');
				return false;
			}
		}
	</script>
</body>
</html>




