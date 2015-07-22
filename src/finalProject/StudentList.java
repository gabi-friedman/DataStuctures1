package finalProject;


import java.io.Serializable;
import java.util.Iterator;
import java.util.PriorityQueue;

public class StudentList implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PriorityQueue<Student> allStudents;
	
	public StudentList(){
		allStudents = new PriorityQueue<Student>();
		//this automatically uses the Students compareTo() which compares Students based on ID
	}
	public int getNumStudents(){
		return allStudents.size();
	}
	
	public void addStudent(Student s)throws DuplicateDataException{
		if(allStudents.contains(s)){
			throw new DuplicateDataException("This student is already in the StudentList");
		}
		allStudents.add(s);
	}
	
	public void addStudent(Integer studentID, String firstName, String lastName, String address, String city, String state, String zipcode, Character gender, String major) throws InvalidDataException, DuplicateDataException{
		Student s = new Student(studentID, firstName, lastName, address, city, state, zipcode, gender, major);
		addStudent(s);
	}
	
	public void addStudent(Integer studentID, String firstName, String lastName, String address, String city, String state, String zipcode, Character gender, String major, Integer numberOfCredits, Double gpa) throws InvalidDataException, DuplicateDataException{
		Student s = new Student(studentID, firstName, lastName, address, city, state, zipcode, gender, major, numberOfCredits, gpa);
		addStudent(s);
	}	
	
	public Student findStudent(Integer studentID) throws NotFoundException{
		Iterator<Student> iter = allStudents.iterator();
		Student s;
		while(iter.hasNext()){
			s = iter.next();
			if(s.getStudentID() == studentID){
				return s;
			}
		}
		throw new NotFoundException();
	}
	public void removeStudent(Integer studentID)throws NotFoundException{
		allStudents.remove(findStudent(studentID));
	}
	
	public boolean containsStudentID(Integer studentID){
		boolean has = false;
		try {
			if(allStudents.contains(findStudent(studentID))){
				has = true;
			}
		} catch (NotFoundException e) {
			has = false;
		}
		return has;
	}
	
	public void modifyStudentLastName(Integer studentID, String newLastName)throws NotFoundException{
		Student s = findStudent(studentID);
		s.setLastName(newLastName);		
	}
	public void modifyStudentAddress(Integer studentID, String street, String city, String state, String zip) throws NotFoundException, InvalidDataException{
		Student s = findStudent(studentID);
		s.setAddress(street);
		s.setCity(city);
		s.setState(state);
		s.setZipcode(zip);
	}
	public void modifyStudentGPA(Integer studentID, Double gpa) throws NotFoundException, InvalidDataException{
		Student s = findStudent(studentID);
		s.setGPA(gpa); 
	}
	public void modifyStudentMajor(Integer studentID, String major) throws NotFoundException{
		Student s = findStudent(studentID);
		s.setMajor(major.toUpperCase());
	}
	
	public void modifyStudentCredits(Integer studentID, Integer amountOfCredit)throws NotFoundException, InvalidDataException{
		Student s = findStudent(studentID);
		s.setNumberOfCredits(amountOfCredit); 
	}
	
	public String getAllStudentsInfo(){
		StringBuilder all = new StringBuilder();
		Iterator<Student> iter = allStudents.iterator();
		Student s;
		while(iter.hasNext()){
			s = iter.next();
			all.append(s);
		}
		return all.toString();
	}
	
	public String getStudentsInMajor(String searchMajor){ 	
		StringBuilder major = new StringBuilder();
		Iterator<Student> iter = allStudents.iterator();
		Student s;
		while(iter.hasNext()){
			s = iter.next();
			if(s.getMajor().equalsIgnoreCase(searchMajor)){
				major.append(s);
			}
		}
		return major.toString();
	}
	
	public String getStudentsWithCredits(Integer numCredits){
		StringBuilder credits = new StringBuilder();
		Iterator<Student> iter = allStudents.iterator();
		Student s;
		while(iter.hasNext()){
			s = iter.next();
			if(s.getNumberOfCredits().equals(numCredits)){
				credits.append(s);
			}
		}
		return credits.toString();
	}
	
	public void addCompletedCourse(Integer studentID, CompletedCourse c) throws NotFoundException, DuplicateDataException{
		Student s = findStudent(studentID);
		s.addCompletedCourse(c);
	}
	
	public void modifyCCSem(Integer studentID, String courseID, Semester sem) throws NotFoundException{
		Student s = findStudent(studentID);
		s.changeCCSem(courseID, sem);
	}
	
	public void modifyCCYr(Integer studentID, String courseID, Integer yr) throws NotFoundException{
		Student s = findStudent(studentID);
		s.changeCCYr(courseID, yr);
	}
	
	public void modifyCCGrade(Integer studentID, String courseID, Grade grade) throws NotFoundException{
		Student s = findStudent(studentID);
		s.changeCCGrade(courseID, grade);
	}
	
	public String getAllCCs(Integer studentID) throws NotFoundException{
		Student s = findStudent(studentID);
		return s.listAllCCs();
	}
	
	public String getAllCCsFromSem(Integer studentID, Semester sem, Integer yr) throws NotFoundException{
		Student s = findStudent(studentID);
		return s.allCCsFromSem(sem, yr);
	}
	
	public String getAllCCsFromYr(Integer studentID, Integer yr) throws NotFoundException{
		Student s = findStudent(studentID);
		return s.allCCsFromYr(yr);
	}
	
	public String getGradesEqual(Integer studentID, Grade g) throws NotFoundException{
		Student s = findStudent(studentID);
		return s.allCCsGradeIs(g);
	}
	
	public String getGradesGreater(Integer studentID, Grade g) throws NotFoundException{
		Student s = findStudent(studentID);
		return s.allCCsGradeGreater(g);
	}
	
	public Double getGPA(Integer studentID) throws NotFoundException{
		Student s = findStudent(studentID);
		return s.getGPA();
	}
	
	public Integer getTotalCredits(Integer studentID) throws NotFoundException{
		Student s = findStudent(studentID);
		return s.getNumberOfCredits();
	}
	
	public String toString(){
		StringBuilder info = new StringBuilder();
		Iterator<Student> iter = allStudents.iterator();
		while(iter.hasNext()){
			info.append(iter.next().toString());
		}
		return info.toString();		
	}
}
