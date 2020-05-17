<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error</title>
<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=Sacramento&display=swap');
h2{
font-size:300%;
font-family: 'Sacramento', cursive;
margin-left: 25%;
}
h1{
font-size:350%;
font-family: 'Sacramento', cursive;
margin-left: 35%;
}
h5{
font-size:80%;
margin-left: 70%;
font-family: "游ゴシック体", YuGothic, "游ゴシック", "Yu Gothic", "メイリオ",
		"Hiragino Kaku Gothic ProN", "Hiragino Sans", sans-serif;
}
table{
width:100%;
background: #FFFF00;
}
.link1,.link1 span {
	display: inline-block;
	color: #7CB342;
	margin-left: 5%;
}
.link1 span {
	transition: .5s;
}
.link1:hover span {
	transform: rotateX(360deg);
}
</style>
</head>
<body>
<br>
<br>
<h2>sorry ,</h2>
<h1>this is error window . . .</h1>
<br>
<br>
<h5>申し訳ございません</h5>
<h5>入力中のデータがございましたら</h5>
<h5>履歴よりご確認下さい</h5>
<a href="${ pageContext.request.contextPath }/findtodaylaborcost" class="link1">
<span>HOME</span></a>
</body>
</html>