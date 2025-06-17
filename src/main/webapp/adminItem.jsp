<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/team_dev_pisuta_shop/css/adminItemStyle.css">
<script src="/team_dev_pisuta_shop/javascript/adminItem.js"></script>
</head>
<body>
	<jsp:include page="/adminHeader.jsp" />
	<div class="input_container">
	<form action="/team_dev_pisuta_shop/ItemManageServlet" method="post">
		<input class="input_key" type="text" name="keyword" placeholder="キ－ワード"> 
		<input class="input_name" type="text" name="userName" placeholder="ユーザー名">
		<button class="button_search" type="submit" name="action" value="search">検索</button>
	</form>
	</div>
	<div class="item_container">
	<c:forEach items="${items }" var="item">
		<div class="item">
			<p>${item.name }</p>
			<p>${item.price }円</p>
			<c:forEach items="${users }" var="user">
			<c:if test="${item.sellerId == user.id }">
			<p>${user.name }</p>
			</c:if>
			</c:forEach>
			<button type="button" class="delete" value="${item.id }">削除</button>
		</div>
	</c:forEach>
	</div>
	<div class="back_container">
	<form action="/team_dev_pisuta_shop/AdminServlet" method="post">
		<button class="button_back">戻る</button>
	</form>
</div>
	<dialog id="deleteDialog">
	<div class="dialog_content">
		<p id="itemName"></p>
		<p id="itemPrice"></p>
		<p class="check_massage">削除しますか</p>
		<div class="button_container">
		<form action="/team_dev_pisuta_shop/ItemManageServlet" method="post">
			<input class="delete_id" type="hidden" name="itemId" value="">
			<button class="button_yes" type="submit" name="action" value="delete">はい</button>
		</form>
		<form method="dialog">
			<button class="button_no">いいえ</button>
		</form>
		</div>
	</div>
	</dialog>
</body>
</html>