<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" charset="UTF-8">
<title>Please Login</title>
<style type="text/css">
@import
	url('https://fonts.googleapis.com/css2?family=Sacramento&display=swap')
	;

@import
	url('https://fonts.googleapis.com/css2?family=Ubuntu+Mono:ital@1&display=swap')
	;

h3 {
	font-size: 200%;
	font-family: 'Sacramento', cursive;
	margin-left: 5%;
}

table {
	margin-left: 25%;
	padding: 130px;
	font-family: "Meiryo";
}

.login {
	display: inline-block;
	color: #7CB342;
	transition: .5s;
}

.login:hover {
	transform: rotateX(360deg);
}

#div9 {
	margin-left: 75%;
	padding: 15px;
	font-family: 'Ubuntu Mono', monospace;
	font-size: 160%;
	color: #202020;
}

.check {
	color: #AAAAAA;
}
</style>
</head>
<body>
	<h3>Please Login Here . . .</h3>
	<%--ログイン時エラーメッセージの表示 --%>
	<br>
	<br>
	<!-- checklogin servletから入力不備についてmessegeが送信されます-->
	<table>
		<form action="${ pageContext.request.contextPath }/checklogin"
			method="post">
			<tr>
				<th></th>
				<th><c:out value="${ message }" /></th>
			</tr>
			<tr>
				<th class="enter">メールアドレス</th>
				<th><input type="email" name="email" required></th>
			</tr>
			<tr>
				<th class="enter">パスワード</th>
				<th><input type="password" name="password" required></th>
			</tr>
			<tr>
			</tr>
			<tr>
				<th></th>
				<th><span><input type="submit" value="Login"
						class="login"></span></th>
			</tr>
		</form>
	</table>
	<div id="div9">
		Easy <span class="check">Check</span> The Cost
	</div>
</body>
</html>