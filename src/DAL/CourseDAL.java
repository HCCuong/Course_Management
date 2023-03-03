package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Assignment;
import DTO.Course;
import UI.CourseUI;

public class CourseDAL extends MyDatabaseManager {

	private static Course cs;

	public CourseDAL() {
		super();
		this.connectDB();
	}

	public ArrayList readCourseOnline() throws SQLException {
		ArrayList course = new ArrayList();
		String query = "SELECT c.CourseID, Title, Credits, Name, url " + "FROM course c, department d, onlinecourse o "
				+ "WHERE c.CourseID = o.CourseID AND d.DepartmentID = c.DepartmentID";
		ResultSet rs = this.doReadQuery(query);
		if (rs != null) {
			while (rs.next()) {
				cs = new Course();
				cs.setCourseID(rs.getInt("CourseID"));
				cs.setTitle(rs.getString("Title"));
				cs.setCredits(rs.getInt("Credits"));
				cs.setName(rs.getString("Name"));
				cs.setUrl(rs.getString("url"));

				course.add(cs);
			}
		}
		return course;
	}

	public ArrayList readCourseOnsite() throws SQLException {
		ArrayList course = new ArrayList();
		String query = "SELECT c.CourseID, Title, Credits, Name, Location, Days, Time "
				+ "FROM course c, department d, onsitecourse o "
				+ "WHERE c.CourseID = o.CourseID AND d.DepartmentID = c.DepartmentID";
		ResultSet rs = this.doReadQuery(query);
		if (rs != null) {
			while (rs.next()) {
				cs = new Course();
				cs.setCourseID(rs.getInt("CourseID"));
				cs.setTitle(rs.getString("Title"));
				cs.setCredits(rs.getInt("Credits"));
				cs.setName(rs.getString("Name"));
				cs.setLocation(rs.getString("Location"));
				cs.setDays(rs.getString("Days"));
				cs.setTime(rs.getTime("Time"));

				course.add(cs);
			}
		}
		return course;
	}
	
	public List searchOnlineCourse(String Key) throws SQLException{
		List course = new ArrayList();
		String query = "SELECT cs.CourseID, cs.Title, Credits, Name, url "
				+ "FROM (course cs INNER JOIN onlinecourse ocs ON cs.CourseID = ocs.CourseID) INNER JOIN department dm ON dm.DepartmentID = cs.DepartmentID "
				+ "WHERE cast(Title AS CHAR) like ? "
				+ "OR cast(Credits AS CHAR) like ? "
				+ "OR cast(Name AS CHAR) like ? "
				+ "OR cast(url AS CHAR) like ? ";
		PreparedStatement p = CourseDAL.getConnection().prepareStatement(query);
		p.setString(1, "%" + Key + "%");
		p.setString(2, "%" + Key + "%");
		p.setString(3, "%" + Key + "%");
		p.setString(4, "%" + Key + "%");
		ResultSet rs = p.executeQuery();
		if(rs != null) {
			while(rs.next()){
				cs = new Course();
				cs.setCourseID(rs.getInt("CourseID"));
				cs.setTitle(rs.getString("Title"));
				cs.setCredits(rs.getInt("Credits"));
				cs.setName(rs.getString("Name"));
				cs.setUrl(rs.getString("url"));
				
				course.add(cs);
			}
		}
		return course;
	}

	public List searchOnsiteCourse(String Key) throws SQLException{
		List course = new ArrayList();
		String query = "SELECT cs.CourseID, cs.Title, Credits, Name, Location, Days, Time "
				+ "FROM (course cs INNER JOIN onsitecourse os ON cs.CourseID = os.CourseID) INNER JOIN department dm ON dm.DepartmentID = cs.DepartmentID "
				+ "WHERE cast(Title AS CHAR) like ? "
				+ "OR cast(Credits AS CHAR) like ? "
				+ "OR cast(Name AS CHAR) like ? "
				+ "OR cast(Location AS CHAR) like ? ";
		PreparedStatement p = CourseDAL.getConnection().prepareStatement(query);
		p.setString(1, "%" + Key + "%");
		p.setString(2, "%" + Key + "%");
		p.setString(3, "%" + Key + "%");
		p.setString(4, "%" + Key + "%");
		ResultSet rs = p.executeQuery();
		if(rs != null) {
			while(rs.next()){
				cs = new Course();
				cs.setCourseID(rs.getInt("CourseID"));
				cs.setTitle(rs.getString("Title"));
				cs.setCredits(rs.getInt("Credits"));
				cs.setName(rs.getString("Name"));
				cs.setLocation(rs.getString("Location"));
				cs.setDays(rs.getString("Days"));
				cs.setTime(rs.getTime("Time"));
				
				course.add(cs);
			}
		}
		return course;
	}
	
