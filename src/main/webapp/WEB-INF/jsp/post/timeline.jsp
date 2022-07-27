<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="d-flex justify-content-center">
	<div class="contents-box">
		<!-- 글쓰기 영역 -->

		<div class="write-box border rounded m-3">
			<textarea id="writeTextArea" placeholder="내용을 입력해주세요"
				class="w-100 border-0" cols="60" rows="10"></textarea>

			<div class="d-flex justify-content-between">
				<!-- 이미지 업로드를 위한 곳 -->
				<div class="d-flex">
					<input type="file" id="file" accept=".gif,.jpg,.png,.jpeg"
						class="d-none"> <a href="#" id="fileUploadBtn"><img
						src="https://cdn.icon-icons.com/icons2/510/PNG/32/image_icon-icons.com_50366.png"
						alt="파일선택"></a>
					<%-- 업로드 된 임시 파일명 보이는 곳 --%>
					<div id="fileName" class="ml-2"></div>
				</div>
				<button type="button" class="btn btn-primary" id="createBtn">게시</button>
			</div>
		</div>

		<c:forEach var="card" items="${cardViewList}" varStatus="status">
			<%-- 타임라인 영역 --%>
			<div class="timeline-box my-5">
				<%-- 카드 마다 영역을 border로 나눔 --%>
				<div class="card border rounded mt-3">
					<%-- 글쓴이 아이디, 삭제를 위한 ...버튼 : 이 둘을 한 행에 멀리 떨어뜨려 나타내기 위해 d-flex, between --%>
					<div class="p-2 d-flex justify-content-between">
						<span class="font-weight-bold">${card.user.name}</span>

						<%-- 삭제 모달을 뛰우기 위한 ... 버튼 --%>
						<a href="#" class="more-btn"> <img
							src="https://www.iconninja.com/files/860/824/939/more-icon.png"
							width="30">
						</a>
					</div>

					<%-- 카드 이미지 --%>
					<div class="card-img">
						<%-- 카드의 크기는 css로 따로 지정해야한다 --%>
						<img src="${card.post.imagePath}" class="imgbox" alt="이미지">
					</div>

					<%-- 좋아요 --%>
					<div class="card-like m-3">
						<a href="#" class="like-btn"> <img
							src="https://www.iconninja.com/files/214/518/441/heart-icon.png"
							width="18px" height="18px" alt="empty heart"> 좋아요 11개
						</a>
					</div>

					<%-- 글(post) --%>
					<div class="card-post m-3">
						<span class="font-weight-bold">${card.user.name}</span> <span>${card.post.content}</span>
					</div>

					<%-- 댓글(comment) --%>
					<div class="card-comment-desc border-bottom">
						<div class="ml-3 mb-1 font-weight-bold">댓글</div>
					</div>
					<div class="card-comment-list m-2">
					<c:forEach car="content" items="${card.commentList}">
						<%-- 댓글 목록 --%>
						<div class="card-comment m-1">
							<span class="font-weight-bold">${comment.user.name}: </span> 
							<span>${comment.comment.content}</span>

							<%-- 댓글 삭제 --%>
							<a href="#" class="commentDelBtn"
								data-comment-id=""> <img
								src="https://www.iconninja.com/files/603/22/506/x-icon.png"
								width="10px" height="10px">
							</a>
						</div>
						<%-- 댓글 쓰기 --%>
						<c:if test="${not empty userId}">
						<div class="comment-writebox d-flex">
							<input type="text" class="form-control">
						<button type="button" class="comment-btn btn btn-primary" data-post-id="${card.post.id}">게시</button>
					</div>
					</c:if>
					</c:forEach>
					
					</div>
				</div>

			</div>
		</c:forEach>



	</div>
</div>

