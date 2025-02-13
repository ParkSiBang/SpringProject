package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.GradeDetailDTO;
import util.DBUtil;

public class GradeDetailDAO {
	// 전체 성적 상세조회
	public List<GradeDetailDTO> selectAllGradeDetail() {
		String sql = "SELECT g.grade_id, st.name, sub.subject_name, sub.professor, g.score " + "FROM grades g "
				+ "JOIN students st ON g.student_id = st.student_id "
				+ "JOIN subjects sub ON g.subject_id = sub.subject_id";
		List<GradeDetailDTO> gradeDetailsList = new ArrayList<>();

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {

			while (rs.next()) {
				GradeDetailDTO gradeDetail = new GradeDetailDTO();
				gradeDetail.setGrade_id(rs.getInt("grade_id"));
				gradeDetail.setName(rs.getString("name"));
				gradeDetail.setSubject_name(rs.getString("subject_name"));
				gradeDetail.setScore(rs.getString("score"));
				gradeDetail.setProfessor(rs.getString("professor"));
				gradeDetailsList.add(gradeDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gradeDetailsList;
	}

	// 1개 성적 상세조회
	public GradeDetailDTO selectByGradeDetail(int grade_Id) {
		String sql = "SELECT g.grade_id, g.score, st.name, sub.subject_name, sub.professor " + "FROM grades g "
				+ "JOIN students st ON g.student_id = st.student_id "
				+ "JOIN subjects sub ON g.subject_id = sub.subject_id " + "WHERE g.grade_id = ?";
		GradeDetailDTO GradeDetail = new GradeDetailDTO();

		try (Connection conn = DBUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setInt(1, grade_Id);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					GradeDetail = makeGradeDetail(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return GradeDetail;
	}

	// makeGradeDetail
	private static GradeDetailDTO makeGradeDetail(ResultSet rs) throws SQLException {
		GradeDetailDTO gradeDetail = new GradeDetailDTO();

		gradeDetail.setGrade_id(rs.getInt("grade_id"));
		gradeDetail.setScore(rs.getString("score"));
		gradeDetail.setName(rs.getString("name"));
		gradeDetail.setSubject_name(rs.getString("subject_name"));
		gradeDetail.setProfessor(rs.getString("professor"));

		return gradeDetail;
	}
}
