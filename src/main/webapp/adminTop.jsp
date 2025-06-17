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

	<div>
		<form>
			<button>会員管理</button>
			<input type="hidden" name="action" value="user">
		</form>
	</div>

	<br>

	<div>
		<form>
			<button>商品管理</button>
			<input type="hidden" name="action" value="item">
		</form>
	</div>
</body>
</html>