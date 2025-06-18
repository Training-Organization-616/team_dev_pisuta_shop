<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>新規会員登録</title>
	<link rel="stylesheet" href="/team_dev_pisuta_shop/css/registUserStyle.css">
</head>
<body>
	<jsp:include page="/header.jsp" />

	<div class="regist-page">
		<form class="regist-form" action="/team_dev_pisuta_shop/UserServlet" method="post">
			<input type="hidden" name="action" value="new">
			<div class="title">会員情報の入力</div>
			<div class="err">${message}<br></div>
		
			氏名<br>
			<input type="text" name="name"><br>
			住所<br>
			<input type="text" name="address"><br>
			電話番号<br>
			<input type="number" name="tel"><br>
			メールアドレス<br>
			<input type="text" name="email"><br>
			生年月日<br>
			<input type="date" name="birthday" required><br>
			パスワード<br>
			<input type="password" name="password"><br>
			パスワード確認用<br>
			<input type="password" name="confirm"><br>
		
			<button>新規登録</button>
		</form>
	</div>

</body>
</html>