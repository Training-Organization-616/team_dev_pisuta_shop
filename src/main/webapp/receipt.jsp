<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<title>購入情報</title>
<link href="/team_dev_pisuta_shop/css/receiptStyle.css" rel="stylesheet"type="text/css">
</head>

<body>
	<jsp:include page="/header.jsp" />

	<h1>購入情報</h1> 
	<span class="haikei2">
	 取引番号：${deal.id}・出品番号：${item.sellerId}<br>
	 商品名：${item.name}<br>
	 価格：${item.price}円<br>
	 </span>
	 <p1>商品の購入が確定しました！</p1><br>
	 
	<br>
	<!-- 商品一覧画面へ遷移 -->
	<a href="/team_dev_pisuta_shop/ItemServlet">商品一覧へ戻る</a>

</body>
</html>