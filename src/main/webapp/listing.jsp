<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>商品登録</title>
	<link rel="stylesheet" href="/team_dev_pisuta_shop/css/listingStyle.css">
	<script src="/team_dev_pisuta_shop/javascript/listing.js" ></script>
</head>
 
<body>

	<jsp:include page="/header.jsp" />

	<div class="regist-page">
		<form class="regist-form" action="/team_dev_pisuta_shop/ListingServlet" method="post" enctype="multipart/form-data">
			<input type="hidden" name="action" value="add">
			<div class="title"><b>商品情報を入力してください</b><br></div>
			<!-- 入力に誤りがあった場合出力 -->
			<div class="err">${message}<br></div>
			
			<div>
				<input type="file" name="product_image" accept=".png" >
			</div>
			
			商品名<br>
			<input class="input-text" type="text" name="name" placeholder="50字以内"><br>
			
			カテゴリー<br>
			<div class="radio">
			<c:forEach items="${categories}" var="category">
				<input id="ct${category.id }" type="radio" name="categoryId" value="${category.id}" required>
				<label for="ct${category.id }">${category.name}</label>
			</c:forEach><br>
			</div>
			
			価格<br>
			<div class="price_container">
			<span class="unit">￥</span>
			<input class="input-text" type="number" name="price" min="1" max="100000000">
			</div><br>
			
			状態<br>
			<div class="radio">
			<c:forEach items="${conditions}" var="condition">
				<c:choose>
					<c:when test="${condition.id eq 3}">
						<input id="cd${condition.id }" type="radio" name="conditionId" value="${condition.id}" checked>
						<label for="cd${condition.id }">${condition.name}</label><br>
					</c:when>
					<c:otherwise>
						<input id="cd${condition.id }" type="radio" name="conditionId" value="${condition.id}">
						<label for="cd${condition.id }">${condition.name}</label>
					</c:otherwise>
				</c:choose>	
			</c:forEach><br>
			</div>
		
			コメント<br>
			<textarea rows="" cols="" name="comment" placeholder="任意"></textarea><br>
			<!-- 会員管理画面へ遷移 -->
			<button>出品</button>
		</form>
	</div>

<!-- 商品名　必須100字以内 -->
<!-- カテゴリー、価格、状態、必須 -->
<!-- メモは要件なし -->


<!-- 会員管理画面へ遷移 -->

</body>
</html>