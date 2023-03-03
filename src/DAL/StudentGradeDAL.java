/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author sooooooooon
 */
import java.sql.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.StudentGrade;

public class StudentGradeDAL extends MyDatabaseManager {

	public StudentGradeDAL() {
		super();
		this.connectDB();
	}

	public List searchAll(String keyword) throws SQLException {
		String query = "SELECT EnrollmentID, sg.CourseID, sg.StudentID, grade, Title, Firstname, Lastname "
				+ "FROM (studentgrade sg inner join course cs ON sg.CourseID = cs.CourseID) INNER JOIN person ps ON sg.StudentID = ps.PersonID " 
				+ "WHERE cast(EnrollmentID as CHAR) like ?"
				+ " OR cast(sg.CourseID as CHAR) like ?" 
				+ " OR cast(sg.StudentID as CHAR) like ?"
				+ " OR cast(grade as CHAR) like ?"
				+ " OR cast(Title as CHAR) like ?"
				+ " OR cast(Firstname as CHAR) like ?"
				+ " OR cast(Lastname as CHAR) like ?";
		List studentGrades = new ArrayList();
		ResultSet rs = null;

		PreparedStatement p = StudentGradeDAL.getConnection().prepareStatement(query);
		
		for(int i = 1; i< 8; i++) {
			p.setString(i, "%" + keyword + "%");
		}

		
		rs = p.executeQuery();
		while (rs.next()) {
			String firstname = rs.getString("Firstname") + " ";
			String lastname = rs.getString("Lastname");
			String name = firstname.concat(lastname);
			StudentGrade s = new StudentGrade(rs.getInt("EnrollmentID"), rs.getInt("CourseID"), rs.getInt("StudentID"),
					rs.getBigDecimal("grade"), rs.getString("Title"), name);
			studentGrades.add(s);
			
		}

		return studentGrades;

	}

	public int delete(int enrollmentID) throws SQLException {
		String query = "DELETE FROM StudentGrade WHERE enrollmentID = ?";
		PreparedStatement p = StudentGradeDAL.getConnection().prepareStatement(query);
		p = getConnection().prepareStatement(query);
		p.setInt(1, enrollmentID);
		int status = p.executeUpdate();
		return status;
	}

	public List getAll() throws SQLException {
		String query = "SELECT EnrollmentID, sg.CourseID, sg.StudentID, grade, Title, Firstname, Lastname "
				+ "FROM studentgrade sg, person ps, course cs "
				+ "WHERE sg.CourseID = cs.CourseID AND sg.StudentID = ps.PersonID";
		List studentGrades = new ArrayList();
		
		PreparedStatement ps = StudentGradeDAL.getConnection().prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		if(rs != null) {
			int i =1;
			while (rs.next()) {
				String firstname = rs.getString("Firstname") + " ";
				String lastname = rs.getString("Lastname");
				String name = firstname.concat(lastname);
				StudentGrade s = new StudentGrade(rs.getInt("EnrollmentID"), rs.getInt("CourseID"), rs.getInt("StudentID"),
						rs.getBigDecimal("grade"), rs.getString("Title"), name);
				studentGrades.add(s);
			}
		}
		return studentGrades;
	}

	public int joinCourse(int courseID, int studentID) throws SQLException {
		String query = "INSERT INTO studentgrade(CourseID, StudentID) VALUE (?,?)";
		PreparedStatement p = StudentGradeDAL.getConnection().prepareStatement(query);
		p = getConnection().prepareStatement(query);
		p.setInt(1, courseID);
		p.setInt(2, studentID);
		int status = p.executeUpdate();
		return status;
	}

	public int updateStudent(BigDecimal grade, int enrollmentID) throws SQLException {
		String query = "UPDATE studentgrade SET Grade = ? WHERE EnrollmentID = ?";
		PreparedStatement p = StudentGradeDAL.getConnection().prepareStatement(query);
		p.setBigDecimal(1, grade);
		p.setInt(2, enrollmentID);
		int status = p.executeUpdate();

		return status;
	}

	public ArrayList getAllStudent() throws SQLException {
		String query = "SELECT Firstname, Lastname FROM person WHERE EnrollmentDate IS NOT NULL";
		ArrayList studentIDs = new ArrayList();
		ResultSet rs = null;

		PreparedStatement p = PersonDAL.getConnection().prepareStatement(query);
		rs = p.executeQuery();

		while (rs.next()) {
			StudentGrade sg = new StudentGrade();
			String firstname = rs.getString("Firstname") + " ";
			String lastname = rs.getString("Lastname");
			String name = firstname.concat(lastname);
			sg.setName(name);

			studentIDs.add(sg);
		}


		return studentIDs;
	}

	public ArrayList getAllCourse() throws SQLException {

		String query = "SELECT Title FROM course";
		ArrayList courses = new ArrayList();
		ResultSet rs = null;

		PreparedStatement p = PersonDAL.getConnection().prepareStatement(query);
		rs = p.executeQuery();

		while (rs.next()) {
			StudentGrade sg = new StudentGrade();
			sg.setCourse(rs.getString("Title"));

			courses.add(sg);
		}

		return courses;
	}

	public int readStudentID(String name) throws SQLException {
		int a = 0;
		String s[] = name.split("\\s", 2);
		String firstname = s[0];
		String lastname = s[1];
		String query = "SELECT PersonID FROM person WHERE Firstname='" + firstname + "' AND Lastname='" + lastname
				+ "'";
		ResultSet rs = this.doReadQuery(query);
		if (rs != null) {
			while (rs.next()) {
				a = rs.getInt("PersonID");
			}
		}
		return a;
	}

	public int readCourseID(String course) throws SQLException {
		int a = 0;
		String query = "SELECT CourseID FROM course WHERE Title='" + course + "'";
		ResultSet rs = this.doReadQuery(query);
		if (rs != null) {
			while (rs.next()) {
				a = rs.getInt("CourseID");
			}
		}
		return a;
	}
}
