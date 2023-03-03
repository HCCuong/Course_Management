package BLL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAL.CourseDAL;
import DTO.Course;
import UI.CourseUI;

public class CourseBLL {
	private CourseUI courseGUI;
	private CourseDAL courseDAL;
	private List<Course> courseList;

	public CourseBLL() {
		courseDAL = new CourseDAL();
	}

	public List getOnlineCourseTable(int page) throws SQLException {
		int numofrecords = 30;
		ArrayList list = courseDAL.readCourseOnline();
		int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;

        return list.subList(from, Math.min(to, size));
	}

	public List getOnsiteCourseTable(int page) throws SQLException {
		int numofrecords = 30;
		ArrayList list = courseDAL.readCourseOnsite();
		int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;

        return list.subList(from, Math.min(to, size));
	}
	
	public List searchOnlineCourse(String key, int page) throws SQLException {
		int numofrecords = 5;
		List list = courseDAL.searchOnlineCourse(key);
		int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;

        return list.subList(from, Math.min(to, size));
	}
	
	public List searchOnsiteCourse(String key, int page) throws SQLException {
		int numofrecords = 5;
		List list = courseDAL.searchOnsiteCourse(key);
		int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;

        return list.subList(from, Math.min(to, size));
	}
	
	public int numOfSearchOnlineCourse(String key) throws SQLException{
		List list = courseDAL.searchOnlineCourse(key);
		double num = list.size();
        return (int) Math.ceil(num / 5);
	}
	
	public int numOfSearchOnsiteCourse(String key) throws SQLException{
		List list = courseDAL.searchOnsiteCourse(key);
		double num = list.size();
        return (int) Math.ceil(num / 5);
	}

	public int getDepartmentID(String name) throws SQLException {
		int result = courseDAL.readDepartmentID(name);
		return result;
	}

	public int addOnlineCourse(Course cs) throws SQLException {
		int result = courseDAL.addCourse(cs);
		int result1 = courseDAL.addOnlineCourse(cs);
		return result;
	}

	public int addOnsiteCourse(Course cs) throws SQLException {
		int result = courseDAL.addCourse(cs);
		int result1 = courseDAL.addOnsiteCourse(cs);
		return result;
	}

	public int updateOnlineCourse(Course cs, int id) throws SQLException {
		int result1 = courseDAL.updateCourse(cs, id);
		int result = courseDAL.updateOnlineCourse(cs, id);
		return result;
	}

	public int updateOnsiteCourse(Course cs, int id) throws SQLException {
		int result1 = courseDAL.updateCourse(cs, id);
		int result = courseDAL.updateOnsiteCourse(cs, id);
		return result;
	}

	public int deleteOnlineCourse(int id) throws SQLException {
		int result = courseDAL.deleteOnlineCourse(id);
		int result1 = courseDAL.deleteCourse(id);
		return result;
	}

	public int deleteOnsiteCourse(int id) throws SQLException {
		int result = courseDAL.deleteOnsiteCourse(id);
		int result1 = courseDAL.deleteCourse(id);
		return result;
	}
	
	public int numOfPageOnlineCourse() throws SQLException{
		ArrayList list = courseDAL.readCourseOnline();
		double num = list.size();
        return (int) Math.ceil(num / 5);
	}
	
	public int numOfPageOnsiteCourse() throws SQLException{
		ArrayList list = courseDAL.readCourseOnsite();
		double num = list.size();
        return (int) Math.ceil(num / 5);
	}

}
