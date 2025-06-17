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
	
	<div class="login-page">
		<form class="login-form" action="/team_dev_pisuta_shop/LoginServlet" method="post">
			<div class="title"><b>ログイン</b></div>
			<div class="err">${message}<br></div>
			<input type="hidden" name="action" value="login">
			<input type="text" name="email" placeholder="メールアドレス"><br>
			<input type="text" name="password" placeholder="パスワード"><br>
			<button>ログイン</button>
	
		</form>
	</div>
	<div class="link">
		<a href="/team_dev_pisuta_shop/UserServlet?action=regist">新規登録</a>
	</div>
	
</body>
</html>