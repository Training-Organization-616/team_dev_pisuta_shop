<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>商品登録</title>
</head>

<body>
C to C 売買システム
商品登録<br>
購入情報を入力してください<br>
<!-- 入力に誤りがあった場合出力 -->
${message}<br>

<form action = "/team_dev_pisuta_shop/ListingServlet"method="post">
<input type="hidden" name="action" value="add"
<input type="text" name="name" placeholder="商品名"><br>
<input type="text" name="categotyId" placeholder="カテゴリー"><br>
<input type="text" name="price" placeholder="価格"><br>
<input type="text" name="condId" placeholder="状態"><br>
<input type="text" name="comment" placeholder="メモ"><br>
<!-- 会員管理画面へ遷移 -->
<button>出品</button>
/form>

<!-- 商品名　必須100字以内 -->
<!-- カテゴリー、価格、状態、必須 -->
<!-- メモは要件なし -->


<!-- 会員管理画面へ遷移 -->

</body>
</html>