<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="sign-up-box">
		<h1 class="mb-4">회원가입</h1>
		<form id="signUpForm" method="post" action="/user/sign_up">
			<table class="sign-up-table table table-bordered">
				<tr>
					<th>* 아이디(4자 이상)<br></th>
					<td>
						<%-- 인풋박스 옆에 중복확인을 붙이기 위해 div를 하나 더 만들고 d-flex --%>
						<div class="d-flex">
							<input type="text" id="loginId" name="loginId" class="form-control" placeholder="아이디를 입력하세요.">
							<button type="button" id="loginIdCheckBtn" class="btn btn-success">중복확인</button><br>
						</div>
						
						<%-- 아이디 체크 결과 --%>
						<%-- d-none 클래스: display none (보이지 않게) --%>
						<div id="idCheckLength" class="small text-danger d-none">ID를 4자 이상 입력해주세요.</div>
						<div id="idCheckDuplicated" class="small text-danger d-none">이미 사용중인 ID입니다.</div>
						<div id="idCheckOk" class="small text-success d-none">사용 가능한 ID 입니다.</div>
					</td>
				</tr>
				<tr>
					<th>* 비밀번호</th>
					<td><input type="password" id="password" name="password" class="form-control" placeholder="비밀번호를 입력하세요."></td>
				</tr>
				<tr>
					<th>* 비밀번호 확인</th>
					<td><input type="password" id="confirmPassword" class="form-control" placeholder="비밀번호를 입력하세요."></td>
				</tr>
				<tr>
					<th>* 이름</th>
					<td><input type="text" id="name" name="name" class="form-control" placeholder="이름을 입력하세요."></td>
				</tr>
				<tr>
					<th>* 이메일</th>
					<td><input type="text" id="email" name="email" class="form-control" placeholder="이메일 주소를 입력하세요."></td>
				</tr>
			</table>
			<br>
		
			<button type="submit" id="signUpBtn" class="btn btn-primary float-right">회원가입</button>
		</form>
	</div>
</div>

<script>
$(document).ready(function() {
	// 아이디 중복확인
	$('#loginIdCheckBtn').on('click', function() {
		//초기화(경고 문구 안보이게 초기화)
/* 		idCheckLength
		idCheckDuplicated
		idCheckOk */
		
		$('#idCheckLength').addClass("d-none");
		$('#idCheckDuplicated').addClass("d-none");
		$('#idCheckOk').addClass("d-none");
		
		let loginId = $('input[name=loginId]').val().trim();
			 if(loginId.length < 4) {
				 $('#idCheckLength').removeClass('d-none');
				 
				 return;
			 }
			 
			 //ajax -  중복확인
			 $.ajax({
				url:"/user/is_duplicated_id"
				,data:{"loginId":loginId}
				
			 	,success:function(data) {
			 		if(data.result == true) {
			 			 $('#idCheckDuplicated').removeClass('d-none');
			 			return;
			 		} else if (data.result == false){
			 			 $('#idCheckOk').removeClass('d-none');
			 			
			 		} else {
			 			alert("중복 체크에 실패했습니다");
			 		}
			 	}
			 		,error: function(e) {
			 			alert("아이디 중복체크에 실패했습니다.");
			 		} 

				 
			 });
		 
			 
	});

 	//회원 가입 form, submit 버튼으로 작동, form보다는 ajax를 쓰자
 	$('#signUpForm').on('submit', function(e) {
 		e.preventDefault();// submit을 중단시킴
 		
 		// validation, ajax쓰면 안해도 됨
 		let loginId = $('#loginId').val().trim();
 		if(loginId == "" ) {
 			alert("아이디를 입력하세요.");
 			return false;
 		}
 		let password = $('#password').val(); //비밀번호는 전부 암호화 되기 때문에 trim은 없어도 된다
 		let confirmPassword = $('#confirmPassword').val();
 		if(password =="" || confirmPassword == "") {
 			alert("비밀번호를 입력하세요");
 			return false;
 		} 
 		if(password != confirmPassword) {
 			alert("비밀번호가 일치하지 않습니다")
 			$('#password').val("");
 			$('#confirmPassword').val(""); //텍스트 값을 초기화
 			return false;
 		}
 		
 		let name = $('#name').val().trim();
 		if(name =="") {
 			alert("이름을 입력하세요");
 			return false;
 		}
 		
 		let email = $('#email').val().trim();
 		if(email =="") {
 			alert("이메일을 입력하세요");
 			return false;
 		}
 		
 		//아이디 검증 되었는지 확인
 		//idCheckOk d-none이 있으면 성공이 아님
		if($('#idCheckOk').hasClass('d-none')) {
			alert("아이디 중복확인을 해주세요");
			return false;
		}
 		//submit
 		//e.preventDefault()로 멈춰놓은 서브밋을 동작시키기
 		/* $(this)[0].submit(); */
 		
 		
 		//2. ajax 
 		//form태그에 있는 action값을 가져옴	
 		let url = $(this).attr("action");
 		let params = $(this).serialize(); //form태그에 들어있는 name 속성 값들을 한번에 가져옴
 		//console.log(params);
 		
 		//ajax 다른 방식
 		$.post(url, params) //post로 저장한 url에 params를 보냄
 		.done(function(data) {
 			if(data.result =="success") {
 				alert("회원가입을 환영합니다. 로그인을 해주세요");
 				location.href = "/user/sign_in_view";
 			} else {
 				alert("회원가입에 실패했습니다. 다시 시도해주세요")
 			}
 		});
 		
 	});
		
});
</script>


