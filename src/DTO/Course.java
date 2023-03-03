package DTO;

import java.sql.Time;

public class Course {
	private int courseID;
	private String title;
	private int credits;
	private int departmentID;
	private String url;
	private String location;
	private String days;
	private Time time;
	private String name;

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course(int courseID, String title, int credits, int departmentID, String url) {
		super();
		this.courseID = courseID;
		this.title = title;
		this.credits = credits;
		this.departmentID = departmentID;
		this.url = url;
	}

	public Course(int courseID, String title, int credits, int departmentID, String location, String days, Time time) {
		super();
		this.courseID = courseID;
		this.title = title;
		this.credits = credits;
		this.departmentID = departmentID;
		this.location = location;
		this.days = days;
		this.time = time;
	}

	public int getCredits() {
		return credits;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int courseData() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

}
