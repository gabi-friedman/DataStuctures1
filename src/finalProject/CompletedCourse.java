package finalProject;

import java.io.Serializable;

public class CompletedCourse extends Course implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Course course;
	private Semester semCompleted;
	private Integer yrCompleted;
	private Grade grade;
	private Integer studentID;

	public CompletedCourse(String courseID, String courseName, Integer numCredits, String deptID, Semester semCompleted, Integer yrCompleted, Grade grade, Integer studentID) throws MissingDataException {
		super(courseID, courseName, numCredits, deptID);
		if(semCompleted == null || yrCompleted == null || grade == null || studentID == null){
			throw new MissingDataException();
		}
		this.semCompleted = semCompleted;
		this.yrCompleted = yrCompleted;
		this.grade = grade;
		this.studentID = studentID;
	}

	public CompletedCourse(Course c, Semester semCompleted, Integer yrCompleted, Grade grade, Integer studentID) throws MissingDataException {
		super(c.getCourseID(), c.getCourseName(), c.getNumCredits(), c.getDeptID());
		if(semCompleted == null || yrCompleted == null || grade == null || studentID == null){
			throw new MissingDataException();
		}
		this.semCompleted = semCompleted;
		this.yrCompleted = yrCompleted;
		this.grade = grade;
		this.studentID = studentID;
	}

	public void setCourse(Course course) {
		if(course != null){
			this.course = course;
		}
	}
	public void setSemCompleted(Semester semCompleted) {
		if(semCompleted != null){
			this.semCompleted = semCompleted;
		}
	}
	public void setYrCompleted(Integer yrCompleted) {
		if(yrCompleted != null){
			this.yrCompleted = yrCompleted;
		}
	}
	public void setGrade(Grade grade) {
		if(grade != null){
			this.grade = grade;
		}
	}
	public void setStudentID(Integer studentID) {
		if(studentID != null){
			this.studentID = studentID;
		}
	}	
	public Course getCourse() {
		return course;
	}
	public Semester getSemCompleted() {
		return semCompleted;
	}
	public Integer getYrCompleted() {
		return yrCompleted;
	}
	public Grade getGrade() {
		return grade;
	}
	public Integer getStudentID() {
		return studentID;
	}

	public int compareTo(CompletedCourse c){
		//return super.compareTo(c);
		return this.getCourseID().compareTo(c.getCourseID());
	}

	public boolean equals(Object o){
		return super.equals(o);
	}

	public String toString(){
		StringBuilder build = new StringBuilder();
		build.append(super.toString());
		build.append("       Course Completed : \n");
		build.append("              Semester Completed:    " + semCompleted + "\n");
		build.append("              Year Completed    :    " + yrCompleted + "\n");
		build.append("              StudentID         :    " + studentID + "\n");
		build.append("              Grade Acheived    :    " + grade + "\n");
		return build.toString();
	}



}
