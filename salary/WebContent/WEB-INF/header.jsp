<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!
DOCTYPE html> <html> <head> <meta charset ="UTF-8"> <title> </title> <style type
	="text/css">
@import url('https://fonts.googleapis.com/css2?family=M+PLUS+Rounded+1c:wght@300&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Ubuntu+Mono:ital@1&display=swap');

.name li{
display: inline-block;
text-decoration:none;
font-family: 'M PLUS Rounded 1c', sans-serif;
}
#div1{
font-family: 'Ubuntu Mono', monospace;
font-size: 160%;
color:#202020;
}
#check{
color: #AAAAAA;
}
hr.line{
border-top: 2px solid #777777;
}

#nav {
font-family: 'M PLUS Rounded 1c', sans-serif;
list-style:none;
}
#nav li{
width:19%;
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
<ul class="name">
<li><div id="div1">Easy <span id="check">Check</span> The Cost</div></li>
<li class="name"><pre>                                                                                              ログイン : <c:out value="${ sessionScope.user_Name }"/></pre></li>
</ul>

<hr class="line">

<ul id="nav">
		<li><a href="${ pageContext.request.contextPath }/findtodaylaborcost"><span class="home">HOME</span></a></li>
		<li><a href="${ pageContext.request.contextPath }/employeefindall">アルバイトさん一覧</a></li>
		<li><a href="${ pageContext.request.contextPath }/controlregist">新人さん登録・割増設定</a></li>
		<li><a href="${ pageContext.request.contextPath }/manual.jsp">説明書</a></li>
		<li><a href="${ pageContext.request.contextPath }/designdocument.jsp">仕様書</a></li>
	</ul>
</body>
</html>