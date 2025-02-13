<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../jsp/header.jsp"%>
<div class="container mt-5">
	<div class="mb-4">
		<button type="button" class="btn btn-success" data-bs-toggle="modal"
			data-bs-target="#gradeInsertModal">성적 추가</button>
	</div>
	<div class="modal fade" id="gradeInsertModal" role="dialog"
		data-bs-backdrop="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">성적 추가</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body">
					<form action="gradeInsert.do" method="post">
						<div class="input-group mb-3">
							<span class="input-group-text">학번</span> <input type="number"
								required="required" class="form-control" name="student_id">
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text">과목ID</span><input type="number"
								required="required" class="form-control" name="subject_id">
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text">성적</span> <select
								required="required" class="form-control" name="score">
								<option value="A+">A+</option>
								<option value="A">A</option>
								<option value="B+">B+</option>
								<option value="B">B</option>
								<option value="C+">C+</option>
								<option value="C">C</option>
								<option value="D">D</option>
								<option value="F">F</option>
							</select>
						</div>
						<div class="d-flex justify-content-center">
							<button type="submit" class="btn btn-success">성적 추가</button>
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

<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>순번</th>
			<th>성적ID</th>
			<th>&nbsp;&nbsp;이름</th>
			<th>&nbsp;&nbsp;과목명</th>
			<th>담당교수</th>
			<th>성적</th>
			<th>&nbsp;&nbsp;수정</th>
			<th>&nbsp;&nbsp;삭제</th>
		</tr>
	</thead>
	<c:forEach items="${grades}" var="grade" varStatus="status">
		<tr>
			<td>&nbsp;&nbsp;${status.count}
			<td>&nbsp;&nbsp;${grade.grade_id}</td>
			<td>${grade.name}</td>
			<td>${grade.subject_name}</td>
			<td>${grade.professor}</td>
			<td>&nbsp;&nbsp;${grade.score}</td>
			<td>
				<button type="button" class="btn btn-info" data-bs-toggle="modal"
					data-bs-target="#gradeUpdateModal" data-gradeid="${grade.grade_id}"
					data-name="${grade.name}" data-subjectname="${grade.subject_name}"
					data-professor="${grade.professor}" data-score="${grade.score}">수정</button>
			</td>
			<td>
				<button type="button" class="btn btn-danger"
					onclick="confirmDelete(${grade.grade_id})">삭제</button>
			</td>
		</tr>
	</c:forEach>
</table>

<div class="modal fade" id="gradeUpdateModal" role="dialog"
	data-bs-backdrop="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">성적 정보 수정</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			</div>
			<div class="modal-body">
				<form action="gradeUpdate.do" method="post">
					<div class="input-group mb-3">
						<span class="input-group-text">성적ID</span> <input type="number"
							value="${gradeInfo.grade_id}" readonly="readonly"
							class="form-control" name="grade_id">
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">이름</span> <input type="text"
							value="${gradeInfo.name}" required="required" readonly="readonly"
							class="form-control" name="name">
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">과목명</span> <input type="text"
							value="${gradeInfo.subject_name}" required="required"
							readonly="readonly" class="form-control" name="subject_name">
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">담당교수</span> <input type="text"
							value="${gradeInfo.professor}" required="required"
							readonly="readonly" class="form-control" name="professor">
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">성적</span> <select
							required="required" class="form-control" name="score">
							<option value="A+" ${gradeInfo.score == "A+" ? 'selected' : ''}>A+</option>
							<option value="A" ${gradeInfo.score == "A" ? 'selected' : ''}>A</option>
							<option value="B+" ${gradeInfo.score == "B+" ? 'selected' : ''}>B+</option>
							<option value="B" ${gradeInfo.score == "B" ? 'selected' : ''}>B</option>
							<option value="C+" ${gradeInfo.score == "C+" ? 'selected' : ''}>C+</option>
							<option value="C" ${gradeInfo.score == "C" ? 'selected' : ''}>C</option>
							<option value="D" ${gradeInfo.score == "D" ? 'selected' : ''}>D</option>
							<option value="F" ${gradeInfo.score == "F" ? 'selected' : ''}>F</option>
						</select>
					</div>
					<div class="d-flex justify-content-center">
						<button type="submit" class="btn btn-success">성적 정보 수정</button>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-bs-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<script>
	$(function() {
		$("#gradeUpdateModal").on("show.bs.modal", function(e) {
			var grade_id = $(e.relatedTarget).data('gradeid');
			var name = $(e.relatedTarget).data('name');
			var subject_name = $(e.relatedTarget).data('subjectname');
			var professor = $(e.relatedTarget).data('professor');
			var score = $(e.relatedTarget).data('score');
			$("#gradeUpdateModal input[name='grade_id']").val(grade_id);
			$("#gradeUpdateModal input[name='name']").val(name);
			$("#gradeUpdateModal input[name='subject_name']").val(subject_name);
			$("#gradeUpdateModal input[name='professor']").val(professor);
			$("#gradeUpdateModal select[name='score']").val(score);
		});
	});

	function confirmDelete(grade_id) {
		const isConfirmed = confirm("이 과목을 삭제하시겠습니까?");
		if (isConfirmed) {
			location.href=`${path}/adminPage/gradeDelete.do?grade_id=\${grade_id}`;
		}
	};
</script>