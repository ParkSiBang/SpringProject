package controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.StudentService;

@WebServlet("/adminPage/studentDelete.do")
public class StudentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	StudentService studentService = new StudentService();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 학생 삭제
		String str_student_id = request.getParameter("student_id");
		int student_id = 0;
		if (str_student_id != null) {
			student_id = Integer.parseInt(str_student_id);
		}

		int result = studentService.deleteStudentService(student_id);

		String studentMessage = result > 0 ? "삭제가 완료되었습니다." : "삭제가 실패했습니다.";
		studentMessage = URLEncoder.encode(studentMessage, "utf-8");
		response.sendRedirect("adminPage.do?studentMessage=" + studentMessage);
	}
}
