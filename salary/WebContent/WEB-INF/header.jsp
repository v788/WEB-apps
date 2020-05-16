<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<!
DOCTYPE html> <html> <head> <meta charset ="UTF-8"> <title> </title> <style type
	="text/css">
@import url('https://fonts.googleapis.com/css2?family=M+PLUS+Rounded+1c:wght@300&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Ubuntu+Mono:ital@1&display=swap');

#div9{
font-family: 'Ubuntu Mono', monospace;
font-size: 160%;
color:#202020;
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
.home{
color:red;
font-size:120%;
}
.table9{
border: 1px solid;
width:100%;
}
</style>
</head>
<body>
<div id="div9">Easy <span class="check">Check</span> The Cost</div>
<table class="table9"></table>
<ul id="nav">
		<li><a href="${ pageContext.request.contextPath }/findtodaylaborcost"><span class="home">HOME</span></a></li>
		<li><a href="${ pageContext.request.contextPath }/employeefindall">アルバイトさん一覧</a></li>
		<li><a href="${ pageContext.request.contextPath }/controlregist">従業員登録・割増設定</a></li>
		<li><a href="${ pageContext.request.contextPath }/">説明書</a></li>
	</ul>
</body>
</html>