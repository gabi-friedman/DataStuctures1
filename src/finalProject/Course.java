package finalProject;

import java.io.Serializable;

public class Course implements Serializable, Comparable<Course>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String courseID;
	private String courseName;
	private Integer numCredits;
	private String deptID;
	
	public Course(String courseID, String courseName, Integer numCredits, String deptID) throws MissingDataException{
		if(courseID == null || courseName == null || numCredits == null || deptID == null){
			throw new MissingDataException();
		}
		this.courseID = courseID;
		this.courseName = courseName;
		this.numCredits = numCredits;
		this.deptID = deptID;
	}
	
	public String getCourseID(){
		return this.courseID;
	}
	public String getCourseName(){
		return this.courseName;
	}
	public Integer getNumCredits(){
		return this.numCredits;
	}
	public String getDeptID(){
		return this.deptID;
	}
	public void setCourseName(String courseName){
		this.courseName = courseName;
	}
	public void setNumCredits(Integer numCredits){
		this.numCredits = numCredits;
	}
	public void setDeptID(String deptID){
		this.deptID = deptID;
	}
	
	public int compareTo(Course c){
		return this.courseID.compareTo(c.getCourseID());
	}
	
	public boolean equals(Object o){
		if(o instanceof Course){
			Course c = (Course)o;
			if(this.courseID.equals(c.courseID)){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}

	public String toString(){
		StringBuilder info = new StringBuilder();
		info.append("    Course ID:      " + courseID + "\n");
		info.append("       Course Name:    " + courseName + "\n");
		info.append("       Course Credits: " + numCredits + "\n");
		info.append("       Course Dept:    " + deptID + "\n");
		return info.toString();
	}
}
