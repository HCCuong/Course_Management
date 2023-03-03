package BLL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAL.AssignmentDAL;
import DTO.Assignment;

public class AssignmentBLL {
	private AssignmentDAL asmDAL;

	public AssignmentBLL() {
		asmDAL = new AssignmentDAL();
	}

	public List loadAsm(int page) throws SQLException {
		int numofrecords = 50;
		ArrayList list = asmDAL.readAssignment();
		int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;

        return list.subList(from, Math.min(to, size));
	}
	
	public List searchAsm(String Key, int page) throws SQLException{
		int numofrecords = 5;
		List list = asmDAL.searchAsm(Key);
		int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;

        return list.subList(from, Math.min(to, size));
	}

	public List readPerson() throws SQLException {
		ArrayList list = asmDAL.readLecture();
		return list;
	}

	public List readCourse() throws SQLException {
		ArrayList list = asmDAL.readCourse();
		return list;
	}

	public int addAsm(Assignment asm) throws SQLException {
		int result = asmDAL.addAsm(asm);
		return result;
	}
	
	public int updateAsm(Assignment asm, int personID, int courseID) throws SQLException{
		int result = asmDAL.updateAsm(asm, personID, courseID);
		return result;
	}

	public int deleteAsm(int idps, int idin) throws SQLException {
		int result = asmDAL.deleteAsm(idps, idin);
		return result;
	}

	public int readCourseID(String course) throws SQLException {
		int result = asmDAL.readCourseID(course);
		return result;
	}

	public int readPersonID(String firstname, String lastname) throws SQLException {
		int result = asmDAL.readPersonID(firstname, lastname);
		return result;
	}

	public int numOfAsmPage() throws SQLException{
		ArrayList list = asmDAL.readAssignment();
		double num = list.size();
        return (int) Math.ceil(num / 5);
	}
	
	public int numOfSearchPage(String key) throws SQLException{
		ArrayList list = asmDAL.searchAsm(key);
		double num = list.size();
        return (int) Math.ceil(num / 5);
	}
	
}
