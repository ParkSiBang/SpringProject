package service;

import java.sql.SQLException;

import dao.AdminDAO;
import dao.StudentDAO;
import dto.StudentDTO;

public class LoginService {
	StudentDAO studentDAO = new StudentDAO();
	AdminDAO adminDAO = new AdminDAO();

	// 관리자 로그인 서비스
	public boolean adminLoginService(String login_id, String login_pw) {
		boolean isValid = adminDAO.AdminLogin(login_id, login_pw);

		return isValid;
	}

	// 학생 로그인 서비스
	public StudentDTO studentLoginService(String login_id, String login_pw) throws SQLException {
		StudentDTO student = new StudentDTO();
		student = studentDAO.loginStudent(login_id, login_pw);

		return student;
	}

	// 학생 회원가입 서비스
	public int signupStudentService(StudentDTO newStudent) {
		return studentDAO.signupStudent(newStudent);
	}
	
	// 학생 회원가입 중복 검사 서비스
	public boolean checkSignupService(String login_id) {
		return studentDAO.checkSignup(login_id);
	}
}