<script>
$(document).ready(function() {
	//파일 업로드 이미지 클릭 => input type="file" 숨어있던 창이 열림
	$('#fileUploadBtn').on('click', function(e) {
		e.preventDefault(); //화면이 위로 올라가는 현상을 방지, submit을 막는 등 여러가지를 막을 수 있다
		$('#file').click(); //input file을 click한것과 같은 효과
		//즉 이미지 버튼을 누르면 input파일을 click한 효과가 작동하여 실제로는 input이 작동한다
	});
	//파일 업로드를 했을 때, 확장자 이름 노출, 파일 확장자 검증
	$('#file').on('change',function(e) {
						let fileName = e.target.files[0].name; //파일 이름
						let arr = fileName.split(".");
						//확장자 검증
						if (arr.length < 2
								|| (arr[arr.length - 1].toLowerCase() != 'gif'
										&& // arr[arr.length - 1]이 구문이 확장자 명이다
										arr[arr.length - 1].toLowerCase() != 'jpg'
										&& arr[arr.length - 1].toLowerCase() != 'jpeg' && arr[arr.length - 1].toLowerCase() != 'png')) { //2보다 작으면 뭔가가 잘못 된 것이다.
							alert("이미지만 업로드 할 수 있습니다")
							$(this).val(""); //파일을 비운다, 비우지 않으면 서버에 올라가 버린다
							$('#fileName').text(""); //파일 이름도 비워줌
							return; //이 과정이 필수이다.
						}
						// 임시 파일명 노출
						$('#fileName').text(fileName);
					});
	$('#createBtn').on('click', function() {
						// validation 
						let content = $('#writeTextArea')
								.val();
						console.log(content);
						if (content.length < 1) {
							alert("글 내용을 입력해주세요");
						}
						// 파일이 업로드 된 경우 확장자 체크
						let file = $('#file').val(); // 파일 경로만 가져온다.
						console.log(file); // C:\fakepath\image.png
						if (file != '') {
							let ext = file.split('.').pop()
									.toLowerCase(); // 파일 경로를 .으로 나누고 확장자가 있는 마지막 문자열을 가져온 후 모두 소문자로 변경
							if ($.inArray(ext, [ 'gif',
									'png', 'jpg', 'jpeg' ]) == -1) {
								alert("gif, png, jpg, jpeg 파일만 업로드 할 수 있습니다.");
								$('#file').val(''); // 파일을 비운다.
								return;
							}
						}
						// 폼태그를 자바스크립트에서 만든다.
						let formData = new FormData();
						formData.append("content", content);
						formData.append("file",
								$('#file')[0].files[0]); // $('#file')[0]은 첫번째 input file 태그를 의미, files[0]는 업로드된 첫번째 파일
						// AJAX form 데이터 전송
						$.ajax({
									type : "post",
									url : "/post/create",
									data : formData,
									enctype : "multipart/form-data" // 파일 업로드를 위한 필수 설정
									,
									processData : false // 파일 업로드를 위한 필수 설정
									,
									contentType : false // 파일 업로드를 위한 필수 설정
									,
									success : function(data) {
										if (data.result == "success") {
											location.reload();
										} else {
											alert(data.errorMessage);
										}
									},
									error : function(e) {
										alert("게시글 업로드에 실패했습니다. 관리자에게 문의해주세요.");
									}
								});
					});
	$('.comment-btn').on('click', function() {
		let postId = $(this).data('post-id'); //data-post-id
		
		let comment = $(this).siblings("input").val().trim(); //형제 태그를 가져옴
		//즉 게시 버튼을 누르면 누른 버튼의 text 버튼의 값을 가져와준다
	
		if(comment == "" ){
			alert("내용을 입력해주세요");
			return;
		}
		
		$.ajax({
			type: "post"
			,url: "/comment/create"
			,data: {"postId" : postId, "comment" : comment} 
			, success: function(data) {
				if (data.result2 == "success") {
					location.reload(true);
				} else {
					alert(data.errorMessage);
				}	
			
			}
			,error: function(e) {
				alert("error" + e);
			}
		
		});
		
		
		
	});
	
	
	
});
</script>