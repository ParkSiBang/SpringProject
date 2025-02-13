package service;

import java.util.List;

import dao.SubjectDAO;
import dto.SubjectDTO;

public class SubjectService {
	SubjectDAO subjectDAO = new SubjectDAO();

	// 전체 과목 조회 서비스
	public List<SubjectDTO> selectAllSubjectService() {
		return subjectDAO.selectAllSubject();
	}

	// 1개 과목 조회 서비스
	public SubjectDTO selectBySubject_idService(int subject_id) {
		return subjectDAO.selectBySubject_id(subject_id);
	}

	// 과목 추가 서비스
	public int insertSubjectService(SubjectDTO subject) {
		return subjectDAO.insertSubject(subject);
	}

	// 과목 수정 서비스
	public int updateSubjectService(SubjectDTO subject) {
		return subjectDAO.updateSubject(subject);
	}

	// 과목 삭제 서비스
	public int deleteSubjectService(int subject_id) {
		return subjectDAO.deleteSubject(subject_id);
	}
}
