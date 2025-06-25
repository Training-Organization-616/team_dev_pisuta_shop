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
	
	<div class="message"><b>商品の購入が確定しました！</b></div>
	
	<div class="receipt-page">
	<div class="receipt-form">

	 <!-- 一次リリースで表示していた内容 -->
	 <!--取引番号<br>  -->
	 <!--<div class="field">${deal.id}</div>-->
	 <!--出品者名<br>-->
	 <!--<div class="field">${sellerName}</div>-->
	 
	 <!-- 二次リリースで表示内容 -->
	 <div class="parent">
	 <div class="img-form">
		<img src="${pageContext.request.contextPath }/upload/${item.fileName}">
	</div>
	
	<div class="item-form">
	 
	 <div class="name"><b>${item.name}</b></div>
	 <div class="price"><b>${item.price}円</b></div>
	 <div class="field-title">商品の説明</div>
	 <div class="field-comment"> ${item.comment}</div>
	 
	 <div class="user-form">
		<div class="user-info">
			<div class="user-title">購入者氏名：</div>
			<div class="field">${user.name}</div>
		</div>
				
		<div class="user-info">
			<div class="user-title">配送先住所：</div>
			<div class="field">${user.address}</div>
		</div>
	</div>
	 </div>
	 
	 </div>
	 </div>
	 </div>
	 
	
	<!-- 商品一覧画面へ遷移 -->
	<form action="/team_dev_pisuta_shop/ItemServlet" method="post">
		<button>商品一覧へ</button>
	</form>
	</div>
	
	<footer></footer>

</body>
</html>