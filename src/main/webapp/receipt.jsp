<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<link href="/team_dev_pisuta_shop/css/receiptStyle.css" rel="stylesheet">
<head>
<meta charset="UTF-8">
<title>購入情報</title>
</head>

<body>
	<jsp:include page="/header.jsp" />

	<div class="receipt">
	<div class="receipt-page">
	<div class="receipt-form">
	<div class="title"><b>購入情報</b></div> 
	
	 <!-- 一次リリースで表示していた内容 -->
	 <!--取引番号<br>  -->
	 <!--<div class="field">${deal.id}</div>-->
	 <!--出品者名<br>-->
	 <!--<div class="field">${sellerName}</div>-->
	 
	 <!-- 二次リリースで表示内容 -->
	 <!-- 画像添付予定 -->
	 商品名<br>
	 <div class="field"> ${item.name}</div>
	 価格<br>
	 <div class="field">${item.price}円</div>
	 商品の説明<br>
	 <div class="field"> ${item.comment}</div>
	 配送先住所<br>
	 <div class="field"> ${user.address}</div>
	 

	 <div class="message"><b>商品の購入が確定しました！</b></div>
	 </div>
	 </div>
	 
	
	<!-- 商品一覧画面へ遷移 -->
	<form action="/team_dev_pisuta_shop/ItemServlet" method="post">
		<button>商品一覧へ</button>
	</form>
<!--	<a href="/team_dev_pisuta_shop/ItemServlet">商品一覧へ戻る</a>-->
	</div>
	
	<footer></footer>

</body>
</html>