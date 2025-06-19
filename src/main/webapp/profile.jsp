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
				<form  id="delete" action="/team_dev_pisuta_shop/UserServlet" method="post" onsubmit="return remove();">
						<input type="hidden" name="action" value="remove"> 
						<button class="delete_button" type="submit">退会</button>
				</form><br>



				<script>
						function remove(){
    					let check = confirm('退会します。よろしいですか？');
    					console.log(check);
    				if (check){
	  					return true;
    				} else {
						return false;
    				}
				}
				</script>
			
			</div>
			 
			
		</div>

		<div id="child2">
		<form action="/team_dev_pisuta_shop/ListingServlet" method="post">
			<button type="submit" class="listing_button">商品登録</button>
		</form>
		</div>
	</div>
</div>


</body>
</html>