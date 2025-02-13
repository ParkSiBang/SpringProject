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

@WebServlet("/adminPage/subjectUpdate.do")
public class SubjectUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	SubjectService subjectService = new SubjectService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 과목 수정
		request.setCharacterEncoding("utf-8");
		int subject_id = Integer.parseInt(request.getParameter("subject_id"));
		String subject_name = request.getParameter("subject_name");
		String professor = request.getParameter("professor");

		SubjectDTO subject = SubjectDTO.builder().subject_id(subject_id).subject_name(subject_name).professor(professor)
				.build();
		int result = subjectService.updateSubjectService(subject);
		
		String subjectMessage = result > 0 ? "수정이 완료되었습니다." : "수정이 실패했습니다.";
		subjectMessage = URLEncoder.encode(subjectMessage, "utf-8");
		response.sendRedirect("adminPage.do?subjectMessage=" + subjectMessage);
	}

}
