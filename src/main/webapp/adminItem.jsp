<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>商品管理</title>
<link rel="stylesheet" href="/team_dev_pisuta_shop/css/adminItemStyle.css">
<script src="/team_dev_pisuta_shop/javascript/adminItem.js"></script>
</head>
<body>
	<jsp:include page="/adminHeader.jsp" />
	
	<div class="admin-item">
	
	<div class="input_container">
		<form class="search_form" action="/team_dev_pisuta_shop/ItemManageServlet" method="post">
			<input class="input_key" type="text" name="keyword" placeholder="キ－ワード"> 
			<input class="input_name" type="text" name="userName" placeholder="ユーザー名">
			<button class="button_search" type="submit" name="action" value="search">検索</button>
			<button class="button_return">一覧へ戻る</button>
		</form>
	</div>
	<div class="container">
	
		<c:if test="${empty items }">
		
		
			<p class=mozi>商品が存在しません</p>
		</c:if>
	

		
	<div class="item_container">
		<c:forEach items="${items }" var="item">
			<div class="item">
				<div><img src="${pageContext.request.contextPath }/upload/${item.fileName}" class="img-form"></div>
				<p class="name"><b>${item.name}</b></p>
				<p class="price"><b class="priceComma" data-value="${item.price}">${item.price}円</b></p>
				<c:forEach items="${users}" var="user">
					<c:if test="${item.sellerId == user.id }">
					<p class="user">${user.name}</p>
					</c:if>
				</c:forEach>
				<div class="delete">
					<button id="delete_button" type="button" value="${item.id }">削除</button>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="back_container">
		<form action="/team_dev_pisuta_shop/AdminServlet" method="post">
			<button class="button_back">戻る</button>
		</form>
	</div>
	</div>
	
	<footer></footer>
	
	<!--金額にカンマ表示-->
		<script>
		document.addEventListener("DOMContentLoaded", function () {
			  const priceCommaCells = document.querySelectorAll(".priceComma");

			  priceCommaCells.forEach(cell => {
			    const value = parseInt(cell.dataset.value, 10); // data-value属性から数値を取得
			    if (!isNaN(value)) {
			      cell.textContent = value.toLocaleString("ja-JP") + "円"; // カンマ区切りでフォーマット
			    }
			  });
			});

		</script>
	
<!--ダイアログ-->
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