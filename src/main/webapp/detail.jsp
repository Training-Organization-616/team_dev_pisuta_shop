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

	<jsp:include page="/header.jsp" />

	<div class="detail-page">
	
		<div class="image-form">
			画像<br>
		</div>
		
		<div class="detail-form">
			<b class="item-name">${item.name}</b><br>

		<b class="item-price">${item.price}円</b><br>

			商品の説明<br>
			
			<div class="field">${item.comment}</div>
			
			
			<div id="child1">
			カテゴリー：<br>
			状態：<br>
			販売者：<br>
			</div>
			<div id="child2">
			<div class="field">${category}</div>
			<div class="field">${condition}</div>
			<div class="field">${sellerName}</div>
			<br>
			

			<form action="/team_dev_pisuta_shop/ItemServlet" method="post">
				<button>購入確認へ</button>
				<input type="hidden" name="action" value="confirm"> <input
					type="hidden" name="itemId" value="${item.id}">
			</form>
			</div>
		
			
			
			</div>
			
		</div>
		
	</div>

	<div>
		<form action="/team_dev_pisuta_shop/ItemServlet" method="post">
			<button>商品一覧へ</button>
		</form>
	</div>
	
	<footer></footer>
</body>
</html>