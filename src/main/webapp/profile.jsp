<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>会員管理</title>
<link href="/team_dev_pisuta_shop/css/profileStyle.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/header.jsp" />
<div>
	<h2>会員管理画面</h2>
	<div id="parent">


		<div id="child1">
			<div class="location0">
			<p class="info_title">会員情報</p>
			</div>
			<div class="location1">
			<p class="info_lable">会員番号</p>
			<p class="info_data">${user.id}</p>
			<p class="info_lable">氏名</p>
			<p class="info_data">${user.name}</p>
			<p class="info_lable">住所</p>
			<p class="info_data">${user.address}</p>
			<p class="info_lable">電話番号</p>
			<p class="info_data">${user.tel}</p>
			<p class="info_lable">Email</p>
			<p class="info_data">${user.email}</p>
			<p class="info_lable">生年月日</p>
			<p class="info_data">${user.birthday}</p>
			</div>
			<div class="location2">
				<div class="edit_button">
				<a class="edit_link" href="/team_dev_pisuta_shop/UserServlet?action=edit">情報変更</a>
				</div>
			</div>
			<br>
			<div class="location3">
				<!-- 退会ダイアログ処理 --> 
						<button class="delete_button" type="button">退会</button>
			</div>		
		</div>
				
				<!--出品中の商品 -->
				
				<h3>出品中の商品</h3>
				
				<c:if test="${not empty items }">
				
				<table border="1">
					<tr>
						<th>商品名</th>
						<th>価格</th>
						<th>出品日時</th>
						<th>変更</th>
						<th>削除</th>
		          	</tr>
		          	<c:forEach items="${items}" var="item">
		          	<tr>
						<td>${item.name}</td>
						<td>${item.price}</td>
						<!--出品日時は仮です -->
						<td>${item.出品日時}</td>
						
						<td>
						<form class="regist-form" action="/team_dev_pisuta_shop/ListingServlet" method="post">
						<input type="hidden" name="action" value="edit">
						<button>変更</button>
						</form></td>
						
		          		<td>
						<form class="regist-form" action="/team_dev_pisuta_shop/ListingServlet" method="post">
						<input type="hidden" name="action" value="delete">
						<button>削除</button>
						</form></td>
		          	
		          	</tr>
		          	</c:forEach>
				
				
				</table>
				
				</c:if>

		<div id="child2">
		<form action="/team_dev_pisuta_shop/ListingServlet" method="post">
			<button type="submit" class="listing_button">商品登録</button>
		</form>
		</div>
	</div>
</div>


<dialog id="deleteDialog">
	<div class="dialog_content">
		<p>削除しますか</p>
		<form action="/team_dev_pisuta_shop/UserServlet" method="post">
			<input class="delete_id" type="hidden">
			<button type="submit" name="action" value="remove">はい</button>
		</form>
		<form method="dialog">
			<button>いいえ</button>
		</form>
	</div>
	</dialog>

	<script>
	const deleteButton = document.querySelector('.delete_button');
	const dialog = document.querySelector('#deleteDialog');
	
		deleteButton.addEventListener('click', () => {
			dialog.showModal();
});
	
	</script>


</body>
</html>