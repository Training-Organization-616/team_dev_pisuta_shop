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


<form action="/team_dev_pisuta_shop/ItemServlet" method="post">
		<button>購入</button>
		<input type="hidden" name="action" value="confirm"> <br>
	</form>
	
</body>
</html>