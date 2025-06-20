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

	画像
	<br>
	<br> 商品名
	<br>
	<div class="field">${item.name}</div>
	価格
	<br>
	<div class="field">${item.price}円</div>
	商品の説明
	<br>
	<div class="field">${item.comment}</div>

	カテゴリ：${category}
	<br> 状態：${item.condId}
	<br> 販売者：${sellerName}
	<br>


	<button>購入確認へ</button>

	<button>商品一覧へ</button>

</body>
</html>