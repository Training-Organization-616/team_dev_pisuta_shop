<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/header.jsp" />

	<div>
		<form action="/team_dev_pisuta_shop/UserManageServlet" method="post">
			<button>会員管理</button>
		</form>
	</div>

	<br>

	<div>
		<form action="/team_dev_pisuta_shop/ItemManageServlet" method="post">
			<button>商品管理</button>
		</form>
	</div>
</body>
</html>