package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.StudentDTO;
import service.LoginService;

@WebServlet("/login/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	LoginService loginService = new LoginService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("loginPage.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String login_id = request.getParameter("login_id");
		String login_pw = request.getParameter("login_pw");

		if (login_id.equals("admin")) {
			boolean isValid = loginService.adminLoginService(login_id, login_pw);
			if (isValid) {
				response.sendRedirect("../adminPage/adminPage.do");
			} else {
				loginFail(response);
			}
		} else {
			StudentDTO student = new StudentDTO();
			try {
				student = loginService.studentLoginService(login_id, login_pw);
				if (student.getStudent_id() == -1) {
					loginFail(response);
				} else {
					HttpSession session = request.getSession();
					session.setAttribute("student_id", student.getStudent_id());
					response.sendRedirect("../studentPage/studentPage.do");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private void loginFail(HttpServletResponse response) throws IOException {
		String loginFailMessage = "로그인에 실패했습니다. 아이디 또는 비밀번호를 확인해 주세요.";
		loginFailMessage = URLEncoder.encode(loginFailMessage, "utf-8");
		response.sendRedirect("loginPage.jsp?loginFailMessage=" + loginFailMessage);
	}

}
