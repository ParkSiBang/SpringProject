package service;

import dao.GradeDAO;
import dto.GradeDTO;
import dto.GradeDetailDTO;

public class GradeService {
	GradeDAO gradeDAO = new GradeDAO();

	// 성적 추가 서비스
	public int insertGradeService(GradeDTO grade) {
		//유효하지 않은 학생 혹은 과목을 입력시 실패
		if (grade.student_id == 0 || grade.subject_id == 0)
			return 0;
		return gradeDAO.insertGrade(grade);
	}

	// 성적 수정 서비스
	public int updateGradeService(GradeDTO grade) {
		return gradeDAO.updateGrade(grade);
	}

	// 성적 삭제 서비스
	public int deleteGradeService(int grade_id) {
		return gradeDAO.deleteGrade(grade_id);
	}

	// 1개 성적 조회 서비스
	public GradeDTO selectByGrade_idService(int grade_id) {
		return gradeDAO.selectByGrade_id(grade_id);
	}

	// GradeDetail로 변경 서비스
	public GradeDetailDTO chageGradeDetailDTOService(int grade_id) {
		return gradeDAO.chageGradeDetailDTO(grade_id);
	}

}
