<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style type="text/css">
@import url('https://fonts.googleapis.com/css?family=Noto+Sans+JP');

body {
font-family: 'Noto Sans JP', sans-serif;
}

header {
	background-color: white;
}

h4 {
	text-align: center;
	margin-left: 70%;
	color: #333333;
	border: solid 2px #008080;
	padding: 0.5em;
	border-radius: 2em;
}

h6 {
	margin-left: 18%;
	color: #999999;
}

table.table1 {
	width: 80%;
	font-size: 100%;
	text-align: center;
	margin-left: 160px;
	border: 0;
	background: #EEFFFF;
	border-top: solid 6px #1dc1d6;
	box-shadow: 0 3px 4px rgba(0, 0, 0, 0.32);
	border-spacing: 0;
	line-height: 2em;
	border: 0;
	font-family: "游ゴシック体", YuGothic, "游ゴシック", "Yu Gothic", "メイリオ",
		"Hiragino Kaku Gothic ProN", "Hiragino Sans", sans-serif;
	font-weight: 500;
}

table.table1 th {
	border-bottom: solid 2px #fb5144;
	padding: 10px 0;
}

table.table1 td {
	border-bottom: solid 2px #ddd;
	text-align: center;
	padding: 10px 0;
}

.submit1 {
	display: inline-block;
	padding: 0.5em 1em;
	text-decoration: none;
	background: #f7f7f7;
	border-left: solid 6px #ff7c5c;
	color: #ff7c5c;
	font-weight: bold;
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.29);
}

.submit1:active {
	box-shadow: inset 0 0 2px rgba(128, 128, 128, 0.1);
	transform: translateY(2px);
}

table.table2 {
	margin-left: 850px;
	text-align: center;
}

table.table3 {
	width: 80%;
	font-size: 100%;
	text-align: center;
	margin-left: 160px;
	border: 0;
	background: #EEFFFF;
	border-top: solid 6px #1dc1d6;
	box-shadow: 0 3px 4px rgba(0, 0, 0, 0.32);
	border-spacing: 0;
	line-height: 2em;
	font-family: "游ゴシック体", YuGothic, "游ゴシック", "Yu Gothic", "メイリオ",
		"Hiragino Kaku Gothic ProN", "Hiragino Sans", sans-serif;
	font-weight: 500;
}

table.table3 td, table3 td {
	padding: 10px 0;
	text-align: center;
}

table.table3 tr:nth-child(even) {
	background-color: #eee
}

#div1 {
	font-size: 120%;
	margin-left: 15%;
	font-family: 'Noto Sans JP', sans-serif;
	color: #777777;
}

#p1 {
	margin-left: 50%;
}
a{
}
</style>

<title>Home</title>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/header.jsp" />
	</header>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
<%--現在～過去12か月分の線グラフの表示 --%>
<a style="position:absolute; top:120px; left:110px; width:350px; height:450px;">
  <canvas id="myLineChart"></canvas>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>

  <script>
  var ctx = document.getElementById("myLineChart");
  var myLineChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: [<c:forEach var="a" items="${ everyMonthTotalList}" end="12">
    	  '${a.date}'
    	  , </c:forEach>
    	  ],
      datasets: [
        {
          label: '人件費',
          data: [<c:forEach var="a" items="${ everyMonthTotalList}" end="12">
          ${a.total_cost},</c:forEach>
          ],
          borderColor: "#00FF00",
          backgroundColor: "rgba(0,0,0,0)"
        }
      ],
    },
    options: {
      title: {
        display: true,

      },
      scales: {
        yAxes: [{
          ticks: {
            suggestedMax: ${everyCostInfo * 2},
            suggestedMin: 0,
            stepSize: 100000,
            callback: function(value, index, values){
              return  value +  '円'
            }
          }
        }]
      },
    }
  });
  </script>

