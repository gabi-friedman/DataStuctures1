package finalProject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class CourseList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PriorityQueue<Course> allCourses;
	
	public CourseList(){
		allCourses = new PriorityQueue<Course>();
	}
	
	public void addCourse(Course course) throws DuplicateDataException{
		if(allCourses.contains(course)){
			throw new DuplicateDataException();
		}
		allCourses.add(course);
	}
	
	public void addCourse(String courseID, String courseName, Integer numCredits, String deptID) throws DuplicateDataException, MissingDataException{		
		Course course = new Course(courseID, courseName, numCredits, deptID);
		addCourse(course);
	}
	
	public Course findCourse(String courseID) throws NotFoundException{
		Iterator<Course> iter = allCourses.iterator();
		Course c;
		while(iter.hasNext()){
			c = iter.next();
			if(c.getCourseID().equals(courseID)){
				return c;
			}
		}
		throw new NotFoundException("Class x found");
	}
	
	public void removeCourse(String courseID) throws NotFoundException{
		allCourses.remove(findCourse(courseID));
	}
	
	public void modifyCourseName(String courseID, String newName) throws NotFoundException{
		Course c = findCourse(courseID);
		c.setCourseName(newName);		
	}

	public void modifyCourseCredits(String courseID, Integer credits) throws NotFoundException{
		Course c = findCourse(courseID);
		c.setNumCredits(credits);
	}
	
	public void modifyCourseDept(String courseID, String deptID) throws NotFoundException{ //InvalidDataException didnt need to be thrown
		Course c = findCourse(courseID);
		c.setDeptID(deptID);
	}
	
	public boolean containsCourseID(String courseID){
		boolean has = false;
		try {
			if(allCourses.contains(findCourse(courseID))){
				has = true;
			}
		} catch (NotFoundException e) {
			has = false;
		}
		return has;
	}
	
	public int numCourses(){
		return allCourses.size();
	}
	
	public String getAllInDept(String searchDept){
		ArrayList<Course> dpt = new ArrayList<Course>();
		Iterator<Course> iter = allCourses.iterator();
		Course c;
		while(iter.hasNext()){
			c = iter.next();
			if(c.getDeptID().equalsIgnoreCase(searchDept)){
				dpt.add(c);
			}
		}
		return dpt.toString();
	}
	
	public String getAllCourses(){
		ArrayList<Course> dpt = new ArrayList<Course>();
		Iterator<Course> iter = allCourses.iterator();
		while(iter.hasNext()){
			dpt.add(iter.next());
		}
		return dpt.toString();
	}	
	
	public String toString(){
		StringBuilder info = new StringBuilder();
		Iterator<Course> iter = allCourses.iterator();
		while(iter.hasNext()){
			info.append(iter.next().toString());
		}
		return info.toString();
	}
	
}
