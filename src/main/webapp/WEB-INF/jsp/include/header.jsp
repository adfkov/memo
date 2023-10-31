<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- 코어 라이브러리 --%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="h-100 d-flex align-items-center">
	<h2 class="font-weight-bold ml-4">메모 게시판</h2>
	
	<%-- 로그인 정보 --%>
	<div>
	 <c:if test="${not empty userName}">
	 <%-- model 과 session 의 이름이 겹치지 않게 한다. --%>
		<span>${userName}님 안녕하세요</span>
		<a href="/user/sign-out">로그아웃</a>
	</c:if>
	</div>
	
	<table class="table">
	<thead>
		<tr>
			<th>No.</th>
			<th>제목</th>
			<th>작성날짜</th>
			<th>수정날짜</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${postList}" var="post">
			<tr>
			<td>${post.id}</td>
			<td>${post.subject}</td>
			<td> <%--zonedDateTime -> Date -> String --%>
			<fmt:parseDate value="${post.createdAt}" var="parsedCreatedAt" pattern="yyyy-MM-dd'T'HH:mm:ss"/>
			<fmt:formatDate value="${parsedCreatedAt}" pattern="yyyy년 M월 d일 HH:mm:dd" />
			</td>
			<td>
			<fmt:parseDate value="${post.createdAt}" var="parsedCreatedAt" pattern="yyyy-MM-dd'T'HH:mm:ss"/>
			<fmt:formatDate value="${parsedCreatedAt}" pattern="yyyy년 M월 d일 HH:mm:dd" />
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
	
	<div class="d-flex justify-content-end"> 
		<a class="btn btn-warning" href="/post/post-create-view">글쓰기</a>
	</div>
		
</div>