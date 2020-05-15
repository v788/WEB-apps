<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<!
DOCTYPE html> <html> <head> <meta charset ="UTF-8"> <title> </title> <style type
	="text/css">
@import url('https://fonts.googleapis.com/css2?family=M+PLUS+Rounded+1c:wght@300&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Ubuntu+Mono:ital@1&display=swap');

div{
font-family: 'Ubuntu Mono', monospace;
font-size: x-large;
}
.check{
color: #AAAAAA;
}

#nav {
font-family: 'M PLUS Rounded 1c', sans-serif;
list-style:none;

}
#nav li{
width:20%;
display: inline-block;
text-align:right;
background-color: #fff;
}
#nav li a{
text-decoration:none;
 color: #333;
}
</style>
</head>
<body>
<div>Easy <span class="check">Check</span> The Cost</div>
<ul id="nav">
		<li><a href="${ pageContext.request.contextPath }/findtodaylaborcost">HOME</a></li>
		<li><a href="${ pageContext.request.contextPath }/employeefindall">従業員一覧</a></li>
		<li><a href="${ pageContext.request.contextPath }/controlregist">従業員登録・割増設定</a></li>
		<li><a href="${ pageContext.request.contextPath }/">説明書</a></li>
	</ul>
</body>
</html>