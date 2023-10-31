<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table class="table">
	<thead>
		<tr>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${postList}" var="post">
			<tr>
			<td>${post.id}</td>
			<td>${post.subject}</td>
			<td>${post.createdAt}</td>
			<td>${post.updatedAt}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>