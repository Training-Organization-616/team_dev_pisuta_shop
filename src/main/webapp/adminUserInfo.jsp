<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>会員管理</title>
<link href="/team_dev_pisuta_shop/css/adminUserInfoStyle.css"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="/adminHeader.jsp" />

	<div>
		<table class="content" border="1">
			<tr>
				<th>会員番号</th>
				<td>${user.id}</td>
			</tr>
			<br>
			<tr>
				<th>氏名</th>
				<td>${user.name}</td>
			</tr>
			<br>
			<tr>
				<th>住所</th>
				<td>${user.address}</td>
			</tr>
			<br>
			<tr>
				<th>電話番号</th>
				<td>${user.tel}</td>
			</tr>
			<br>
			<tr>
				<th>メールアドレス</th>
				<td>${user.email}</td>
			</tr>
			<br>
			<tr>
				<th>生年月日</th>
				<td>${user.birthday}</td>
			</tr>
			<br>
		</table>
	</div>

	<div>
		<div class="delete">
			<button value="${user.id}">退会</button>
		</div>
	</div>
	<div>
	<div class="return">
		<form action="/team_dev_pisuta_shop/UserManageServlet" method="get">
			<button>一覧へ戻る</button>
		</form>
	</div>
	</div>
	


	<dialog id="deleteDialog">
	<div class="dialog_content">
		<p>退会させますか？</p>
		<form action="/team_dev_pisuta_shop/UserManageServlet" method="post">
			<input class="delete_id" type="hidden" name="userId"
				value="${user.id}">
			<button type="submit" name="action" value="delete">はい</button>
		</form>
		<form method="dialog">
			<button>いいえ</button>
		</form>
	</div>
	</dialog>

	<script>
	const deleteButton = document.querySelector('.delete');
	const dialog = document.querySelector('#deleteDialog');
	
		deleteButton.addEventListener('click', () => {
			dialog.showModal();
});
	
	</script>
</body>
</html>