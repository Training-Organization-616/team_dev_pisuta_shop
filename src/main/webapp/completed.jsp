<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>登録完了</title>
	<link href="/team_dev_pisuta_shop/css/completedStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="/header.jsp" />
	
	<div class="completed-page">
		<div class="completed-form">
			<div class="title"><b>以下の情報で会員登録しました</b></div> 
			<br>
			氏名<br>
			<div class="field">${name}</div>
			住所<br>
			<div class="field">${address}</div>
			電話番号<br>
			<div class="field">${tel}</div>
			メールアドレス<br>
			<div class="field">${email}</div>
			生年月日<br>
			<div class="field">${birthday}</div>	
		</div>
	</div>
	
	<form action="/team_dev_pisuta_shop/LoginServlet" method="post">
		<button>ログイン画面へ</button>
	</form>
	
</body>
</html>