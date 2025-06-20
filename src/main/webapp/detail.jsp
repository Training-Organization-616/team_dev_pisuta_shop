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

	画像 商品名
	<br>
	<div class="field">${item.name}</div>
	価格
	<br>
	<div class="field">${item.price}円</div>
	商品の説明
	<br>
	<div class="field">${item.comment}</div>

	<!--カテゴリ-->
	${item.category_id}
	<!--商品の状態-->
	${item.cond_id}
	<!--出品者名-->
	${sellerName}

	<button>購入確認へ</button>

	<button>商品一覧へ</button>

</body>
</html>