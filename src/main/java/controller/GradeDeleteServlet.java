package controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GradeService;

@WebServlet("/adminPage/gradeDelete.do")
public class GradeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	GradeService gradeService = new GradeService();
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 성적 삭제
		String str_grade_id = request.getParameter("grade_id");
		int grade_id = 0;
		if (str_grade_id != null) {
			grade_id = Integer.parseInt(str_grade_id);
		}

		int result = gradeService.deleteGradeService(grade_id);

		String gradeMessage = result > 0 ? "삭제가 완료되었습니다." : "삭제가 실패했습니다.";
		gradeMessage = URLEncoder.encode(gradeMessage, "utf-8");
		response.sendRedirect("adminPage.do?gradeMessage=" + gradeMessage);
	}

}
