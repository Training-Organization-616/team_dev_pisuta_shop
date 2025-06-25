<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">



<head>
<meta charset="UTF-8">
<title>購入確認</title>
<link href="/team_dev_pisuta_shop/css/confirmStyle.css" rel="stylesheet"
	type="text/css">
</head>


<body>

	<jsp:include page="/header.jsp" />

<div class="confirm">
	
	<div class="title">
		<b>こちらの商品を購入しますか？</b>
	</div>

	<div class="confirm-page">
		<div class="confirm-form">
			
			<div class="parent">
			<div class="img-form">
				<img src="${pageContext.request.contextPath }/upload/${item.fileName}">
			</div>
			
			<!--	 商品番号<br>-->
			<!--	 <div class="field">${item.id}</div>-->
			<!--	 出品者名<br>-->
			<!--	 <div class="field">${sellerName}</div>-->
			<!--	 商品名<br>-->
			<!--	 <div class="field">${item.name}</div>-->
			<!--	 価格<br>-->
			<!--	 <div class="field">${item.price}円</div>-->

			<div class="item-form">
			
				<div class="name"><b>${item.name}</b></div>
<!--				<div class="price"><b>${item.price}円</b></div>-->
				<div class="price"><b class="priceComma" data-value="${item.price}"></b></div>
				<div class="field-title">商品の説明</div>
				<div class="field-comment">${item.comment}</div>
				
				<div class="user-form">
				<div class="user-info">
					<div class="user-title">購入者氏名：</div>
					<div class="field">${name}</div>
				</div>
				
				<div class="user-info">
					<div class="user-title">配送先住所：</div>
					<div class="field">${address}</div>
				</div>
				</div>
				
				<!-- 購入情報画面へ遷移 -->
				<form action="/team_dev_pisuta_shop/ItemServlet" method="post">
					<input type="hidden" name="itemId" value="${item.id}">
					<input type="hidden" name="action" value="buy">
					<button>購入確定</button>
				</form>
				
			</div>
			</div>
		</div>
	</div>


	<!-- 商品一覧画面へ遷移 -->
	<!--	<a href="/team_dev_pisuta_shop/ItemServlet">キャンセル</a>-->
	<form action="/team_dev_pisuta_shop/ItemServlet" method="post">
		<input type="hidden" name="action" value="detail">
		<input type="hidden" name="itemId" value="${item.id}">
		<button>キャンセル</button>
	</form>
</div>

<!--		金額にカンマ表示-->
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
	<footer></footer>

</body>
</html>