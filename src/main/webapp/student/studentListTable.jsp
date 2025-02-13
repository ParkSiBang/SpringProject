<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../jsp/header.jsp"%>
<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>순번</th>
			<th>학번</th>
			<th>로그인ID</th>
			<th>&nbsp;이름</th>
			<th>&nbsp;&nbsp;&nbsp;전화번호</th>
			<th>&nbsp;&nbsp;생년월일</th>
			<th>&nbsp;&nbsp;&nbsp;전공</th>
			<th>학년</th>
			<th>평균 학점</th>
			<th>석차</th>
			<th>&nbsp;&nbsp;삭제</th>
		</tr>
	</thead>
	<c:forEach items="${studentInfos}" var="studentInfo" varStatus="status">
		<tr>
			<td>&nbsp;&nbsp;${status.count}</td>
			<td>${studentInfo[0].student_id}</td>
			<td>${studentInfo[0].login_id}</td>
			<td>${studentInfo[0].name}</td>
			<td>${studentInfo[0].phone}</td>
			<td>${studentInfo[0].birth}</td>
			<td>${studentInfo[0].major}</td>
			<td>&nbsp;&nbsp;${studentInfo[0].grade_level}</td>
			<td><c:if test="${studentInfo[1] == 0}">
                    &nbsp;&nbsp;N/A
                </c:if> <c:if test="${studentInfo[1] != 0}">
                    &nbsp;&nbsp;${studentInfo[1]}
                </c:if></td>
			<td><c:if test="${studentInfo[2] == 0}">
                    N/A
                </c:if> <c:if test="${studentInfo[2] != 0}">
                    &nbsp;&nbsp;${studentInfo[2]}
                </c:if></td>
			<td>
				<button type="button" class="btn btn-danger"
					onclick="confirmDelete(${studentInfo[0].student_id})">삭제</button>
			</td>
		</tr>
	</c:forEach>
</table>

<script>
	function confirmDelete(student_id) {
		const isConfirmed = confirm("이 학생을 삭제하시겠습니까?");
		if (isConfirmed) {
			location.href=`${path}/adminPage/studentDelete.do?student_id=\${student_id}`;
		}
	};
</script>