<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../jsp/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>성적 관리 시스템</title>
<style>
.login-box {
	max-width: 400px;
	background-color: white;
	border-radius: 12px;
	box-shadow: 0px 6px 15px rgba(0, 0, 0, 0.2);
}
</style>
</head>
<body>
	<div
		class="container mt-4 d-flex align-items-center justify-content-center"
		style="height: 70vh;">
		<div class="login-box p-5 shadow rounded"
			style="width: 100%; max-width: 500px;">
			<h2 class="text-center mb-4">성적 관리 시스템</h2>
			<form action="login.do" method="post">
				<div class="mb-3">
					<label class="form-label">아이디</label> <input type="text"
						class="form-control" name="login_id" required>
				</div>
				<div class="mb-3">
					<label class="form-label">비밀번호</label> <input type="password"
						class="form-control" name="login_pw" required>
				</div>
				<div class="d-flex justify-content-between">
					<button type="submit" id="btn_login" class="btn btn-primary w-50">로그인</button>
					<button type="button" class="btn btn-outline-primary w-50 ms-2"
						data-bs-toggle="modal" data-bs-target="#signupModal">회원가입</button>
				</div>
			</form>
		</div>
	</div>

	<div class="modal fade" id="signupModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">회원가입</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body">
					<form id="signupForm" action="signup.do" method="post">
						<div class="input-group mb-3">
							<span class="input-group-text">아이디</span> <input type="text"
								class="form-control" id="login_id" name="login_id"
								placeholder="아이디를 입력하세요" required="required"> <input
								id="dupCheck" type="button" class="btn btn-outline-primary"
								required="required" style="border-radius: 0 0.25rem 0.25rem 0;"
								value="중복 검사" />
						</div>
						<input type="hidden" id="dupCheckStatus" value="false">
						<div class="input-group mb-3">
							<span class="input-group-text">비밀번호</span> <input type="text"
								required="required" class="form-control" name="login_pw">
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text">이름</span> <input type="text"
								required="required" class="form-control" name="name">
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text">전화번호</span> <input type="tel"
								class="form-control" name="phone" placeholder="숫자만 입력하세요"
								required="required" maxlength="13"
								pattern="^(01[0-9])-(\d{3,4})-(\d{4})$"
								oninput="this.value = this.value.replace(/[^0-9-]/g, '').replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');">
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text">생년월일</span> <input type="date"
								required="required" class="form-control" name="birth">
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text">전공</span> <input type="text"
								required="required" class="form-control" name="major">
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text">학년</span> <select
								required="required" class="form-control" name="grade_level">
								<option value="1">1학년</option>
								<option value="2">2학년</option>
								<option value="3">3학년</option>
								<option value="4">4학년</option>
							</select>
						</div>
						<div class="d-flex justify-content-center">
							<button type="submit" class="btn btn-success">회원가입</button>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>

	<script>
		$(function() {
			var loginFailMessage = "${param.loginFailMessage}";
			if (loginFailMessage != "") {
				alert(loginFailMessage);
				var url = window.location.href.split('?')[0];
				window.history.replaceState({}, '', url);
			}

			var signupMessage = "${param.signupMessage}";
			if (signupMessage != "") {
				alert(signupMessage);
				var url = window.location.href.split('?')[0];
				window.history.replaceState({}, '', url);
			}
		});

		$('#signupModal').on('hidden.bs.modal', function() {
			$(this).find('form')[0].reset();
			$('#dupCheckResult').text('');
			$('#dupCheckStatus').val("false");
		});

		document.getElementById("dupCheck").onclick = function(event) {
			const login_Id = document.getElementById("login_id").value.trim();

			if (!login_Id) {
				if (!login_Id) {
					alert("아이디를 입력해주세요.");
					document.getElementById("login_id").focus();
					return;
				}
				return;
			}

			$("#dupCheckStatus").val("false");

			$.ajax({
				url : "${path}/login/signup.do",
				data : {
					login_id : login_Id
				},
				success : function(responsData) {
					if (responsData === "이미 사용중인 아이디입니다") {
						alert(responsData);
						document.getElementById("login_id").focus();
					} else {
						alert(responsData);
						$("#dupCheckStatus").val("true");
					}

					$("#dupCheckStatus").val("true");
				},
				error : function() {
				}
			});

		};

		document.getElementById('signupForm').onsubmit = function(event) {
			event.preventDefault();
			const dupCheckStatus = document.getElementById("dupCheckStatus").value;

			if (dupCheckStatus !== "true") {
				alert('아이디 중복 검사를 완료해주세요.');
				return;
			}

			var data = $("#signupForm").serialize();
			$.ajax({
				url : "${path}/login/signup.do?",
				type : "post",
				data : data,
				success : function(responsData) {
					var result = responsData;
					if (result > 0) {
						alert("회원가입이 완료되었습니다.");
						location.reload();
					} else {
						alert("회원가입에 실패했습니다.");
					}

					$("#dupCheckStatus").val("false");
					$("#signupModal").hide();
				},
				error : function() {
					alert("error: 회원가입에 실패했습니다.");
				}
			});

		};
	</script>
</body>
</html>