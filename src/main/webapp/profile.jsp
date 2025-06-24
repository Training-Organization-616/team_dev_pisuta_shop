<%@page import="la.dao.ItemsDAO"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>会員管理</title>
<link href="/team_dev_pisuta_shop/css/profileStyle.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/header.jsp" />

	<div class="profile">
		<div class="title">
			<b>会員管理</b>
		</div>
		<div id="parent">
			<div id="child1">
				<div class="location0">
					<div class="info_title">
						<b>会員情報</b>
					</div>
				</div>
				<div class="location1">
					<div class="info_lable">会員番号</div>
					<div class="info_data">${user.id}</div>
					<div class="info_lable">氏名</div>
					<div class="info_data">${user.name}</div>
					<div class="info_lable">住所</div>
					<div class="info_data">${user.address}</div>
					<div class="info_lable">電話番号</div>
					<div class="info_data">${user.tel}</div>
					<div class="info_lable">メールアドレス</div>
					<div class="info_data">${user.email}</div>
					<div class="info_lable">生年月日</div>
					<div class="info_data">${user.birthday}</div>
				</div>

				<div class="location2">
					<form action="/team_dev_pisuta_shop/UserServlet" method="post">
						<input type="hidden" name="action" value="edit">
						<button>情報変更</button>
					</form>
				</div>

				<!-- 退会ダイアログ処理 -->
				<div class="location3">
					<button class="delete_button">退会</button>
				</div>
			</div>

			<div id="child2">

				<!--出品中の商品 -->
				<div class="listing_box">
					<div class="info_title">
						<b>出品中の商品</b>
					</div>

					<c:choose>
						<c:when test="${not empty items }">
							<table border="1">
								<tr>
									<th class="name">商品名</th>
									<th class="price">価格</th>
									<th class="time">出品日時</th>
									<th>変更</th>
									<th>削除</th>
								</tr>
								<c:forEach items="${items}" var="item">
									<tr>
										<td>${item.name}</td>
										<td>${item.price}円</td>
										<!--出品日時は仮です -->
										<td>${item.createdAt }</td>

										<td>
											<form class="regist-form" action="/team_dev_pisuta_shop/ListingServlet" method="post">
												<input type="hidden" name="action" value="edit"> <input type="hidden" name="itemId" value="${item.id}">
												<button>変更</button>
											</form>
										</td>

										<td>
											<!--	<form class="regist-form" action="/team_dev_pisuta_shop/ListingServlet" method="post">-->
											<!--	<input type="hidden" name="action" value="delete">-->
											<!--	<input type="hidden" name="itemId" value="${item.id}">-->
											<div class="regist-form">
												<button value="${item.id}" class="remove_button">削除</button>
											</div> 
											<!--</form>-->
										</td>
									</tr>
								</c:forEach>
							</table>
						</c:when>

						<c:otherwise>
							<div class="empty">出品中の商品はありません</div>
						</c:otherwise>
					</c:choose>
				</div>

				<form action="/team_dev_pisuta_shop/ListingServlet" method="post">
					<button type="submit" class="listing_button">商品登録へ</button>
				</form>
			</div>
		</div>
	</div>


	<dialog id="deleteDialog">
	<div class="dialog_content">
		<p>退会しますか</p>
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


<!--	削除ボタンダイアログ表示-->
	<dialog id="removeDialog">
	<div class="dialog_content">
		<p>削除しますか</p>

			<form action="/team_dev_pisuta_shop/ListingServlet" method="post">
				<input class="remove_id" type="hidden">
				 <input type="hidden" id="removeId" name="itemId" value="${item.id}">
				<button type="submit" name="action" value="delete">はい</button>
			</form>
		<form method="dialog">
			<button>いいえ</button>
		</form>
	</div>
	</dialog>

		<script>
		const removeButton = document.querySelectorAll('.remove_button');
		const dialog1 = document.querySelector('#removeDialog');
		const removeId = document.querySelector('#removeId');

		removeButton.forEach(function(button) {

			<!--removeId.valueは145行目のvalueの値を示している-->
			<!--button.valueは上の削除ボタンのvalue属性の値-->
		    button.addEventListener('click', () => {
		    	removeId.value = button.value;
				dialog1.showModal();
		})
	});
		</script>

</body>
</html>