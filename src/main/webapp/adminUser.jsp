<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/team_dev_pisuta_shop/css/adminUserStyle.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/adminHeader.jsp" />

	<div class="content2">
		<form action="/team_dev_pisuta_shop/UserManageServlet" method="post">
			<input type="text" name="userId" placeholder="会員番号" class="textarea">
			<button class="search">検索</button>
			<input type="hidden" name="action" value="search"> <br>
		</form>
	</div>
	
	<br>

	<div>${message}</div>

	<div>
		<table class="content" border="1">
			<tr>
				<th>会員番号</th>
				<th>氏名</th>
				<th>住所</th>
				<th>電話番号</th>
				<th>email</th>
				<th>生年月日</th>
				<th></th>
			</tr>

			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.name}</td>
					<td>${user.address}</td>
					<td>${user.tel}</td>
					<td>${user.email}</td>
					<td>${user.birthday}</td>
					<td><form action="/team_dev_pisuta_shop/UserManageServlet"
							method="post">
							<input type="hidden" name="action" value="show">
							<button>表示</button>
							<input type="hidden" name="userId" value="${user.id}">
						</form></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<div>
		<form>
			<button class="return">戻る</button>
			<input type="hidden" name="action" value="top">
		</form>
	</div>

</body>
</html>