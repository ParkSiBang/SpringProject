<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../jsp/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<style>
.container {
    max-width: 900px;
	margin: 0 auto;
	padding-top: 2rem;
	text-align: center;
	background: #ffffff;
	box-shadow: 0px 6px 15px rgba(0, 0, 0, 0.2);
	border-radius: 8px;
	padding: 2rem;
}
</style>
</head>
<body>
	<div class="container mt-5">
		<h2 class="text-center">관리자 페이지</h2>
		<button type="button" class="btn btn-primary" id="btn_student">학생
			관리</button>
		<button type="button" class="btn btn-primary" id="btn_subject">과목
			관리</button>
		<button type="button" class="btn btn-primary" id="btn_grade">성적
			관리</button>
		<button type="button" class="btn btn-outline-primary"
			onclick="logoutConfirm()">로그아웃</button>
		<div id="list"></div>
	</div>

	<script>
		$(function() {
			$("#btn_student").on("click", studentList_ajax);
			$("#btn_subject").on("click", subjectList_ajax);
			$("#btn_grade").on("click", gradeList_ajax);

			var studentMessage = "${param.studentMessage}";
			if (studentMessage != "") {
				alert(studentMessage);
				var url = window.location.href.split('?')[0];
				window.history.replaceState({}, '', url);
				$("#btn_student").click();
			}

			var subjectMessage = "${param.subjectMessage}";
			if (subjectMessage != "") {
				alert(subjectMessage);
				var url = window.location.href.split('?')[0];
				window.history.replaceState({}, '', url);
				$("#btn_subject").click();
			}

			var gradeMessage = "${param.gradeMessage}";
			if (gradeMessage != "") {
				alert(gradeMessage);
				var url = window.location.href.split('?')[0];
				window.history.replaceState({}, '', url);
				$("#btn_grade").click();
			}

		});

		function studentList_ajax() {
			$.ajax({
				url : "${path}/student/studentList.do",
				type : "get",
				data : {
					"student_id" : $('[name="student_id"]').val(),
					"login_id" : $('[name="login_id"]').val(),
					"name" : $('[name="name"]').val(),
					"phone" : $('[name="phone"]').val(),
					"birth" : $('[name="birth"]').val(),
					"major" : $('[name="major"]').val(),
					"grade_level" : $('[name="grade_level"]').val()
				},
				success : function(responseData) {
					$("#list").html(responseData);
				},
				error : function(err) {
					alert(err);
				}
			});
		}

		function subjectList_ajax() {
			$.ajax({
				url : "${path}/subject/subjectList.do",
				type : "get",
				data : {
					"subject_id" : $('[name="subject_id"]').val(),
					"subject_name" : $('[name="subject_name"]').val(),
					"professor" : $('[name="professor"]').val()
				},
				success : function(responseData) {
					$("#list").html(responseData);
				},
				error : function(err) {
					alert(err);
				}
			});
		}

		function gradeList_ajax() {
			$.ajax({
				url : "${path}/grade/gradeList.do",
				type : "get",
				data : {
					"grade_id" : $('[name="grade_id"]').val(),
					"name" : $('[name="name"]').val(),
					"subject_name" : $('[name="subject_name"]').val(),
					"professor" : $('[name="professor"]').val(),
					"score" : $('[name="score"]').val()
				},
				success : function(responseData) {
					$("#list").html(responseData);
				},
				error : function(err) {
					alert(err);
				}
			});
		}

		function logoutConfirm() {
			const isConfirmed = confirm("[관리자] 로그아웃 하시겠습니까?");
			if (isConfirmed) {
				location.href = `${path}/login/logout.do`;
			}
		};
	</script>
</body>
</html>