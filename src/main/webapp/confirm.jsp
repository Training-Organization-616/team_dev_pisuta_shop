<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">



<head>
<meta charset="UTF-8">
<title>購入確認</title>
</head>


<body>

C to C 売買システム
購入確認画面<br>


${item.id}・${item.sellerId}<br>
${item.name}<br>
${item.price}<br>

<!-- 購入情報画面へ遷移 -->

<a href="/team_dev_pisuta_shop/ItemServlet?action=buy">購入確定</a>

<!-- 商品一覧画面へ遷移 -->
<a href="/top.jsp">キャンセル</a>




</body>
</html>