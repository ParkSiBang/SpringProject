<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../jsp/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>${studentName}님&nbsp;마이페이지</title>
<style>
.container {
	max-width: 900px;
	margin: 0 auto;
	padding-top: 2rem;
	background: #ffffff;
	box-shadow: 0px 6px 15px rgba(0, 0, 0, 0.2);
	border-radius: 8px;
	padding: 2rem;
}
</style>
</head>
<body>
	<div class="container mt-5">
		<h2 class="text-center">${studentName}님&nbsp;마이페이지</h2>
		<button type="button" class="btn btn-primary" data-bs-toggle="modal"
			data-bs-target="#gradeInfoModal">성적 조회</button>
		<button type="button" class="btn btn-primary" data-bs-toggle="modal"
			data-bs-target="#gradeStatsModal">성적 통계</button>
		<button type="button" class="btn btn-primary" data-bs-toggle="modal"
			data-bs-target="#studentInfoModal">학생 정보 조회</button>
		<button type="button" class="btn btn-primary" data-bs-toggle="modal"
			data-bs-target="#studentUpdateModal">학생 정보 수정</button>
		<button type="button" class="btn btn-outline-primary"
			onclick="logoutConfirm()">로그아웃</button>

		<div class="modal fade" id="gradeInfoModal" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">성적 조회</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>&nbsp;과목명</th>
									<th>담당교수</th>
									<th>성적</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${studentGradeDetails}"
									var="studentGradeDetail">
									<tr>
										<td>${studentGradeDetail.subject_name}</td>
										<td>${studentGradeDetail.professor}</td>
										<td>&nbsp;&nbsp;${studentGradeDetail.score}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-danger"
							data-bs-dismiss="modal">닫기</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="gradeStatsModal" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">성적 통계</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>평균 학점</th>
									<th>평균 등급</th>
									<th>석차</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>&nbsp;&nbsp;&nbsp;${gradeStatsList[0]}</td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;${gradeStatsList[1]}</td>
									<td>&nbsp;&nbsp;${gradeStatsList[2]}</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-danger"
							data-bs-dismiss="modal">닫기</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="studentInfoModal" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">학생 정보 조회</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>
					<div class="modal-body">
						<p>
							<strong>학번: ${studentInfo.student_id}</strong>
						</p>
						<p>
							<strong>이름: ${studentInfo.name}</strong>
						</p>
						<p>
							<strong>전화번호: ${studentInfo.phone}</strong>
						</p>
						<p>
							<strong>생년월일: ${studentInfo.birth}</strong>
						</p>
						<p>
							<strong>전공: ${studentInfo.major}</strong>
						</p>
						<p>
							<strong>학년: ${studentInfo.grade_level}학년</strong>
						</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-danger"
							data-bs-dismiss="modal">닫기</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="studentUpdateModal" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">학생 정보 수정</h4>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>
					<div class="modal-body">
						<form action="studentUpdate.do" method="post">
							<div class="input-group mb-3">
								<span class="input-group-text">학번</span> <input type="number"
									value="${studentInfo.student_id}" readonly="readonly"
									class="form-control" name="student_id">
							</div>
							<div class="input-group mb-3">
								<span class="input-group-text">로그인ID</span> <input type="text"
									value="${studentInfo.login_id}" required="required"
									class="form-control" name="login_id">
							</div>
							<div class="input-group mb-3">
								<span class="input-group-text">로그인PW</span> <input type="text"
									value="${studentInfo.login_pw}" required="required"
									class="form-control" name="login_pw">
							</div>
							<div class="input-group mb-3">
								<span class="input-group-text">이름</span> <input type="text"
									value="${studentInfo.name}" required="required"
									class="form-control" name="name">
							</div>
							<div class="input-group mb-3">
								<span class="input-group-text">전화번호</span> <input type="text"
									value="${studentInfo.phone}" required="required"
									class="form-control" name="phone">
							</div>
							<div class="input-group mb-3">
								<span class="input-group-text">생년월일</span> <input type="Date"
									value="${studentInfo.birth}" required="required"
									class="form-control" name="birth">
							</div>
							<div class="input-group mb-3">
								<span class="input-group-text">전공</span> <input type="text"
									value="${studentInfo.major}" required="required"
									class="form-control" name="major">
							</div>
							<div class="input-group mb-3">
								<span class="input-group-text">학년</span> <select
									required="required" class="form-control" name="grade_level">
									<option value="1"
										${studentInfo.grade_level == 1 ? 'selected' : ''}>1학년</option>
									<option value="2"
										${studentInfo.grade_level == 2 ? 'selected' : ''}>2학년</option>
									<option value="3"
										${studentInfo.grade_level == 3 ? 'selected' : ''}>3학년</option>
									<option value="4"
										${studentInfo.grade_level == 4 ? 'selected' : ''}>4학년</option>
								</select>
							</div>
							<div class="d-flex justify-content-center">
								<button type="submit" class="btn btn-success">학생 정보 수정</button>
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
	</div>

	<script>
		$(function() {
			var studentMessage = "${param.studentMessage}";
			if (studentMessage != "") {
				alert(studentMessage);
				var url = window.location.href.split('?')[0];
				window.history.replaceState({}, '', url);
			}
		});

		function logoutConfirm() {
			const isConfirmed = confirm("로그아웃 하시겠습니까?");
			if (isConfirmed) {
				location.href = `${path}/login/logout.do`;
			}
		};
	</script>
</body>
</html>