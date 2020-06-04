<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
div{text-align:center;
}
p{
text-indent: 1.5in;
color:#666666;
}
.table1{
margin-right: auto;
 margin-left : auto;
color:#555555;
padding: 8px 30px 6px;

}
.table1 td{
margin-right: auto;
 margin-left : auto;
background-color: #EEEEEE;
}
.table2{
width:95%;
padding: 40px 30px 6px;
}
.table2 td{
border:solid 1px #BBBBBB;
}
</style>
<title>仕様書</title>
</head>
<body>
<header>
		<jsp:include page="/WEB-INF/header.jsp" />
</header>
<p>仕様書</p>

<table class="table1" >
<tr>
<th>OS</th>
<th></th>
</tr>
<tr>
<td>Amazon Linux 2 x86_64</td>
<th></th>
</tr>
</table>

<table class="table1">
<tr>
<th>ミドルウエア</th>
</tr>
<tr><td>・データベース</td>
<td>MariaDB 5.5.64</td>
</tr>
<tr>
<td>・Webサーバ</td>
<td>Apache/2.4.43</td>
</tr>
<tr>
<td>・アプリケーションサーバ</td>
<td>Apache Tomcat/8.5.51</td>
</tr>
<tr>
<td>・使用言語</td>
<td>JAVA(jdk version "1.8.0_252")</td>
</tr>
</table>

<p>画面遷移図</p>

<div>
<img src="画面遷移図.jpg"/>
</div>

<p>画面一覧</p>

<table class="table2">
<tr>
<th>画面名</th>
<th>分類</th>
<th>説明</th>
<th>機能名</th>
<th>備考</th>
</tr>
<tr>
<td>Please Login</td>
<td>ログインページ</td>
<td>emailとpasswordによるログイン</td>
<td>ログインの認証</td>
<td>クロスサイトスクリプティングへの対応  SQL例外発生時にはログイン画面への遷移</td>
</tr>
<tr>
<td>Home</td>
<td>入力フォーム</td>
<td>勤務時間の入力　各従業員別勤務履歴検索  当月勤務履歴の一覧表示</td>
<td>勤務入力</td>
<td>クロスサイトスクリプティングへの対応  SQL例外発生時にはエラー画面へ遷移 6か月分の給与総額のグラフ表示</td>
</tr>
<tr>
<td>アルバイトさん一覧</td>
<td>情報表示フォーム</td>
<td>登録従業員の氏名、時給、交通費、特別時給の一覧表示  深夜勤務、休日、残業の割増率の登録一覧</td>
<td>登録一覧</td>
<td>ー</td>
</tr>
<tr>
<td>登録・修正・削除</td>
<td>入力フォーム</td>
<td>従業員の氏名、時給、交通費、特別時給の新規登録  深夜勤務、休日、残業の割増率の登録  従業員の登録情報の修正、削除</td>
<td>従業員登録</td>
<td>SQL例外発生時にはエラー画面へ遷移</td>
</tr>
<tr>
<td>履歴検索・消去コンソール</td>
<td>検索結果一覧表示、削除</td>
<td>各従業員の月別勤務履歴の検索結果の表示、削除</td>
<td>検索一覧</td>
<td>過去の勤務履歴については修正できないものとする</td>
</tr>
<tr>
<td>説明書</td>
<td>情報表示フォーム</td>
<td>当アプリの制作方針、マニュアル</td>
<td>マニュアル</td>
<td>ー</td>
</tr>
<tr>
<td>仕様書</td>
<td>情報表示フォーム</td>
<td>当ページ  設計仕様の表示</td>
<td>仕様情報</td>
<td>ー</td>
</tr>
</table>

<p>ER-図</p>
<div>
<img src="ER.jpg" class="table1"/>
</div>
</body>
</html>