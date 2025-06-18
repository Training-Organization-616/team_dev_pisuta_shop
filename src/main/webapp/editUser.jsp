<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>会員情報変更</title>
	<link rel="stylesheet" href="/team_dev_pisuta_shop/css/editUserStyle.css">
</head>
<body>
	<jsp:include page="/header.jsp" />
	
	<div class="edit-page">
		<form class="edit-form" action="/team_dev_pisuta_shop/UserServlet" method="post">
			<input type="hidden" name="action" value="update">
			<div class="title">会員情報の変更</div>
			<div class="err">${message}<br></div>
			
			氏名<br>
			<input type="text" name="name" value="${user.name}"><br>
			住所<br>
			<input type="text" name="address" value="${user.address}"><br>
			電話番号<br>
			<input type="number" name="tel" value="${user.tel}"><br>
			メールアドレス<br>
			<input type="text" name="email" value="${user.email}"><br>
			生年月日<br>
			<input type="date" name="birthday" value="${user.birthday}" required><br>
			パスワード<br>
			<input type="password" name="password"><br>
			パスワード確認用<br>
			<input type="password" name="confirm"><br>
			
			<button>変更完了</button>
		</form>
	</div>
	
    <form action="/team_dev_pisuta_shop/UserServlet" method="post">
		<button>キャンセル</button>
	</form>
</body>
</html>