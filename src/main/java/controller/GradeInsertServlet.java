package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.GradeDTO;
import dto.StudentDTO;
import dto.SubjectDTO;
import service.GradeService;
import service.StudentService;
import service.SubjectService;

@WebServlet("/adminPage/gradeInsert.do")
public class GradeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	GradeService gradeService = new GradeService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 성적 추가
		request.setCharacterEncoding("utf-8");
		String score = request.getParameter("score");
		int student_id = Integer.parseInt(request.getParameter("student_id"));
		int subject_id = Integer.parseInt(request.getParameter("subject_id"));

		GradeDTO grade = GradeDTO.builder().score(score).student_id(student_id).subject_id(subject_id).build();
		int result = gradeService.insertGradeService(grade);

		String gradeMessage = result > 0 ? "추가가 완료되었습니다." : "추가에 실패했습니다.";
		gradeMessage = URLEncoder.encode(gradeMessage, "utf-8");
		response.sendRedirect("adminPage.do?gradeMessage=" + gradeMessage);
	}

}
