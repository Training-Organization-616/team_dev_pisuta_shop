<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border="1">
			<tr><th>会員番号</th><td>${user.id}</td></tr><br>
			<tr><th>氏名</th><th>${user.name}</th></tr><br>
			<tr><th>住所</th><th>${user.address}</th></tr><br>
				<tr><th>電話番号</th><th>${user.tel}</th></tr><br>
				<tr><th>email</th><th>${user.email}</th></tr><br>
				<tr><th>生年月日</th><th>${user.birthday}</th></tr><br>
				</table>
				
		<form action="/team_dev_pisuta_shop/UserManageServlet" method="post">
		<input type="hidden" name="action" value="delete">
		<button>退会</button>
		<input type="hidden" name="userId" value="${user.id}">
		</form>
		
		<form>
		<button>一覧へ戻る</button>
		<input type="hidden" name="action" value="user">
		</form>
			
			
	<dialog>
	<div class="dialog_content">
		<p>削除しますか</p>
		<form action="/team_dev_pisuta_shop/UserManageServlet" method="post">
			<input class="delete_id" type="hidden" name="deleteId" value="">
			<button type="submit" name="action" value="delete">はい</button>
		</form>
		<form method="dialog">
			<button>いいえ</button>
		</form>
	</div>
	</dialog>
	
</body>
</html>