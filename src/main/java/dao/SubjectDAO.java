package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.SubjectDTO;
import util.DBUtil;

public class SubjectDAO {
	// 전체 과목 조회
	public List<SubjectDTO> selectAllSubject() {
		String sql = "SELECT * FROM subjects";
		List<SubjectDTO> subjectList = new ArrayList<>();

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {

			while (rs.next()) {
				SubjectDTO subject = makeSubject(rs);
				subjectList.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return subjectList;
	}

	// 1개 과목 조회
	public SubjectDTO selectBySubject_id(int subject_Id) {
		String sql = "SELECT * FROM subjects where subject_Id = ?";
		SubjectDTO subject = new SubjectDTO();

		try (Connection conn = DBUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setInt(1, subject_Id);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				subject = makeSubject(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return subject;
	}

	// makeSubject
	private static SubjectDTO makeSubject(ResultSet rs) throws SQLException {
		SubjectDTO subject = new SubjectDTO();

		subject.setSubject_id(rs.getInt("Subject_id"));
		subject.setSubject_name(rs.getString("Subject_name"));
		subject.setProfessor(rs.getString("Professor"));

		return subject;
	}

	// 과목 추가
	public int insertSubject(SubjectDTO subject) {
		int result = 0;
		String sql = "INSERT INTO subjects VALUES (subject_id_seq.nextval, ?, ?)";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, subject.getSubject_name());
			st.setString(2, subject.getProfessor());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 과목 수정
	public int updateSubject(SubjectDTO subject) {
		int result = 0;
		String sql = "UPDATE subjects SET subject_name = ?, professor = ? WHERE subject_id = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, subject.getSubject_name());
			st.setString(2, subject.getProfessor());
			st.setInt(3, subject.getSubject_id());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 과목 삭제
	public int deleteSubject(int subject_id) {
		int result = 0;
		String sql = "DELETE FROM subjects WHERE subject_id = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setInt(1, subject_id);
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
