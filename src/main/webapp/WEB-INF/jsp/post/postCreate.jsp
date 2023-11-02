<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="w-50">
		<h1>글 상세</h1>
		<input type="text" class="form-control" id="subject" value="" placeholder="제목을 입력하세요">
		<textarea id="content" class="form-control" rows="10" placeholder="내용을 입력하세요"></textarea>
		<div class="d-flex justify-content-end my-4">
			<input type="file" id="file" value="파일 선택" accept=".jpg, .jpeg, .png, .gif">
		</div>
		
		<div class="d-flex justify-content-between">
			<button type="button" id="postListBtn" class="btn btn-dark">목록</button>
			<div>
				<button type="button" id="clearBtn" class="btn btn-secondary">모두 지우기</button>
				<button type="button" id="saveBtn" class="btn btn-warning">저장</button>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$("#postListBtn").on('click', function() {
			// 목록 버튼 클릭
			alert('클릭');
			location.href = "/post/post-list-view";
		});
		
		$('#clearBtn').on('click', function() {
			alert("클리어");
			$('#subject').val("");
			$("#content").val("");
			
		})
		
		// 글 저장 버튼
		$('#saveBtn').on('click', function() {
			//alert("저장");
			let subject = $('#subject').val().trim();
			let content = $('#content').val().trim();
			let fileName = $("#file").val();
			// alert(file); // C:\fakepath\스크린샷(2).png
			// validation check
			
			if(!subject) {
				alert("제목을 입력하세요.");
				return;
			}
			if(!content) {
				alert("내용을 입력하세요.");
				return;
			}
			
			// 파일이 업로드 된 경우에만 확장자 체크
			if(fileName) {
				//alert("파일이 있다.");
				// C:\fakepath\스크린샷(2).png
				// 확장자만 뽑은 후 소문자로 변경한다.
				let ext = fileName.split(".").pop().toLowerCase(); // 마지막 것을 뽑아내는 것
				//alert(ext);
				if ($.inArray(ext, ['jpg', 'jpeg','png','gif']) == -1) { // 배열에 ext 가 있는가
					alert("이미지 파일만 업로드 할 수 있습니다.");
					$('#file').val(""); // 파일을 비운다.	
					return;
				}
			
			}
			
			
			
			
			// request param 구성
			// javascript 에서 form 을 임의로 만들어 전송
			// 이미지를 업로드 할 때는 반드시 form 태그가 있어야 한다.
			let formData = new FormData();
			formData.append("subject", subject); // key는 form 태그의 name 속성과 같고 Request parameter 명이 된다.
			formData.append("content", content);
			// 서버에 보내기 : 이미지
			formData.append("file", $('#file')[0].files[0]); // 여러 개 이미지 올리는 것은 검색해야 한다.
			
			
			
			$.ajax({
				//request
				type:"post" // insert 요청이므로
				, url:"/post/create"
				, data : formData
				, enctype : "multipart/form-data" // 파일 업로드를 위한 필수 설정
				, processData:false  // 파일 업로드를 위한 필수 설정
				, contentType: false  // 파일 업로드를 위한 필수 설정
			
				
				
				//response
				, success : function(data) {
					if(data.result == "성공") {
						alert("메모가 저장되었습니다.");
						location.href="/post/post-list-view";
					} else {
						// 로직 실패
						alert(data.errorMessage);
						
					}
				}
				, error : function(request, status, error) {
					alert("글을 저장하는데 실패했습니다.");
				}
				
			})
			
			
		})
		
	})
</script>