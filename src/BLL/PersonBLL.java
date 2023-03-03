package BLL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAL.PersonDAL;
import DTO.Person;

public class PersonBLL {

	PersonDAL psDal;

	public PersonBLL() {
		psDal = new PersonDAL();
	}

	public List loadPerson(int page) throws SQLException {
        int numofrecords = 100;
        ArrayList list = psDal.readPerson();
        int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;

        return list.subList(from, Math.min(to, size));
    }
	
	public List loadStudents(int page) throws SQLException {
		int numofrecords = 10;
		ArrayList list = psDal.readStudent();
		int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;

        return list.subList(from, Math.min(to, size));
    }
	
	public List loadInstructor(int page) throws SQLException {
		int numofrecords = 10;
		ArrayList list = psDal.readInstructor();
		int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;

        return list.subList(from, Math.min(to, size));
    }

	public int numOfPersonPage() throws SQLException {
    	ArrayList list = psDal.readPerson();
    	double num = list.size();
        return (int) Math.ceil(num / 10);
    }
    
    public int numOfStudentPage() throws SQLException {
    	ArrayList list = psDal.readStudent();
    	double num = list.size();
        return (int) Math.ceil(num / 10);
    }
    
    public int numOfInstructorPage() throws SQLException {
    	ArrayList list = psDal.readInstructor();
    	double num = list.size();
        return (int) Math.ceil(num / 10);
    }
	
	public int deletePerson(int id) throws SQLException {
		int result = psDal.deletePerson(id);
		return result;
	}

	public int addStudent(Person p) throws SQLException {
		int result = psDal.insertStudent(p);
		return result;
	}

	public int addInstructor(Person p) throws SQLException {
		int result = psDal.insertInstructor(p);
		return result;
	}

	public int updatePerson(Person p) throws SQLException {
		int result = psDal.updatePerson(p);
		return result;
	}

	public List searchPerson(String key, int page) throws SQLException{
		int numofrecords = 10;
		List list = psDal.searchPerson(key);
		int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;

        return list.subList(from, Math.min(to, size));
	}
	
	public int numOfSearchPage(String key) throws SQLException{
		List list = psDal.searchPerson(key);
		double num = list.size();
        return (int) Math.ceil(num / 10);
	}

}
