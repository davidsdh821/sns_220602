<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="header bg-secondary d-flex justify-content-between mb-5">
	<div class="logo">
		<h1 class="text-white p-4 font-weight-bold">gram</h1>
	</div>
	
	<div class="login-info">
		<%-- session 정보가 있을 때만 출력 --%>
		<c:if test="${not empty userName}">
		<div class="mt-5 mr-4">
			<span class="text-white">${userName}님 안녕하세요</span>
			<a href="/user/sign_out" class="ml-2 text-white font-weight-bold">로그아웃</a>
		</div>
		</c:if>
	</div>