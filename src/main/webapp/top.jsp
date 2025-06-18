<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<title>商品一覧</title>
<link rel="stylesheet" href="/team_dev_pisuta_shop/css/topStyle.css" >
</head>

<body>
	<jsp:include page="/header.jsp" />
	

	<div class="title"><b>商品一覧</b></div>
	
	<c:if test="${empty items}"><div class="message">商品が存在しません</div></c:if>

	<div class="item_container">
		<c:forEach items="${items}" var="item">
			<div class="item">
			<b class="name">${item.name}</b><br> 
			￥${item.price}
			<form action="/team_dev_pisuta_shop/ItemServlet" method="post">
				<button>購入</button>
				<input type="hidden" name="action" value="confirm">
				<input type="hidden" name="itemId" value="${item.id }"><br>
			</form>
			</div>
		</c:forEach>
	</div>
	
	<footer></footer>
</body>
</html>