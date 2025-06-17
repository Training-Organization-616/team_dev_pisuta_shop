<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
	<a href="/team_dev_pisuta_shop/ItemServlet"><b>Pisuta Shop</b></a>

	<c:choose>
		<c:when test="${user.name ne null}">
			ようこそ、
			<a href="/team_dev_pisuta_shop/UserServlet">${user.name}</a>さん<br>
		</c:when>
		<c:otherwise>
			<a href="/team_dev_pisuta_shop/LoginServlet">ログイン</a>
		</c:otherwise>
	</c:choose>

</div>