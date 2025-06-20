<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
<link rel="stylesheet" href="/team_dev_pisuta_shop/css/detailStyle.css">
</head>
<body>
	<div>
		画像<br> 商品名:
		<div class="field">${item.name}</div>

		価格:
		<div class="field">${item.price}円</div>

		商品の説明<br>
		<div class="field">${item.comment}</div>

		<div class="field">カテゴリ：${category}</div>
		<div class="field">状態：${condition}</div>
		<div class="field">販売者：${sellerName}</div>
		<br>

		<form action="/team_dev_pisuta_shop/ItemServlet" method="post">
			<button>購入確認へ</button>
			<input type="hidden" name="action" value="confirm"> <input
				type="hidden" name="itemId" value="${item.id }">
	</div>

	<div>
		<form action="/team_dev_pisuta_shop/ItemServlet" method=get>
			<button>商品一覧へ</button>
		</form>
	</div>
</body>
</html>