	public int readDepartmentID(String name) throws SQLException {
		int DepartmentID = 0;
		String query = "SELECT DepartmentID FROM department WHERE Name='" + name + "'";
		ResultSet rs = this.doReadQuery(query);
		if (rs != null) {
			while (rs.next()) {
				DepartmentID = rs.getInt("DepartmentID");
			}
		}

		return DepartmentID;
	}

	public static int addCourse(Course cs) throws SQLException {
		String query = "INSERT INTO course (CourseID, Title, Credits, DepartmentID) values (?, ?, ?, ?); ";
		PreparedStatement p = CourseDAL.getConnection().prepareStatement(query);
		p.setInt(1, cs.getCourseID());
		p.setString(2, cs.getTitle());
		p.setInt(3, cs.getCredits());
		p.setInt(4, cs.getDepartmentID());
		int result = p.executeUpdate();

		return result;
	}

	public static int addOnlineCourse(Course cs) throws SQLException {
		String query = "INSERT INTO onlinecourse (CourseID, url) values (?, ?); ";
		PreparedStatement p = CourseDAL.getConnection().prepareStatement(query);
		p.setInt(1, cs.getCourseID());
		p.setString(2, cs.getUrl());
		int result = p.executeUpdate();

		return result;
	}

	public static int addOnsiteCourse(Course cs) throws SQLException {
		String query = "INSERT INTO onsitecourse (CourseID, Location, Days, Time) values (?, ?, ?, ?); ";
		PreparedStatement p = CourseDAL.getConnection().prepareStatement(query);
		p.setInt(1, cs.getCourseID());
		p.setString(2, cs.getLocation());
		p.setString(3, cs.getDays());
		p.setTime(4, cs.getTime());
		int result = p.executeUpdate();

		return result;

	}

	public static int updateCourse(Course cs, int id) throws SQLException {
		String query = "UPDATE course SET CourseID=?, Title=?, Credits=?, DepartmentID=? WHERE CourseID=?; ";
		PreparedStatement p = CourseDAL.getConnection().prepareStatement(query);
		p.setInt(1, cs.getCourseID());
		p.setString(2, cs.getTitle());
		p.setInt(3, cs.getCredits());
		p.setInt(4, cs.getDepartmentID());
		p.setInt(5, id);
		int result = p.executeUpdate();

		return result;
	}

	public static int updateOnlineCourse(Course cs, int id) throws SQLException {
		String query = "UPDATE onlinecourse SET CourseID=?, url=? WHERE CourseID=?;";
		PreparedStatement p = CourseDAL.getConnection().prepareStatement(query);
		p.setInt(1, cs.getCourseID());
		p.setString(2, cs.getUrl());
		p.setInt(3, id);
		int result = p.executeUpdate();

		return result;
	}

	public static int updateOnsiteCourse(Course cs, int id) throws SQLException {
		String query = "UPDATE onsitecourse SET CourseID=?, Location=?, Days=?, Time=? WHERE CourseID=?;";
		PreparedStatement p = CourseDAL.getConnection().prepareStatement(query);
		p.setInt(1, cs.getCourseID());
		p.setString(2, cs.getLocation());
		p.setString(3, cs.getDays());
		p.setTime(4, cs.getTime());
		p.setInt(5, id);
		int result = p.executeUpdate();

		return result;
	}

	public static int deleteCourse(int id) throws SQLException {
		String query = "DELETE FROM course WHERE CourseID=?";
		PreparedStatement p = CourseDAL.getConnection().prepareStatement(query);
		p.setInt(1, id);
		int result = p.executeUpdate();

		return result;
	}

	public static int deleteOnlineCourse(int id) throws SQLException {
		String query = "DELETE FROM onlinecourse WHERE CourseID=?";
		PreparedStatement p = CourseDAL.getConnection().prepareStatement(query);
		p.setInt(1, id);
		int result = p.executeUpdate();

		return result;
	}

	public static int deleteOnsiteCourse(int id) throws SQLException {
		String query = "DELETE FROM onsitecourse WHERE CourseID=?";
		PreparedStatement p = CourseDAL.getConnection().prepareStatement(query);
		p.setInt(1, id);
		int result = p.executeUpdate();

		return result;
	}
	
//	public static void main(String[] args) {
//		ArrayList list;
//		try {
//			list = new CourseDAL().searchOnlineCourse("Com");
//			Object[][] asmInfo = new Object[list.size()][5];
//			for(int i = 0; i< list.size(); i++) {
//				System.out.println(asmInfo[i][0]);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
