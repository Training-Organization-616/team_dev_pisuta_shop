<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<title>購入情報</title>
</head>

<body>
	<jsp:include page="/header.jsp" />

	C to C 売買システム 購入情報
	<br> ${deal.id}・${item.sellerId}
	<br> ${item.name}
	<br> ${item.price}
	<br> 商品の購入が確定しました！
	<br>
	<!-- 商品一覧画面へ遷移 -->
	<a href="/team_dev_pisuta_shop/ItemServlet">商品一覧へ戻る</a>

</body>
</html>