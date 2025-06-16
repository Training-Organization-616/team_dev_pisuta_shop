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

<table border="1">
			<tr>
				<th>会員番号</th><th>${user.id}</th>
				<th>氏名</th><th>${user.name}</th>
				<th>住所</th><th>${user.address}</th>
				<th>電話番号</th><th>${user.tel}</th>
				<th>email</th><th>${user.email}</th>
				<th>生年月日</th><th>${user.birthday}</th>
			</tr>
			
</body>
</html>