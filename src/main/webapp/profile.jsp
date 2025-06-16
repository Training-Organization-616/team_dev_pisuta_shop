<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>会員管理</title>
</head>
<body>
	
	<h2>会員管理画面</h2>
	
	<div>会員情報<br>
		
		${user.id}<br>
		${user.name}<br>
		${user.address}<br>
		${user.tel}<br>
		${user.email}<br>
		${user.birthday}<br>
		
		<a href="/team_dev_pisuta_shop/UserServlet?action=edit">情報変更</a>
	</div>
	
	<a href="/team_dev_pisuta_shop/ListingServlet">商品登録</a>
	
</body>
</html>