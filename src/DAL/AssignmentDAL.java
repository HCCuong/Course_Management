package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.Person;
import DTO.Assignment;
import DTO.Course;

public class AssignmentDAL extends MyDatabaseManager {

	private static Assignment Asm;
	private static Person ps;
	private static Course cs;
	private static Connection c;

	public AssignmentDAL() {
		super();
		this.connectDB();
	}

	public ArrayList readAssignment() throws SQLException {
		ArrayList asm = new ArrayList();
		String query = "SELECT Lastname, Firstname, Title, cs.CourseID, ps.PersonID "
				+ "FROM courseinstructor ct,person ps , course cs "
				+ "WHERE ct.PersonID = ps.PersonID AND ct.CourseID = cs.CourseID";
		ResultSet rs = this.doReadQuery(query);
		if (rs != null) {
			while (rs.next()) {
				Asm = new Assignment();
				Asm.setCourseID(rs.getInt("CourseID"));
				Asm.setPersonID(rs.getInt("PersonID"));
				Asm.setFirstname(rs.getString("Firstname"));
				Asm.setLastname(rs.getString("Lastname"));
				Asm.setCourse(rs.getString("Title"));

				asm.add(Asm);
			}
		}
		return asm;
	}

	public ArrayList readLecture() throws SQLException {
		ArrayList person = new ArrayList();
		String query = "SELECT * FROM person WHERE HireDate IS NOT NULL";
		ResultSet rs = this.doReadQuery(query);
		if (rs != null) {
			while (rs.next()) {
				ps = new Person();
				ps.setPersonID(rs.getInt("PersonID"));
				ps.setLastName(rs.getString("Lastname"));
				ps.setFirstName(rs.getString("Firstname"));
				ps.setHireDate(rs.getDate("HireDate"));

				person.add(ps);
			}
		}
		return person;
	}

	public ArrayList readCourse() throws SQLException {
		ArrayList course = new ArrayList();
		String query = "SELECT title FROM course";
		ResultSet rs = this.doReadQuery(query);
		if (rs != null) {
			while (rs.next()) {
				cs = new Course();
				cs.setTitle(rs.getString("title"));

				course.add(cs);
			}
		}
		return course;
	}

	public int readPersonID(String firstname, String lastname) throws SQLException {
		int a = 0;
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

	public static int addAsm(Assignment asm) throws SQLException {
		String query = "INSERT INTO courseinstructor (CourseID, PersonID) values (?, ?)";
		PreparedStatement p = AssignmentDAL.getConnection().prepareStatement(query);
		p.setInt(1, asm.getCourseID());
		p.setInt(2, asm.getPersonID());
		int result = p.executeUpdate();

		return result;
	}
	
	public static int updateAsm(Assignment asm, int personID, int courseID) throws SQLException{
		String query = "UPDATE courseinstructor SET CourseID = ?, PersonID=? WHERE CourseID = ? AND PersonID=? ";
		PreparedStatement p = AssignmentDAL.getConnection().prepareStatement(query);
		p.setInt(1, asm.getCourseID());
		p.setInt(2, asm.getPersonID());
		p.setInt(3, courseID);
		p.setInt(4, personID);
		int result = p.executeUpdate();

		return result;
	}

	public static int deleteAsm(int idps, int idin) throws SQLException {
		String query = "DELETE FROM courseinstructor WHERE PersonID=? AND CourseID=? ";
		PreparedStatement p = AssignmentDAL.getConnection().prepareStatement(query);
		p.setInt(1, idps);
		p.setInt(2, idin);
		int result = p.executeUpdate();

		return result;
	}
	
	public ArrayList searchAsm(String Key) throws SQLException{
		ArrayList asm = new ArrayList();
		String query = "SELECT cs.Title, ps.Firstname, ps.Lastname "
				+ "FROM (courseinstructor c INNER JOIN person ps ON c.PersonID =  ps.PersonID) INNER JOIN course cs ON c.CourseID = cs.CourseID "
				+ "WHERE cast(Title as CHAR) like ?"
				+ "OR cast(Firstname as CHAR) like ?"
				+ "OR cast(Lastname as CHAR) like ?";
		PreparedStatement p = AssignmentDAL.getConnection().prepareStatement(query);
		p.setString(1, "%" + Key + "%");
		p.setString(2, "%" + Key + "%");
		p.setString(3, "%" + Key + "%");
		ResultSet rs = p.executeQuery();
		if(rs != null) {
			while(rs.next()){
				Asm = new Assignment();
				Asm.setFirstname(rs.getString("Firstname"));
				Asm.setLastname(rs.getString("Lastname"));
				Asm.setCourse(rs.getString("Title"));
				
				asm.add(Asm);
			}
		}
		return asm;
	}
}