</a>
	<h4>今月の総人件費 <c:out value="${thisMonthCost}"/> 円</h4>

	<table class="table1">
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

		<%--従業員の出勤、退勤時間と各個人の時給等のデータを計算servletにpostで送信 --%>

		<c:forEach var="cost" items="${employeeInfoList }">
			<tr>
				<form action="${pageContext.request.contextPath }/salarycalc"
					method="post">
					<td><input type="hidden" name="name_id" value="${ cost.no }"><c:out value="${ cost.no }"/></td>
					<td><input type="hidden" name="name" value="${ cost.no }"><c:out value="${ cost.name }"/></td>
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
					<td><input type="time" name="rest" value="00:00"></td>
					<td><input type="hidden" name="hourlywage"
						value="${cost. hourlywage}">
					    <input type="hidden" name="sphourly" value="${ cost.sphourly }">
						<input type="hidden" name="carfare" value="${ cost.carfare }">
						<input type="hidden" name="midnight" value="${ cost.midnight }">
						<input type="hidden" name="holiday" value="${ cost.holiday }">
						<input type="hidden" name="overtime" value="${ cost.overtime }">
						<input type="submit" value="${ cost.name }" class="submit1"></td>
				</form>
			</tr>
		</c:forEach>
	</table>

	<br>


	<h6>※休憩時間は通常勤務時間から優先して控除します</h6>

	<%--深夜等　時給割増情報の確認表示 --%>

	<table class="table2">
		<tr>
			<th>深夜勤務</th>
			<th>休日勤務</th>
			<th>残業勤務</th>
		</tr>
		<tr>
			<c:forEach var="ex" items="${ extrasettingInfoList }">
				<td><input type="hidden" value="${ ex.midnight }"
					name="ex.midnight"><c:out value="${ ex.midnight }"/>%</td>
				<td><input type="hidden" value="${ ex.holiday }"
					name="ex.holiday"><c:out value="${ ex.holiday }"/>%</td>
				<td><input type="hidden" value="${ ex.overtime }"
					name="ex.overtime"><c:out value="${ ex.overtime }"/>%</td>
			</c:forEach>
		</tr>
	</table>
	<br>

	<%--出勤データ削除の為の検索ボタン(削除servletにpost送信)と今月分データの一覧表示 --%>

	<br>
	<br>
	<div id="div1">今月期リスト一覧</div>
	<form action="${ pageContext.request.contextPath }/searchlaborcost"
		method="post">

		<p id="p1">
			<label>検索・削除コンソール: <input type="month" name="search_month" required></label>
			<select  name="search_name" required>
				<option>名前を選択してください</option>
				<c:forEach var="select_name" items="${ employeeInfoList }">
					<option value="${ select_name.no }"><c:out value="${ select_name.name }"/></option>
				</c:forEach>
			</select> <input type="submit" value="search">
		</p>
	</form>
	<table class="table3">
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

		<c:forEach var="cost" items="${ laborcostInfoList }">
			<%--休日出勤の場合は日付を赤色で表示します--%>
			<tr>
				<td><c:out value="${ cost.id }"/></td>
				<c:choose>
					<c:when test="${cost.attend eq '2'}">
						<td><span style="color: fuchsia;"><c:out value="${ cost.date }"/></span></td>
					</c:when>
					<c:otherwise>
						<td><c:out value="${ cost.date }"/></td>
					</c:otherwise>
				</c:choose>
				<td><c:out value="${ cost.name }"/></td>
				<td><c:out value="${ cost.hourly }"/></td>
				<td><c:out value="${ cost.begin }"/></td>
				<td><c:out value="${ cost.finish }"/></td>
				<td><c:out value="${ cost.rest }"/></td>
				<td><c:out value="${ cost.total_work }"/></td>
				<td><c:out value="${ cost.late }"/></td>
				<td><c:out value="${ cost.overTimeWork }"/></td>
				<td><c:out value="${ cost.fare }"/></td>
				<td><c:out value="${ cost.total_cost}"/></td>
			</tr>
		</c:forEach>
	</table>
	(日付が赤文字は休日出勤を表します)
	<br>
</body>
</html>