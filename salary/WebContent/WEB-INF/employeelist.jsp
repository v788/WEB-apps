<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

<style>
@import url('https://fonts.googleapis.com/css?family=Noto+Sans+JP');
#employee{
display: flex;
  justify-content: space-around;

}

#employee > div{
 width: 45%;
}

table{
border: 1px solid;
  width: 100%;
    font-size: 80%;
    font-family: 'Noto Sans JP', sans-serif;
}
table.table2{
border: 0;
width: 40%;
text-align: center;
}
</style>

<title>アルバイトさん一覧</title>
</head>

<body>
<jsp:include page="/WEB-INF/header.jsp"/>

	<br>
	<div id="employee">
	<div>
	<h6>アルバイトさん一覧</h6>
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
</div>

<div>
<br>
<br>
<h6 align="center">割増率
<p>※深夜時間帯は22:00～4:59の間で設定しています</p></h6>
	<table class="table2" align="center">
	<c:forEach var="ex" items = "${extra}">
		<tr>
			<td  height="50">深夜勤務</td>
			<td>${ ex.midnight }%</td>
		</tr>
			<tr>
			<td  height="50">休日勤務</td>
			<td>${ ex.holiday }%</td>
			</tr>
			<tr>
			<td  height="50">残業勤務</td>
			<td>${ ex.overtime }%</td>
			</tr>

		</c:forEach>
	</table>
</div>

</div>
	<br>
</body>
</html>