<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>人件費検索・消去コンソール</title>
</head>
<body>
	<br>
	<h4>人件費検索結果</h4>
	<a href="${ pageContext.request.contextPath }
						/findtodaylaborcost">人件費入力フォームへ</a>
	<br>
	<br>

	<%--検索結果から今月の個人の給与総額と削除操servletへの送信ボタンの実装 --%>>
	<h6>削除後は元に戻せません</h6>
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
			<th></th>
		</tr>
		<c:forEach var="result" items="${ searchCostInfoList }" end="350">
			<tr>
				<td>${ result.id }</td>
				<td>${ result.date }</td>
				<td>${ result.name }</td>
				<td>${ result.hourly }</td>
				<td>${ result.begin }</td>
				<td>${ result.finish }</td>
				<td>${ result.rest }</td>
				<td>${ result.total_work }</td>
				<td>${ result.late }</td>
				<td>${ result.overTimeWork }</td>
				<td>${ result.fare }</td>
				<td>${ result.total_cost}</td>
				<td><form action="${ pageContext.request.contextPath }/deletelaborcost"
						method="post"> <input type="hidden" name="id"
					value="${ result.id }"> <input type="submit" value="削除">
					</form>
			</tr>
		</c:forEach>
	</table>
	<h4>今月支給額 ${ personalCost } 円</h4>
</body>
</html>