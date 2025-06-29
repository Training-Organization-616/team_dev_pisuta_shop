<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>ログイン</title>
	<link rel="stylesheet" href="/team_dev_pisuta_shop/css/loginStyle.css">
</head>
<body>
	<jsp:include page="/header.jsp" />
	
	<div class="login">
	<div class="login-page">
		<form class="login-form" action="/team_dev_pisuta_shop/LoginServlet" method="post">
			<div class="title"><b>ログイン</b></div>
			<div class="err">${message}</div>
			<input type="hidden" name="action" value="login">
			<div class="field-title">メールアドレス</div>
			<div class="field">
				<input type="text" name="email">
			</div>
			<div class="field-title">パスワード</div>
			<div class="field">
				<input type="password" name="password"><br>
			</div>
			<button>ログイン</button>
	
		</form>
	</div>
	<div class="link">
		<form action="/team_dev_pisuta_shop/UserServlet" method="post">
		<button>新規登録画面へ</button>
		<input type="hidden" name="action" value="regist">
		
		</form>
	</div>
	</div>
	
</body>
</html>