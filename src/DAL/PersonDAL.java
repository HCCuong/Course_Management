package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DTO.Assignment;
import DTO.Person;

public class PersonDAL extends MyDatabaseManager {

	Date date = new Date();
	java.sql.Date sqlDate = new java.sql.Date(date.getTime());

	public PersonDAL() {
		super();
		this.connectDB();
	}

	public ArrayList readPerson() throws SQLException {
		String query = "SELECT * FROM Person";
		ResultSet rs = this.doReadQuery(query);
		ArrayList list = new ArrayList();
		if (rs != null) {
			int i = 1;
			while (rs.next()) {
				Person p = new Person();
				p.setPersonID(rs.getInt("PersonID"));
				p.setFirstName(rs.getString("FirstName"));
				p.setLastName(rs.getString("LastName"));
				p.setHireDate(rs.getDate("HireDate"));
				p.setEnrollmentDate(rs.getDate("EnrollmentDate"));
				list.add(p);
			}
		}
		return list;
	}

	public ArrayList readStudent() throws SQLException {
		String query = "SELECT * FROM Person WHERE EnrollmentDate >0";
		ResultSet rs = this.doReadQuery(query);
		ArrayList list = new ArrayList();
		if (rs != null) {
			int i = 1;
			while (rs.next()) {
				Person p = new Person();
				p.setPersonID(rs.getInt("PersonID"));
				p.setFirstName(rs.getString("FirstName"));
				p.setLastName(rs.getString("LastName"));
				p.setEnrollmentDate(rs.getDate("EnrollmentDate"));
				list.add(p);
			}
		}
		return list;
	}

	public ArrayList<Person> readInstructor() throws SQLException {
		String query = "SELECT * FROM Person WHERE HireDate >0";
		ResultSet rs = this.doReadQuery(query);
		ArrayList list = new ArrayList();
		if (rs != null) {
			int i = 1;
			while (rs.next()) {
				Person p = new Person();
				p.setPersonID(rs.getInt("PersonID"));
				p.setFirstName(rs.getString("FirstName"));
				p.setLastName(rs.getString("LastName"));
				p.setHireDate(rs.getDate("HireDate"));
				list.add(p);
			}
		}
		return list;
	}

	public Person getStudent(int personID) throws SQLException {

		String query = "SELECT * FROM Person WHERE EnrollmentDate >0 AND PersonID = ? ";

		PreparedStatement ps = this.getConnection().prepareStatement(query);
		ps.setInt(1, personID);
		ResultSet rs = ps.executeQuery();

		Person p = new Person();
		if (rs != null) {
			int i = 1;

			while (rs.next()) {

				p.setPersonID(rs.getInt("PersonID"));
				p.setFirstName(rs.getString("FirstName"));
				p.setLastName(rs.getString("LastName"));
				p.setEnrollmentDate(java.sql.Date.valueOf(rs.getString("EnrollmentDate")));
			}
		}
		return p;

	}

	public Person getInstructor(int personID) throws SQLException {

		String query = "SELECT * FROM Person WHERE HireDate >0 AND PersonID = ? ";

		PreparedStatement ps = this.getConnection().prepareStatement(query);
		ps.setInt(1, personID);
		ResultSet rs = ps.executeQuery();

		Person p = new Person();
		if (rs != null) {
			int i = 1;

			while (rs.next()) {

				p.setPersonID(rs.getInt("PersonID"));
				p.setFirstName(rs.getString("FirstName"));
				p.setLastName(rs.getString("LastName"));
				p.setHireDate(java.sql.Date.valueOf(rs.getString("HireDate")));
			}
		}
		return p;

	}

	public static int updatePerson(Person p) throws SQLException {
		String query = "Update Person SET FirstName =? , LastName =? WHERE PersonID = ?;";
		PreparedStatement ps = PersonDAL.getConnection().prepareStatement(query);
		ps.setString(1, p.getFirstName());
		ps.setString(2, p.getLastName());
		ps.setInt(3, p.getPersonID());
		int result = ps.executeUpdate();
		return result;
	}

	public int insertStudent(Person p) throws SQLException {
		String query = "Insert Person (FirstName, LastName, EnrollmentDate) VALUES (?, ?, ?)";
		PreparedStatement ps = this.getConnection().prepareStatement(query);
		ps.setString(1, p.getFirstName());
		ps.setString(2, p.getLastName());
		// ps.setString(3, p.getEnrollmentDate().toString());
		ps.setDate(3, sqlDate);
		int result = ps.executeUpdate();
		return result;
	}

	public int insertInstructor(Person p) throws SQLException {
		String query = "Insert Person (FirstName, LastName, HireDate) VALUES (?, ?, ?)";
		PreparedStatement ps = this.getConnection().prepareStatement(query);
		ps.setString(1, p.getFirstName());
		ps.setString(2, p.getLastName());
		// ps.setString(3, p.getHireDate().toString());
		ps.setDate(3, sqlDate);
		int result = ps.executeUpdate();
		return result;
	}

	public int deletePerson(int personID) throws SQLException {
		String query = "DELETE FROM Person WHERE PersonID = ?";
		PreparedStatement p = this.getConnection().prepareStatement(query);
		p.setInt(1, personID);
		int result = p.executeUpdate();

		return result;
	}
	
	public ArrayList searchPerson(String Key) throws SQLException{
		ArrayList person = new ArrayList();
		String query = "SELECT * FROM Person "
				+ "WHERE cast(Firstname as CHAR) like ?"
				+ "OR cast(Lastname as CHAR) like ?";
		PreparedStatement ps = PersonDAL.getConnection().prepareStatement(query);
		ps.setString(1, "%" + Key + "%");
		ps.setString(2, "%" + Key + "%");
		ResultSet rs = ps.executeQuery();
		if(rs != null) {
			while(rs.next()){
				Person p = new Person();
				p.setPersonID(rs.getInt("PersonID"));
				p.setFirstName(rs.getString("FirstName"));
				p.setLastName(rs.getString("LastName"));
				p.setHireDate(rs.getDate("HireDate"));
				p.setEnrollmentDate(rs.getDate("EnrollmentDate"));
				person.add(p);
			}
		}
		return person;
	}

}
