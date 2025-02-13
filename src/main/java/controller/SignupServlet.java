package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.StudentDTO;
import service.LoginService;
import util.DateUtil;

@WebServlet("/login/signup.do")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	LoginService loginService = new LoginService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 아이디 중복 검사
        String login_id = request.getParameter("login_id");

        boolean isDuplicate = loginService.checkSignupService(login_id);

        response.setContentType("text/plain; charset=utf-8");
        if (isDuplicate) {
            response.getWriter().write("이미 사용 중인 아이디입니다.");
        } else {
            response.getWriter().write("사용 가능한 아이디입니다.");
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 회원가입
		request.setCharacterEncoding("utf-8");
		String login_id = request.getParameter("login_id");
		String login_pw = request.getParameter("login_pw");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		Date birth = DateUtil.convertSqlDate(DateUtil.convertDate(request.getParameter("birth")));
		String major = request.getParameter("major");
		int grade_level = Integer.parseInt(request.getParameter("grade_level"));

		StudentDTO student = StudentDTO.builder().login_id(login_id).login_pw(login_pw).name(name).phone(phone)
				.birth(birth).major(major).grade_level(grade_level).build();
		int result = loginService.signupStudentService(student);

		response.getWriter().write(result+"");
	}

}
