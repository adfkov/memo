<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- 코어 라이브러리 --%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="h-100 d-flex align-items-center justify-content-between">
	<h2 class="font-weight-bold ml-4">메모 게시판</h2>
	
	<%-- 로그인 정보 --%>
	<div class="mr-5">
	 <c:if test="${not empty userName}">
	 <%-- model 과 session 의 이름이 겹치지 않게 한다. --%>
		<span>${userName}님 안녕하세요</span>
		<a href="/user/sign-out">로그아웃</a>
	</c:if>
	</div>

</div>