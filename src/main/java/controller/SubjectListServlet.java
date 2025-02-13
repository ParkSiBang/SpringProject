package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.SubjectDTO;
import service.SubjectService;

@WebServlet("/subject/subjectList.do")
public class SubjectListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 전체 과목 조회
		SubjectService subjectService = new SubjectService();
		List<SubjectDTO> subjectList = subjectService.selectAllSubjectService();
		request.setAttribute("subjects", subjectList);
		
		RequestDispatcher rd = request.getRequestDispatcher("subjectListTable.jsp");
		rd.forward(request, response);
	}

}
