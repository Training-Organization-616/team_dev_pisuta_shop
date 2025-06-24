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

<div class="edit">
<div class="edit-page">
	
		<div class="detail">
			<div class="detail-page">
				<div class="detail-form">
				<div class="img-form">
					<img src="${pageContext.request.contextPath }/upload/${item.fileName}">
				</div>
			
		
				<div class="item-form">
			
					
					<div class="item-name"><b>${item.name}</b></div>
					<div class="item-price"><b>${item.price}円</b></div>
					<div class="item-content">商品の説明</div>
					<div class="field_hight">${item.comment}</div>
					
			
					<div class="item-info">
						<div class="field-title">カテゴリー：</div><div class="field">${category}</div>
					</div>
					<div class="item-info">
						<div class="field-title">状態：　　　</div><div class="field">${condition}</div>
					</div>
					<div class="item-info">
						<div class="field-title">出品者：　　</div><div class="field">${sellerName}</div>
					</div>
			
					<div class="confirm-button">
					<c:if test="${item.status eq true}">
						<form action="/team_dev_pisuta_shop/ItemServlet" method="post">
							<button>購入確認へ</button>
							<input type="hidden" name="action" value="confirm">
							<input type="hidden" name="itemId" value="${item.id}">							</form>
					</c:if>
					</div>
			
				</div>
				</div>
			</div>
		
		
			<div>
				<form action="/team_dev_pisuta_shop/ItemServlet" method="post">
					<button>商品一覧へ</button>
				</form>
			</div>
		</div>
	
</div>
</div>
	
	<footer></footer>
</body>
</html>