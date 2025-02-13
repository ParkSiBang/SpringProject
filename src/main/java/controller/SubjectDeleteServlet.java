package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.SubjectService;

@WebServlet("/adminPage/subjectDelete.do")
public class SubjectDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	SubjectService subjectService = new SubjectService();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 과목 삭제
		String str_subject_id = request.getParameter("subject_id");
		int subject_id = 0;
		if (str_subject_id != null) {
			subject_id = Integer.parseInt(str_subject_id);
		}

		int result = subjectService.deleteSubjectService(subject_id);

		String subjectMessage = result > 0 ? "삭제가 완료되었습니다." : "삭제가 실패했습니다.";
		subjectMessage = URLEncoder.encode(subjectMessage, "utf-8");
		response.sendRedirect("adminPage.do?subjectMessage=" + subjectMessage);
	}

}
