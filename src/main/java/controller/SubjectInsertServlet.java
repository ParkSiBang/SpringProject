package controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.SubjectDTO;
import service.SubjectService;

@WebServlet("/adminPage/subjectInsert.do")
public class SubjectInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	SubjectService subjectService = new SubjectService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 성적 추가
		request.setCharacterEncoding("utf-8");
		String subject_name = request.getParameter("subject_name");
		String professor = request.getParameter("professor");
		
		SubjectDTO subject = SubjectDTO.builder().subject_name(subject_name).professor(professor).build();
		int result = subjectService.insertSubjectService(subject);
		
		String subjectMessage = result > 0 ? "추가가 완료되었습니다." : "추가에 실패했습니다.";
		subjectMessage = URLEncoder.encode(subjectMessage, "utf-8");
		response.sendRedirect("adminPage.do?subjectMessage=" + subjectMessage);
	}

}
