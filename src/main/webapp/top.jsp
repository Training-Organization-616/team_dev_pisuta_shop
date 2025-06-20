<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<title>商品一覧</title>
<link rel="stylesheet" href="/team_dev_pisuta_shop/css/topStyle.css">
</head>

<body>
	<jsp:include page="/header.jsp" />


	<div class="title">
		<b>商品一覧</b>
	</div>

	<c:if test="${empty items}">
		<div class="message">商品が存在しません</div>
	</c:if>

	<div class="item_container">
		<c:forEach items="${items}" var="item">
			<div class="item">
				<b class="name">${item.name}</b><br> ￥${item.price}

				<!--2次発注-->
				<form action="/team_dev_pisuta_shop/ItemServlet" method="post">
					<button>購入</button>
					<input type="hidden" name="action" value="detail"> <input
						type="hidden" name="itemId" value="${item.id }"> <input
						type="hidden" name="categoryId" value="${item.categoryId }">
					<input type="hidden" name=condId value="${item.condId }"><br>
				</form>

			</div>
		</c:forEach>
	</div>

	<div>
		<form action="/team_dev_pisuta_shop/ItemServlet" method="post">
		<input type="text" name="keyword" placeholder="何をお探しですか？">
		<button>検索</button>
		<input type="hidden" name="action" value="search">
		
		
		<h1>絞り込み</h1>
		
			カテゴリー 
			<select name="categoryId">
			<option>全て</option>
			<c:forEach items="${categories}" var="category">
				<option value="${category.id}">${category.name}</option>
			</c:forEach>
			</select> 
			
			価格
			<input type="text" name="minPrice" placeholder="最低価格">～<input type="text"name="maxPrice" placeholder="最高価格">
			
			状態
			<select name="conditionId">
			<option>全て</option>
			<c:forEach items="${conditions}" var="condition">
				<option value="${condition.id}">${condition.name}</option>
			</c:forEach>
			</select> 

			<button>絞り込み</button>
			<input type="hidden" name="action" value="search">
		</form>

	</div>

	<footer></footer>
</body>
</html>