package DTO;

import java.math.BigDecimal;

public class StudentGrade {
	private int enrollmentID;
	private int courseID;
	private int studentID;
	private BigDecimal grade;
	private String course;
	private String name;

	public StudentGrade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentGrade(int enrollmentID, int courseID, int studentID, BigDecimal grade) {
		super();
		this.enrollmentID = enrollmentID;
		this.courseID = courseID;
		this.studentID = studentID;
		this.grade = grade;
	}

	public StudentGrade(int enrollmentID, int courseID, int studentID, BigDecimal grade, String course, String name) {
		super();
		this.enrollmentID = enrollmentID;
		this.courseID = courseID;
		this.studentID = studentID;
		this.grade = grade;
		this.course = course;
		this.name = name;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEnrollmentID() {
		return enrollmentID;
	}

	public void setEnrollmentID(int enrollmentID) {
		this.enrollmentID = enrollmentID;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public BigDecimal getGrade() {
		return grade;
	}

	public void setGrade(BigDecimal grade) {
		this.grade = grade;
	}
}
