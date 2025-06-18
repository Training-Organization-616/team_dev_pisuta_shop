<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>会員管理</title>
<link href="/team_dev_pisuta_shop/css/profileStyle.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/header.jsp" />
<div>
	<h2>会員管理画面</h2>
	<div id="parent">


		<div id="child1">
			会員情報<br> ${user.id}<br> ${user.name}<br>
			${user.address}<br> ${user.tel}<br> ${user.email}<br>
			${user.birthday}<br> 
			<a href="/team_dev_pisuta_shop/UserServlet?action=edit">情報変更</a>
		</div>

		<div id="child2">
		<a href="/team_dev_pisuta_shop/ListingServlet" class="button">商品登録</a>
		</div>
	</div>
</div>
</body>
</html>