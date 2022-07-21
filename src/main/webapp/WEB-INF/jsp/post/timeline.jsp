<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="d-flex justify-content-center">
	<div class="contents-box">
		<!-- 글쓰기 영역 -->
		
		<div class="write-box boreder rounded m-3">
			<textarea id="writeTextArea" placeholder="내용을 입력해주세요" class="w-100 border" cols="50" rows="15"></textarea>
			
			<div class="d-flex justify-content-between">
				<!-- 이미지 업로드를 위한 곳 -->
				<div class="d-flex">
					<input type="file" id="file" accept=".gif,.jpg,.png,.jpeg" class="d-none">
					<a href="#" id="fileUploadBtn"><img src="https://cdn.icon-icons.com/icons2/510/PNG/32/image_icon-icons.com_50366.png" alt="파일선택"></a>
					<%-- 업로드 된 임시 파일명 보이는 곳 --%>
					<div id="fileName" class="ml-2">
					
						
					</div>
				</div>
				<button type="button" class="btn btn-primary">게시</button>
			</div>
		</div>
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
		$('#file').on('change', function(e) {
			let fileName = e.target.files[0].name; //파일 이름
			let arr = fileName.split(".");
			
			//확장자 검증
			if(arr.length < 2 || 
					(arr[arr.length - 1] != 'gif' && // arr[arr.length - 1]이 구문이 확장자 명이다
							arr[arr.length - 1] != 'jpg' &&
							arr[arr.length - 1] != 'jpeg' &&
							arr[arr.length - 1] != 'png')) { //2보다 작으면 뭔가가 잘못 된 것이다.
				alert("이미지만 업로드 할 수 있습니다")
				$(this).val(""); //파일을 비운다, 비우지 않으면 서버에 올라가 버린다
				$('#fileName').text(""); //파일 이름도 비워줌
				return; //이 과정이 필수이다.
			
			}
			
			
			// 임시 파일명 노출
			$('#fileName').text(fileName);
			
		
		});
		
		
		
	});

</script>