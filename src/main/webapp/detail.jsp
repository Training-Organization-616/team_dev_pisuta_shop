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

	<div class="detail">
	
		<div class="detail-page">
	
			<div class="image-form">
				<div class="img-form">
				<img src="${pageContext.request.contextPath }/upload/${item.fileName}">
			</div>
			
			</div>
		
			<div class="detail-form">
			
				<div class=location0>
				<b class="item-name"><h2>${item.name}</h2></b><br>

				<b class="item-price"><h2>${item.price}円</h2></b><br>

				商品の説明<br>
			
				<div class="field_hight">${item.comment}</div>
			</div>
			
					<div style="display:flex"><div class="a1">カテゴリー：</div><div class="field">${category}</div></div>
					<div style="display:flex"><div class="a1">状態：</div><div class="field">${condition}</div></div>
					<div style="display:flex"><div class="a1">販売者：</div><div class="field">${sellerName}</div></div>
			
			

				<form action="/team_dev_pisuta_shop/ItemServlet" method="post">
				<button>購入確認へ</button>
				<input type="hidden" name="action" value="confirm"> <input
					type="hidden" name="itemId" value="${item.id}">
			</form>
			</div>
		
			
			
		</div>
		
		
	<div>
		<form action="/team_dev_pisuta_shop/ItemServlet" method="post">
			<button>商品一覧へ</button>
		</form>
	</div>
	
	</div>
	
	<footer></footer>
</body>
</html>