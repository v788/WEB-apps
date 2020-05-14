<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>従業員登録</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>
	<pre>
		<a href="${pageContext.request.contextPath }/employeefindall">一覧へ</a>
	</pre>
	<h4>新規登録・修正・削除</h4>
	<br>
	<br>新規登録フォーム${ message1 }
	<form action="${pageContext.request.contextPath}/employeeregist"
		method="post">
		<table>
			<tr>
				<th>名前</th>
				<td><input type="text" name="name" required></td>
			</tr>
			<tr>
				<th>基本時給</th>
				<td><input type="text" name="hourlywage" required></td>
			</tr>
			<tr>
				<th>交通費</th>
				<td><input type="text" name="carfare"></td>
			</tr>
			<tr>
				<th>特別時給</th>
				<td><input type="text" name="sphourly"></td>
			</tr>
		</table>
		<input type="submit" value="登録" />
	</form>
	<br>
	<br>修正・削除フォーム${ message2 }
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
				<form action="${ pageContext.request.contextPath }/updateregist"
					id="update">
					<td><input type="hidden" name="no"
						value="${ employeeInfo.no }" form="update">${ employeeInfo.no }</td>
					<td><input type="text" name="name"
						value="${ employeeInfo.name }" required form="update"></td>
					<td><input type="text" name="hourlywage" size="10"
						value="${ employeeInfo.hourlywage }" required form="update"></td>
					<td><input type="text" name="carfare" size="10"
						value="${ employeeInfo.carfare }" form="update"></td>
					<td><input type="text" name="sphourly" size="10"
						value="${ employeeInfo.sphourly }" form="update"></td>
					<td><input type="submit" value="修正" form="update"></td>
				</form>
				<td><form action="${ pageContext.request.contextPath }/delete"
						method="post" id="delete">
						<input type="hidden" name="delete" id="delete"
							value="${ employeeInfo.no }" /> <input type="submit" value="削除"
							form="delete">
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

	<br>
	<br>
	<h4>割増率変更</h4>
	<table>
		<tr>
			<th>深夜割増率</th>
			<th>休日割増率</th>
			<th>残業割増率</th>
		</tr>
		<tr>
			<c:forEach var="ex" items="${extra}">
				<form action="${pageContext.request.contextPath}/extrasettingregist"
					method="get">
					<td><input type="text" name="ex_mid" size="10"
						value="${ ex.midnight }" /></td>
					</td>
					<td><input type="text" name="ex_holiday" size="10"
						value="${ ex.holiday }" /></td>
						<td><input type="text" name="ex_overtime" size="10"
						value="${ ex.overtime }" /></td>
					<td><input type="submit" value="更新"></td>
				</form>
			</c:forEach>
		</tr>
	</table>
</body>
</html>