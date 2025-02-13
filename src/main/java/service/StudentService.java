package service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import dao.GradeDAO;
import dao.StudentDAO;
import dto.GradeDTO;
import dto.StudentDTO;

public class StudentService {
	StudentDAO studentDAO = new StudentDAO();
	GradeDAO gradeDAO = new GradeDAO();

	// [학생]성적 조회 서비스
	public List<GradeDTO> getGradeService(int student_id) {
		List<GradeDTO> gradeList = GradeDAO.getGrade(student_id);
		return gradeList;
	}

	// 석차 계산
	public List<Map.Entry<Integer, Double>> Allrank() {
		TreeMap<Integer, Double> AllrankMap = new TreeMap<>(Comparator.reverseOrder());
		List<StudentDTO> studentList = studentDAO.selectAllStudent();

		for (StudentDTO student : studentList) {
			List<GradeDTO> gradeList = GradeDAO.getGrade(student.getStudent_id());

			if (gradeList.isEmpty()) {
				AllrankMap.put(student.getStudent_id(), 0.0);
			} else {
				List<String> convertGradeList = new ArrayList<>();
				for (GradeDTO grade : gradeList) {
					convertGradeList.add(grade.getScore());
				}

				double avgScore = convertAvgScore(convertGradeList);
				AllrankMap.put(student.getStudent_id(), avgScore);
			}
		}
		
		// value(평균 평점)를 기준으로 내림차순 
		List<Map.Entry<Integer, Double>> sortedRankList = new ArrayList<>(AllrankMap.entrySet());
		sortedRankList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        
		return sortedRankList;
	}

	// 성적 통계 서비스
	public List<Object> GradeStatsService(int student_id) {
		List<Object> StatsList = new ArrayList<>();

		// 평균 평점, 평균 등급 계산
		List<GradeDTO> gradeList = GradeDAO.getGrade(student_id);
		List<String> convertGradeList = new ArrayList<>();
		if (!gradeList.isEmpty()) {
			for (GradeDTO grade : gradeList) {
				convertGradeList.add(grade.getScore());
			}

			double avgScore = convertAvgScore(convertGradeList);
			String avgGrade = convertAvgGrade(avgScore);

			List<Map.Entry<Integer, Double>> sortedRankList = Allrank();
			
			int rank = 1;
			for (Entry<Integer, Double> entry : sortedRankList) {
				if (entry.getKey() == student_id) {
					break;
				}
				rank++;
			}

			StatsList.add(avgScore);
			StatsList.add(avgGrade);
			StatsList.add(rank);
		} else {
			StatsList.add(0.0);
			StatsList.add("N/A");
			StatsList.add(0);
		}

		return StatsList;
	}

	// 평균 평점 변환
	public double convertAvgScore(List<String> convertGradeList) {
		double total = 0;

		for (String grade : convertGradeList) {
			switch (grade) {
			case "A+" -> total += 4.5;
			case "A" -> total += 4.0;
			case "B+" -> total += 3.5;
			case "B" -> total += 3.0;
			case "C+" -> total += 2.5;
			case "C" -> total += 2.0;
			case "D+" -> total += 1.5;
			case "D" -> total += 1.0;
			case "F" -> total += 0.0;
			}
		}

		DecimalFormat df = new DecimalFormat("#.##");
		total = Double.parseDouble(df.format(total / convertGradeList.size()));

		return total;
	}

	// 평균 등급 변환
	public String convertAvgGrade(double total) {
		String avgGrade = "";

		if (total >= 4.5)
			avgGrade = "A+";
		else if (total >= 4.0)
			avgGrade = "A";
		else if (total >= 3.5)
			avgGrade = "B+";
		else if (total >= 3.0)
			avgGrade = "B";
		else if (total >= 2.5)
			avgGrade = "C+";
		else if (total >= 2.0)
			avgGrade = "C";
		else if (total >= 1.5)
			avgGrade = "D+";
		else if (total >= 1.0)
			avgGrade = "D";
		else
			avgGrade = "F";

		return avgGrade;
	}

	// 1명 학생 조회 서비스
	public StudentDTO selectByStudent_idService(int studentId) {
		return studentDAO.selectByStudent_id(studentId);
	}

	// [관리자]전체 학생 조회 서비스
	public List<StudentDTO> selectAllStudentService() {
		return studentDAO.selectAllStudent();
	}

	// 학생 정보 수정 서비스
	public int updateStudentService(StudentDTO student) {
		return studentDAO.updateStudent(student);
	}

	// 학생 정보 삭제 서비스
	public int deleteStudentService(int studentId) {
		return studentDAO.deleteStudent(studentId);
	}
}
