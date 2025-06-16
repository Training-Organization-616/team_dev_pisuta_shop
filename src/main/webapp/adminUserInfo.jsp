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
				<th>会員番号</th><td>${user.id}</td><br>
				<th>氏名</th><th>${user.name}</th><br>
				<th>住所</th><th>${user.address}</th><br>
				<th>電話番号</th><th>${user.tel}</th><br>
				<th>email</th><th>${user.email}</th><br>
				<th>生年月日</th><th>${user.birthday}</th><br>
			</tr>
			
</body>
</html>