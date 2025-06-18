<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="/team_dev_pisuta_shop/css/headerStyle.css">

<div class="header">
	<div class="pisuta">
		<a href="/team_dev_pisuta_shop/ItemServlet"><b><i>Pisuta Shop</i></b></a>
	</div>
	
	<div class="logininfo">
		<c:choose>
			<c:when test="${user.name ne null}">
				ようこそ、
				<a href="/team_dev_pisuta_shop/UserServlet">${user.name}</a>さん
				<br>
				<div class="logout">
				<a href="/team_dev_pisuta_shop/LoginServlet?action=logout">ログアウト</a>
				</div>
			</c:when>
			<c:otherwise>
				<a href="/team_dev_pisuta_shop/LoginServlet">ログイン / 新規登録</a>
			</c:otherwise>
		</c:choose>
	</div>

</div>

