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

<form action="/team_dev_pisuta_shop/UserManageServlet" method="post">
<input type="text" name="userId" placeholder="会員番号">
<button>検索</button>
<input type="hidden" name="action" value="search">
<br>
</form>

<table border="1">
			<tr>
				<th>会員番号</th>
				<th>氏名</th>
				<th>住所</th>
				<th>電話番号</th>
				<th>email</th>
				<th>生年月日</th>
			</tr>


			<!--c:ifは数字を「仕事中」のように直すためのもの-->

			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.name}</td>
					<td>${user.address}</td>
					<td>${user.tel}</td>
					<td>${user.email}</td>
					<td>${user.birthday}</td>
					
					
				</tr>
			</c:forEach>
		</table>



${list.id}

</body>
</html>