<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/team_dev_pisuta_shop/css/adminTopStyle.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/adminHeader.jsp" />
	<div class="content">
		<div class="content1">
			<form action="/team_dev_pisuta_shop/UserManageServlet" method="post">
				<button>会員管理</button>
			</form>
		</div>

		<br>

		<div class="content2">
			<form action="/team_dev_pisuta_shop/ItemManageServlet" method="post">
				<button>商品管理</button>
			</form>
		</div>
	</div>
</body>
</html>