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

	<div class="top-page">
		<div class="search">
			<div class="narrow"><b>絞り込み</b></div>

			<form action="/team_dev_pisuta_shop/ItemServlet" method="post">
				<input type="hidden" name="action" value="search"> 
				<div class="item_search">
				 <input type="text" name="keyword"
					placeholder="何をお探しですか？" value="${keyword}" class="search-text">
					<button class="search-button">検索</button>
				</div>
				カテゴリー
				<div class="field">
					<select name="categoryId">
						<option value="-1">全て</option>
						<c:forEach items="${categories}" var="category">
							<c:choose>
								<c:when test="${categoryId eq category.id}">
									<option value="${category.id}" selected>${category.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${category.id}">${category.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>

				価格
				<div class="field">
					<input type="number" name="minPrice" placeholder="最低価格"
						value="${minPrice}" class="price" min="0"> 円
				</div>
				
				<img src="/team_dev_pisuta_shop/upload/from.png" class="from">
				
				<div class="field">
					<input type="number" name="maxPrice" placeholder="最高価格"
						value="${maxPrice}" class="price" min="0"> 円
				</div>

				状態
				<div class="field">
					<select name="conditionId">
						<option value="-1">全て</option>
						<c:forEach items="${conditions}" var="condition">
							<c:choose>
								<c:when test="${conditionId eq condition.id}">
									<option value="${condition.id}" selected>${condition.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${condition.id}">${condition.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
				<div class="narrow-button">
					<button>絞り込み</button>
				</div>
			</form>
		</div>

		<div class="top-item">
			<div class="title">
				<b>商品一覧</b>
			</div>

			<c:if test="${empty items}">
				<div class="message">商品が存在しません</div>
			</c:if>

			<div class="item_container">
				<c:forEach items="${items}" var="item">
					<div class="item">

						<!--			<div class="img-form">-->
						<!--				<img src="${pageContext.request.contextPath }/upload/${item.fileName}">-->
						<!--			</div>-->



						<!--2次発注-->
						<form action="/team_dev_pisuta_shop/ItemServlet" method="post">
							<button class="button">
								<div>
									<img
										src="${pageContext.request.contextPath }/upload/${item.fileName}"
										class="img-form">
								</div>
							</button>
							<input type="hidden" name="action" value="detail"> <input
								type="hidden" name="itemId" value="${item.id }"> <input
								type="hidden" name="categoryId" value="${item.categoryId }">
							<input type="hidden" name=condId value="${item.condId }"><br>
						</form>

						<b class="name">${item.name}</b><br>

						
						<c:choose>
							<c:when test="${item.status eq true}">
								<option>${item.price}円</option>
							</c:when>

							<c:otherwise>
								<option class="soldout">soldout</option>
							</c:otherwise>
						</c:choose>

					</div>
				</c:forEach>
			</div>
		</div>


	</div>

	<footer></footer>
</body>
</html>