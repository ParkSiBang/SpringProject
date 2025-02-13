package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.StudentDTO;
import util.DBUtil;

public class StudentDAO {
	// 학생 로그인
	public StudentDTO loginStudent(String login_id, String login_pw) throws SQLException {
		String sql = "SELECT * FROM students WHERE login_id = ?";
		StudentDTO student = new StudentDTO();

		try (Connection conn = DBUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, login_id);

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					String pw = rs.getString("login_pw");

					if (pw.equals(login_pw))
						student = makeStudent(rs);
					else
						student.setStudent_id(-1);
				} else
					student.setStudent_id(-1);
			}
		}
		return student;
	}

	// 학생 전체 조회
	public List<StudentDTO> selectAllStudent() {
		String sql = "SELECT * FROM students";
		List<StudentDTO> studentList = new ArrayList<>();

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {

			while (rs.next()) {
				StudentDTO student = makeStudent(rs);
				studentList.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return studentList;
	}

	// 학생 1명 조회
	public StudentDTO selectByStudent_id(int student_id) {
		String sql = "SELECT * FROM students where student_id = ?";
		StudentDTO student = new StudentDTO();

		try (Connection conn = DBUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setInt(1, student_id);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				student = makeStudent(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
	}

	// makeStudent
	private static StudentDTO makeStudent(ResultSet rs) throws SQLException {
		StudentDTO student = new StudentDTO();

		student.setStudent_id(rs.getInt("Student_id"));
		student.setLogin_id(rs.getString("Login_id"));
		student.setLogin_pw(rs.getString("Login_pw"));
		student.setName(rs.getString("Name"));
		student.setPhone(rs.getString("Phone"));
		student.setBirth(rs.getDate("Birth"));
		student.setMajor(rs.getString("Major"));
		student.setGrade_level(rs.getInt("Grade_level"));

		return student;
	}

	// 학생 정보 수정
	public int updateStudent(StudentDTO student) {
		int result = 0;
		String sql = "UPDATE students SET name = ?, phone = ?, birth = ?, major = ?, grade_level = ? WHERE student_id = ?";

		try (Connection conn = DBUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, student.getName());
			st.setString(2, student.getPhone());
			st.setDate(3, student.getBirth());
			st.setString(4, student.getMajor());
			st.setInt(5, student.grade_level);
			st.setInt(6, student.student_id);

			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 학생 정보 삭제
	public int deleteStudent(int student_Id) {
		int result = 0;
		String sql = "DELETE FROM students WHERE student_id = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setInt(1, student_Id);
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 학생 회원가입
	public int signupStudent(StudentDTO newStudent) {
		int result = 0;
		String sql = "INSERT INTO students VALUES (student_id_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = DBUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, newStudent.getLogin_id());
			st.setString(2, newStudent.getLogin_pw());
			st.setString(3, newStudent.getName());
			st.setString(4, newStudent.getPhone());
			st.setDate(5, newStudent.getBirth());
			st.setString(6, newStudent.getMajor());
			st.setInt(7, newStudent.getGrade_level());

			result = st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 학생 회원가입 중복 검사
	public boolean checkSignup(String login_id) {
		String sql = "SELECT COUNT(*) FROM students WHERE login_id = ?";
		boolean isExist = false;

		try (Connection conn = DBUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, login_id);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				int cnt = rs.getInt(1);
				isExist = cnt > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isExist;
	}
}
