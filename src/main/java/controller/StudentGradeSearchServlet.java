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

import dto.GradeDTO;
import dto.GradeDetailDTO;
import service.GradeDetailService;
import service.StudentService;

@WebServlet("/studentPage/studentGradeSearch.do")
public class StudentGradeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//HttpSession session = request.getSession(false);
		//String str_student_id = (String)session.getAttribute("student_id");
		//int student_id = Integer.parseInt(str_student_id);

		StudentService studentService = new StudentService();
		List<GradeDTO> studentGradeList = studentService.getGradeService(101);
		
		GradeDetailService gradeDetailService = new GradeDetailService();
		List<GradeDetailDTO> studentGradeDetailList = new ArrayList<>();
		
		for(GradeDTO studentGrade : studentGradeList) {
			GradeDetailDTO gradeDetail = gradeDetailService.selectByGradeDetailService(studentGrade.getGrade_id());
			studentGradeDetailList.add(gradeDetail);
		}
		
		request.setAttribute("studentGradeDetails", studentGradeDetailList);
		RequestDispatcher rd = request.getRequestDispatcher("studentGradeSearch.jsp");
		rd.forward(request, response);
	}

}
