<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>会員管理</title>
<link href="/team_dev_pisuta_shop/css/adminUserStyle.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/adminHeader.jsp" />
	
	<div class="admin-user">

	<div class="content">
		<form action="/team_dev_pisuta_shop/UserManageServlet" method="post">
			<input type="text" name="userId" placeholder="会員番号" class="textarea">
			<button class="search">検索</button>
			<input type="hidden" name="action" value="search"> <br>
		</form>
	</div>

		<div class="error">${message}</div>

	<div>
		<table border="1">
			<tr>
				<th class="id">会員番号</th>
				<th class="name">氏名</th>
				<th class="address">住所</th>
				<th class="tel">電話番号</th>
				<th class="email">メールアドレス</th>
				<th class="birthday">生年月日</th>
				<th class="open"></th>
			</tr>

			<c:forEach items="${users}" var="user">
				<tr>
					<td class="id1" >${user.id}</td>
					<td class="name1">${user.name}</td>
					<td class="address1">${user.address}</td>
					<td class="tel1">${user.tel}</td>
					<td class="email1">${user.email}</td>
					<td class="birthday1">${user.birthday}</td>
					<td class="open1"><form action="/team_dev_pisuta_shop/UserManageServlet"
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
	
	</div>

</body>
</html>