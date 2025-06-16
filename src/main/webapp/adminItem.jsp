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
	<form action="/team_dev_pisuta_shop/ItemManageServlet" method="post">
		<input type="text" name="keyword" placeholder="キ－ワード"> <input
			type="text" name="userName" placeholder="ユーザー名">
		<button type="submit" name="action" value="search">検索</button>
	</form>

	<c:forEach items="${items }" var="item">
		<div class="item">
			<p>${item.name }</p>
			<p>${item.price }</p>
			<button type="button" class="delete" value="${item.id }">削除</button>
		</div>
	</c:forEach>

	<form action="/team_dev_pisuta_shop/AdminServlet" method="post">
		<button>戻る</button>
	</form>

	<dialog id="deleteDialog">
	<div class="dialog_content">
		<p>削除しますか</p>
		<form action="/team_dev_pisuta_shop/ItemManageServlet" method="post">
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