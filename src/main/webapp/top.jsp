<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<title>商品一覧</title>
</head>


<body>
	C to C売買システム
	<h1>商品一覧</h1>


	<c:forEach items="${items}" var="item">
		<div>${item.name}<br> ${item.price}
			<form action="/team_dev_pisuta_shop/ItemServlet" method="post">
				<button>購入</button>
				<input type="hidden" name="action" value="confirm"> <br>
			</form>
		</div>
	</c:forEach>




</body>


</html>