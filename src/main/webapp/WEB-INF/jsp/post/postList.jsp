<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 조각 페이지 -->
<div class="d-flex justify-content-center">
	<div class="w-50">
		<h1>글 목록</h1>
		
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
			<fmt:formatDate value="${post.createdAt}" pattern="yyyy년 M월 d일 HH:mm:dd" />
			</td>
			<td>
			<fmt:formatDate value="${post.createdAt}" pattern="yyyy년 M월 d일 HH:mm:dd" />
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
	
	<div class="d-flex justify-content-end"> 
		<a class="btn btn-warning" href="/post/post-create-view">글쓰기</a>
	</div>
		
	</div>
</div>