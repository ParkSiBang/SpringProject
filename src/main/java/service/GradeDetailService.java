package service;

import java.util.List;

import dao.GradeDetailDAO;
import dto.GradeDetailDTO;

public class GradeDetailService {
	GradeDetailDAO gradeDetailDAO = new GradeDetailDAO();

	// [관리자]전체 성적 상세조회 서비스
	public List<GradeDetailDTO> selectAllGradeDetailService() {
		return gradeDetailDAO.selectAllGradeDetail();
	}

	// [관리자]관리자 1개 성적 상세조회 서비스
	public GradeDetailDTO selectByGradeDetailService(int grade_id) {
		return gradeDetailDAO.selectByGradeDetail(grade_id);
	}
	
	
}
