<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/team_dev_pisuta_shop/css/adminTopStyle.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/adminHeader.jsp" />
	

	<table border="1">
			<tr><th>会員番号</th><td>${user.id}</td></tr><br>
			<tr><th>氏名</th><td>${user.name}</td></tr><br>
			<tr><th>住所</th><td>${user.address}</td></tr><br>
				<tr><th>電話番号</th><td>${user.tel}</td></tr><br>
				<tr><th>email</th><td>${user.email}</td></tr><br>
				<tr><th>生年月日</th><td>${user.birthday}</td></tr><br>
				</table>
				
		<button class="delete" value="${user.id}">退会</button>
		
		<form action="/team_dev_pisuta_shop/UserManageServlet" method="get">
			<button>一覧へ戻る</button>
		</form>
			
			
	<dialog id="deleteDialog">
	<div class="dialog_content">
		<p>削除しますか</p>
		<form action="/team_dev_pisuta_shop/UserManageServlet" method="post">
			<input class="delete_id" type="hidden" name="userId" value="${user.id}">
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