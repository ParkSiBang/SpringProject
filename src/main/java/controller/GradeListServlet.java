package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.GradeDetailDTO;
import service.GradeDetailService;


@WebServlet("/grade/gradeList.do")
public class GradeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	GradeDetailService gradeDetailService = new GradeDetailService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전체 성적 조회
		List<GradeDetailDTO> gradeList = gradeDetailService.selectAllGradeDetailService();
		request.setAttribute("grades", gradeList);

		RequestDispatcher rd = request.getRequestDispatcher("gradeListTable.jsp");
		rd.forward(request, response);
	}

}
