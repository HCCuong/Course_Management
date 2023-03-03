/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sooooooooon
 */
import DAL.*;
import DTO.StudentGrade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentGradeBLL {
	StudentGradeDAL studentGradeDAL;
	
	public StudentGradeBLL() {
		super();
		// TODO Auto-generated constructor stub
		studentGradeDAL = new StudentGradeDAL();
	}
	
	public List initStudentList(int page) throws SQLException {
		int numofrecords = 50;
		List list = new ArrayList();
        list = studentGradeDAL.getAll();
        int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;

        return list.subList(from, Math.min(to, size));
    }
	
	public int numOfStudentGradePage() throws SQLException {
    	List list = studentGradeDAL.getAll();
    	double num = list.size();
        return (int) Math.ceil(num / 10);
    }
	
	public List searchStudentGrade(String key, int page) throws SQLException{
		int numofrecords = 10;
		List list = studentGradeDAL.searchAll(key);
		int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;

        return list.subList(from, Math.min(to, size));
	}
	
	public int numOfSearchPage(String key) throws SQLException{
		List list = studentGradeDAL.searchAll(key);
		double num = list.size();
        return (int) Math.ceil(num / 10);
	}

	public int updateGrade(ArrayList<String> studentInfo) {
		int enrollmentID, courseID, studentID;
		BigDecimal grade;
		try {
			enrollmentID = Integer.parseInt(studentInfo.get(0));
			grade = new BigDecimal(studentInfo.get(3)).setScale(2, RoundingMode.HALF_EVEN);

		} catch (NumberFormatException e) {
			e.printStackTrace();
			return -1;
		}

		int statusCode = -1;
		try {
			statusCode = studentGradeDAL.updateStudent(grade, enrollmentID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return statusCode;

	}

	public ArrayList<StudentGrade> getSearchResult(String keyword) {
		List list;
		try {
			list = studentGradeDAL.searchAll(keyword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		ArrayList<StudentGrade> students = new ArrayList<StudentGrade>();

		for (int i = 0; i < list.size(); i++) {
			StudentGrade student_t = (StudentGrade) list.get(i);
			students.add(student_t);
		}

		return students;

	}

	public int deleteStudentFromCourse(int enrollmentID) {
		int status;
		try {
			status = studentGradeDAL.delete(enrollmentID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return status;

	}

	public int registerNewCourse(int courseID, int studentID) {
		int status;
		try {
			status = studentGradeDAL.joinCourse(courseID, studentID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}

		return status;

	}

	public ArrayList readStudent() throws SQLException {
		ArrayList list = studentGradeDAL.getAllStudent();
		return list;
	}

	public ArrayList readCourse() throws SQLException {
		ArrayList list = studentGradeDAL.getAllCourse();
		return list;
	}

	public int readStudentID(String name) throws SQLException {
		int result = studentGradeDAL.readStudentID(name);
		return result;
	}

	public int readCourseID(String course) throws SQLException {
		int result = studentGradeDAL.readCourseID(course);
		return result;
	}
	@Override
	public void finalize() {
		studentGradeDAL = null;
	}
}
