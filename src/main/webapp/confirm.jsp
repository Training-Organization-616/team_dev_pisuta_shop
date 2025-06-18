<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">



<head>
<meta charset="UTF-8">
<title>購入確認</title>
	<link href="/team_dev_pisuta_shop/css/confirmStyle.css" rel="stylesheet"type="text/css">
</head>


<body>
	
	<jsp:include page="/header.jsp" />
	
	<div class="confirm-page">
	<div class="confirm-form">
	<div class="title"><b>購入確認画面</b></div> 
	<br>
	 商品番号<br>
	 <div class="field">${item.id}</div>
	 出品者名<br>
	 <div class="field">${sellerName}</div>
	 商品名<br>
	 <div class="field">${item.name}</div>
	 価格<br>
	 <div class="field">${item.price}円</div>
	</div>
	</div>

	<!-- 購入情報画面へ遷移 -->
	<form action="/team_dev_pisuta_shop/ItemServlet" method="post">
		<input type="hidden" name="itemId" value="${item.id }">
		<input type="hidden" name="action" value="buy">
		<button>購入確定</button>
	</form>
	

	<!-- 商品一覧画面へ遷移 -->
<!--	<a href="/team_dev_pisuta_shop/ItemServlet">キャンセル</a>-->
	<form action="/team_dev_pisuta_shop/ItemServlet" method="post">
		<button>キャンセル</button>
	</form>
	
	<footer></footer>

</body>
</html>