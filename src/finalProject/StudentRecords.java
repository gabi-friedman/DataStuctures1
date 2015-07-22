package finalProject;

import java.io.Serializable;

public class StudentRecords  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StudentList sl;
	private CourseList cl;

	public StudentRecords(){
		sl = new StudentList();
		cl = new CourseList();
	}

	//student methods
	public void addStudent(Student s) throws DuplicateDataException{
		sl.addStudent(s);
	}

	public void removeStudent(Integer studentID) throws NotFoundException{
		sl.removeStudent(studentID);
	}

	public void modifyStudentLastName(Integer studentID, String newLastName) throws NotFoundException{
		sl.modifyStudentLastName(studentID, newLastName);
	}

	public void modifyStudentMajor(Integer studentID, String major) throws NotFoundException{
		sl.modifyStudentMajor(studentID, major);
	}

	public String getStudentInfo(Integer studentID) throws NotFoundException{
		return sl.findStudent(studentID).toString();
	}

	public String getAllStudentInfo(){
		return sl.getAllStudentsInfo();
	}

	public String getStudentsInMajor(String searchMajor){
		return sl.getStudentsInMajor(searchMajor);
	}

	public String getStudentsWithCredits(Integer numCredits){
		return sl.getStudentsWithCredits(numCredits);
	}

	public Double getStudentGPA(Integer studentID) throws NotFoundException{
		return sl.findStudent(studentID).getGPA();
	}

	public Integer getTotalStudentCredits(Integer studentID) throws NotFoundException{
		return sl.findStudent(studentID).getNumberOfCredits();
	}

	public void modifyStudentAddress(Integer studentID, String address, String city, String state, String zipcode) throws NotFoundException, InvalidDataException {
		sl.modifyStudentAddress(studentID, address, city, state, zipcode);;
	}

	//completed course methods
	public void addCompletedCourse(Integer studentID, CompletedCourse c) throws NotFoundException, DuplicateDataException{
		if(cl.containsCourseID(c.getCourseID())){
			sl.addCompletedCourse(studentID, c);
		}
	}

	public void removeCompletedCourse(String courseID, Integer studentID) throws NotFoundException{
		sl.findStudent(studentID).removeCompletedCourse(courseID);
	}

	public void editCCSemester(Integer studentID, String courseID, Semester sem) throws NotFoundException{
		sl.modifyCCSem(studentID, courseID, sem);
	}

	public void editCCYr(Integer studentID, String courseID, Integer year) throws NotFoundException{
		sl.modifyCCYr(studentID, courseID, year);
	}

	public void editCCGrade(Integer studentID, String courseID, Grade grade) throws NotFoundException{
		sl.modifyCCGrade(studentID, courseID, grade);
	}

	public String getAllCCs(Integer studentID) throws NotFoundException{
		return sl.getAllCCs(studentID);
	}

	public String getAllCCsFromSem(Integer studentID, Semester sem, Integer yr) throws NotFoundException{
		return sl.getAllCCsFromSem(studentID, sem, yr);
	}

	public String getAllCCsFromYr(Integer studentID, Integer yr) throws NotFoundException{
		return sl.getAllCCsFromYr(studentID, yr);
	}

	public String getGradesEqual(Integer studentID, Grade g) throws NotFoundException{
		return sl.getGradesEqual(studentID, g);
	}

	public String getGradesGreater(Integer studentID, Grade g) throws NotFoundException{
		return sl.getGradesGreater(studentID, g);
	}

	//course methods
	public Course getCourse(String courseID) throws NotFoundException{
		return cl.findCourse(courseID);
	}

	public void addCourse(Course c) throws DuplicateDataException{
		cl.addCourse(c);
	}
	
	public void addCourse(String courseID, String courseName, Integer numCredits, String deptID) throws DuplicateDataException, MissingDataException {
		cl.addCourse(courseID, courseName, numCredits, deptID);
	}
	
	public void removeCourse(String courseID) throws NotFoundException{
		cl.removeCourse(courseID);
	}

	public void modifyCourseName(String courseID, String newName) throws NotFoundException {
		cl.modifyCourseName(courseID, newName);
	}

	public void modifyCourseCredits(String courseID, Integer numCredits) throws NotFoundException {
		cl.modifyCourseCredits(courseID, numCredits);
	}
	
	public void modifyCourseDept(String courseID, String deptID) throws NotFoundException {
		cl.modifyCourseDept(courseID, deptID);
	}

	public String getAllCourses() {
		return cl.getAllCourses();
	}
	
	public String getAllCoursesInDept(String deptID){
		return cl.getAllInDept(deptID);
	}
}
