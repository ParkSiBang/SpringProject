package controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.GradeDTO;
import service.GradeDetailService;
import service.GradeService;

@WebServlet("/adminPage/gradeUpdate.do")
public class gradeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	GradeService gradeService = new GradeService();
	GradeDetailService gradeDetailService = new GradeDetailService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 성적 수정
		request.setCharacterEncoding("utf-8");
		int grade_id = Integer.parseInt(request.getParameter("grade_id"));
		String score = request.getParameter("score");
		
		GradeDTO grade = gradeService.selectByGrade_idService(grade_id);
		grade.setScore(score);
		int result = gradeService.updateGradeService(grade);

		String gradeMessage = result > 0 ? "수정이 완료되었습니다." : "수정이 실패했습니다.";
		gradeMessage = URLEncoder.encode(gradeMessage, "utf-8");
		response.sendRedirect("adminPage.do?gradeMessage=" + gradeMessage);
	}

}
