package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.GradeDTO;
import dto.GradeDetailDTO;
import util.DBUtil;

public class GradeDAO {
	// student_id(PK)로 성적 조회
	public static List<GradeDTO> getGrade(int student_id) {
		String sql = "SELECT * FROM grades WHERE student_id = ?";
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		List<GradeDTO> gradeList = new ArrayList<>();

		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, student_id);
			rs = st.executeQuery();

			if (!rs.isBeforeFirst()) {
				return gradeList;
			}

			while (rs.next()) {
				GradeDTO grade = new GradeDTO();
				grade.grade_id = rs.getInt("grade_id");
				grade.score = rs.getString("score");
				gradeList.add(grade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.DBDiscoonect(conn, st, rs);
		}

		return gradeList;
	}

	// 성적 추가
	public int insertGrade(GradeDTO grade) {
		int result = 0;
		String sql = "INSERT INTO grades VALUES (grade_id_seq.nextval, ?, ?, ?)";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, grade.getScore());
			st.setInt(2, grade.getStudent_id());
			st.setInt(3, grade.getSubject_id());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 성적 수정
	public int updateGrade(GradeDTO grade) {
		int result = 0;
		String sql = "UPDATE grades SET score = ? WHERE grade_id = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, grade.getScore());
			st.setInt(2, grade.getGrade_id());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 성적 삭제
	public int deleteGrade(int grade_id) {
		int result = 0;
		String sql = "DELETE FROM grades WHERE grade_id = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setInt(1, grade_id);
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 1개 성적 조회
	public GradeDTO selectByGrade_id(int grade_id) {
		String sql = "SELECT * FROM grades WHERE grade_id = ?";
		GradeDTO grade = new GradeDTO();

		try (Connection conn = DBUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setInt(1, grade_id);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					grade = new GradeDTO();
					grade.setGrade_id(rs.getInt("grade_id"));
					grade.setScore(rs.getString("score"));
					grade.setStudent_id(rs.getInt("student_id"));
					grade.setSubject_id(rs.getInt("subject_id"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return grade;
	}

	// GradeDTO -> GradeDetailDTO
	public GradeDetailDTO chageGradeDetailDTO(int grade_id) {
		String sql = """
				    SELECT g.grade_id, g.score, sub.subject_name, sub.professor, st.name
				    FROM grades g
				    JOIN subjects sub ON g.subject_id = sub.subject_id
				    JOIN students st ON g.student_id = st.student_id
				    WHERE g.grade_id = ?
				""";

		GradeDetailDTO gradeDetail = new GradeDetailDTO();

		try (Connection conn = DBUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setInt(1, grade_id);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					gradeDetail = new GradeDetailDTO();
					gradeDetail.setGrade_id(rs.getInt("grade_id"));
					gradeDetail.setName(rs.getString("name"));
					gradeDetail.setSubject_name(rs.getString("subject_name"));
					gradeDetail.setProfessor(rs.getString("professor"));
					gradeDetail.setScore(rs.getString("score"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gradeDetail;
	}
}
