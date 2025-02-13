package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.StudentDTO;
import service.StudentService;
import util.DateUtil;

@WebServlet("/studentPage/studentUpdate.do")
public class StudentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	StudentService studentService = new StudentService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 학생 수정
		request.setCharacterEncoding("utf-8");
		int student_id = Integer.parseInt(request.getParameter("student_id"));
		String login_id = request.getParameter("login_id");
		String login_pw = request.getParameter("login_pw");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		Date birth = DateUtil.convertSqlDate(DateUtil.convertDate(request.getParameter("birth")));
		String major = request.getParameter("major");
		int grade_level = Integer.parseInt(request.getParameter("grade_level"));

		StudentDTO student = StudentDTO.builder().student_id(student_id).login_id(login_id)
				.login_pw(login_pw).name(name).phone(phone).birth(birth).major(major).grade_level(grade_level).build();

		int result = studentService.updateStudentService(student);
		
		String studentMessage = result > 0 ? "수정이 완료되었습니다." : "수정이 실패했습니다.";
		studentMessage = URLEncoder.encode(studentMessage, "utf-8");
		response.sendRedirect("studentPage.do?studentMessage=" + studentMessage);
	}

}
