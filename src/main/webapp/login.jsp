<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
	<jsp:include page="/header.jsp" />
	
	<h2>ログイン</h2>

	<p>${message}</p>
	
	<form action="/team_dev_pisuta_shop/LoginServlet" method="post">
		<input type="hidden" name="action" value="new">
		<input type="text" name="email" placeholder="メールアドレス"><br>
		<input type="text" name="password" placeholder="パスワード"><br>
		<button>ログイン</button>
	
	</form>
	
	<a href="/team_dev_pisuta_shop/UserServlet?action=regist">新規登録</a>
	
</body>
</html>