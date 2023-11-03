<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">
	<div class="w-50">
		<h1>글 상세${post.id}</h1>
		<input type="text" class="form-control" id="subject" value="" placeholder="제목을 입력하세요" value="${post.subject}">
		<textarea id="content" class="form-control" rows="10" placeholder="내용을 입력하세요">${post.content}</textarea>
		
		<!--  이미지가 있으면 추가 -->
		<c:if test="${not empty post.imagePath}">
			<div class="my-4">
				<img src=""${post.imagePath} alt="업로드 된 이미지" width="300">
			</div>
		</c:if>
		<div class="d-flex justify-content-end my-4">
			<input type="file" id="file" value="파일 선택" accept=".jpg, .jpeg, .png, .gif">
		</div>
		
		<div class="d-flex justify-content-between">
			<button type="button" id="deleteBtn" class="btn btn-secondary">삭제</button>
			<div>
				<a href="/post/post-list-view" class="btn btn-dark">목록</a>
				<button type="button" id="saveBtn" class="btn btn-warning">저장</button>
			</div>
		</div>
	</div>
</div>

    