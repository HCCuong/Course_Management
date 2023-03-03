package DTO;

import java.sql.Date;

public class Person {

	int personID;
	String firstName, lastName;
	Date hireDate, enrollmentDate;

	public Person() {
	}

	public Person(int personID, String firstName, String lastName, Date hireDate, Date enrollmentDate) {
		this.personID = personID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hireDate = hireDate;
		this.enrollmentDate = enrollmentDate;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Date getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

}
