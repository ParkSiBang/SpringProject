package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.StudentDTO;
import service.StudentService;

@WebServlet("/student/studentList.do")
public class StudentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	StudentService studentService = new StudentService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 전체 학생 조회
		List<List<Object>> studentInfoList = new ArrayList<>();
		List<StudentDTO> studentList = studentService.selectAllStudentService();

		for (StudentDTO student : studentList) {
		    List<Object> gradeStats = studentService.GradeStatsService(student.getStudent_id());
		    
		    List<Object> studentInfo = new ArrayList<>();
		    studentInfo.add(student);
		    studentInfo.add(gradeStats.get(0)); //
		    studentInfo.add(gradeStats.get(2)); //
		    
		    studentInfoList.add(studentInfo);
		}

		studentInfoList.sort((info1, info2) -> {
		    Integer rank1 = (Integer) info1.get(2);
		    Integer rank2 = (Integer) info2.get(2);
		    
		    if (rank1 == 0) {
		        return 1;
		    } else if (rank2 == 0) {
		        return -1;
		    }
		    return rank1.compareTo(rank2);
		});
		
		request.setAttribute("studentInfos", studentInfoList);
		RequestDispatcher rd = request.getRequestDispatcher("studentListTable.jsp");
		rd.forward(request, response);
	}
	
}
