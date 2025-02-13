<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../jsp/header.jsp"%>
<div class="container mt-5">
	<div class="mb-4">
		<button type="button" class="btn btn-success" data-bs-toggle="modal"
			data-bs-target="#subjectInsertModal">과목 추가</button>
	</div>
	<div class="modal fade" id="subjectInsertModal" role="dialog"
		data-bs-backdrop="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">과목 추가</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body">
					<form action="subjectInsert.do" method="post">
						<div class="input-group mb-3">
							<span class="input-group-text">과목명</span> <input type="text"
								required="required" class="form-control" name="subject_name">
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text">담당교수</span> <input type="text"
								required="required" class="form-control" name="professor">
						</div>
						<div class="d-flex justify-content-center">
							<button type="submit" class="btn btn-success">과목 추가</button>
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
			<th>과목ID</th>
			<th>&nbsp;&nbsp;&nbsp;과목명</th>
			<th>담당교수</th>
			<th>&nbsp;&nbsp;수정</th>
			<th>&nbsp;&nbsp;삭제</th>
		</tr>
	</thead>
	<c:forEach items="${subjects}" var="subject" varStatus="status">
		<tr>
			<td>&nbsp;&nbsp;${status.count}</td>
			<td>&nbsp;&nbsp;${subject.subject_id}</td>
			<td>${subject.subject_name}</td>
			<td>${subject.professor}</td>
			<td>
				<button type="button" class="btn btn-info" data-bs-toggle="modal"
					data-bs-target="#subjectUpdateModal"
					data-subjectid="${subject.subject_id}"
					data-subjectname="${subject.subject_name}"
					data-professor="${subject.professor}">수정</button>
			</td>
			<td>
				<button type="button" class="btn btn-danger"
					onclick="confirmDelete(${subject.subject_id})">삭제</button>
			</td>
		</tr>
	</c:forEach>
</table>

<div class="modal fade" id="subjectUpdateModal" role="dialog"
	data-bs-backdrop="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">과목 정보 수정</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			</div>
			<div class="modal-body">
				<form action="subjectUpdate.do" method="post">
					<div class="input-group mb-3">
						<span class="input-group-text">과목ID</span> <input type="number"
							value="${subjectInfo.subject_id}" readonly="readonly"
							class="form-control" name="subject_id">
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">과목명</span> <input type="text"
							value="${subjectInfo.subject_name}" required="required"
							class="form-control" name="subject_name">
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">담당교수</span> <input type="text"
							value="${subjectInfo.professor}" required="required"
							class="form-control" name="professor">
					</div>
					<div class="d-flex justify-content-center">
						<button type="submit" class="btn btn-success">과목 정보 수정</button>
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
		$("#subjectUpdateModal").on("show.bs.modal", function(e) {
			var subject_id = $(e.relatedTarget).data('subjectid');
			var subject_name = $(e.relatedTarget).data('subjectname');
			var professor = $(e.relatedTarget).data('professor');
			$("#subjectUpdateModal input[name='subject_id']").val(subject_id);
			$("#subjectUpdateModal input[name='subject_name']").val(subject_name);
			$("#subjectUpdateModal input[name='professor']").val(professor);
		});
	});

	function confirmDelete(subject_id) {
		const isConfirmed = confirm("이 과목을 삭제하시겠습니까?");
		if (isConfirmed) {
			location.href=`${path}/adminPage/subjectDelete.do?subject_id=\${subject_id}`;
		}
	};
</script>