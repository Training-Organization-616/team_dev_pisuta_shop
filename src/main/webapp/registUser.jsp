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

	<div class="regist">
	<div class="regist-page">
		<form class="regist-form" action="/team_dev_pisuta_shop/UserServlet" method="post">
			<input type="hidden" name="action" value="new">
			<div class="title"><b>会員情報の入力</b></div>
			<div class="err">${message}</div>
		
			<div class="field-title">氏名</div>
			<div class="field">
				<input type="text" name="name"><br>
			</div>
			<div class="field-title">住所</div>
			<div class="field">
				<input type="text" name="address">
			</div>
			<div class="field-title">電話番号</div>
			<div class="field">
				<input type="number" name="tel" placeholder="ハイフンなし">
			</div>
			<div class="field-title">メールアドレス</div>
			<div class="field">
				<input type="text" name="email">
			</div>
			<div class="field-title">生年月日</div>
			<div class="field">
				<input type="date" name="birthday" required>
			</div>
			<div class="field-title">パスワード</div>
			<div class="field">
				<input type="password" name="password" placeholder="6文字以上16文字以下">
			</div>
			<div class="field-title">パスワード確認用</div>
			<div class="field">
				<input type="password" name="confirm">
			</div>
		
			<button>新規登録</button>
		</form>
	</div>
	
	<div class="link">
		<form action="/team_dev_pisuta_shop/LoginServlet" method="post">
			<button>ログイン画面へ</button>
		</form>
	</div>
	</div>
	
	<footer></footer>

</body>
</html>