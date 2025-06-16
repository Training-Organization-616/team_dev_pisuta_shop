<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>会員情報変更</title>
</head>
<body>

	<h2>会員情報変更</h2>

	<p>${message}</p>
	
	<form action="/team_dev_pisuta_shop/UserServlet" method="post">
		<input type="hidden" name="action" value="update">
		
		氏名<br>
		<input type="text" name="name">${user.name}<br>
		住所<br>
		<input type="text" name="address">${user.address}<br>
		電話番号<br>
		<input type="text" name="tel">${user.tel}<br>
		メールアドレス<br>
		<input type="text" name="email">${user.email}<br>
		生年月日<br>
		<input type="text" name="birthday">${user.birthday}<br>
		パスワード<br>
		<input type="text" name="password"><br>
		パスワード確認用<br>
		<input type="text" name="confirm"><br>
		
		<button>変更完了</button>
	
	</form>

</body>
</html>