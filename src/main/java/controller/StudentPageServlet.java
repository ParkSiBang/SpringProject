package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.GradeDTO;
import dto.GradeDetailDTO;
import dto.StudentDTO;
import service.GradeDetailService;
import service.GradeService;
import service.StudentService;

@WebServlet("/studentPage/studentPage.do")
public class StudentPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	StudentService studentService = new StudentService();
	GradeService gradeService = new GradeService();
	GradeDetailService gradeDetailService = new GradeDetailService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String str_student_id = String.valueOf(session.getAttribute("student_id"));
		int student_id = Integer.parseInt(str_student_id);
		
		StudentDTO student = studentService.selectByStudent_idService(student_id);
		request.setAttribute("studentName", student.getName());
		request.setAttribute("studentInfo", student);
		
		List<Object> gradeStatsList = studentService.GradeStatsService(student_id);
		request.setAttribute("gradeStatsList", gradeStatsList);
		
		List<GradeDTO> studentGradeList = studentService.getGradeService(student_id);
		List<GradeDetailDTO> studentGradeDetailList = new ArrayList<GradeDetailDTO>();
		for (GradeDTO studentGrade : studentGradeList) {
			studentGradeDetailList.add(gradeService.chageGradeDetailDTOService(studentGrade.getGrade_id()));
	    }
		request.setAttribute("studentGradeDetails", studentGradeDetailList);
		
		RequestDispatcher rd = request.getRequestDispatcher("studentPage.jsp");
		rd.forward(request, response);
	}

}
