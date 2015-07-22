package finalProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

public class UIStudentRecords{

	public static void main(String[] args){
		Integer studentID;
		String firstName;
		String lastName;
		Character gender;
		String major;
		String address;
		String city;
		String state;
		String zipcode;	
		Integer numberOfCredits;
		Double gpa;

		String courseID;
		String courseName;
		Integer numCredits;
		String deptID;

		//Course course;
		Semester semCompleted;
		Integer yrCompleted;
		Grade grade;

		ObjectInputStream objInStr;
		ObjectOutputStream objOutStr;

		StudentRecords sr = new StudentRecords();
		Integer mainmenu = null;
		Integer sMenu = null;
		Integer cMenu = null;
		Integer ccMenu = null;
		Integer oMenu = null;

		do{
			mainmenu = Integer.parseInt(JOptionPane.showInputDialog("Please choose a menu:"
					+ "\n     1: For Student Options"
					+ "\n     2: For Course Options"
					+ "\n     3: For CompletedCourse Options"
					+ "\n     4: To Save or Retrieve to/from a File (Object Serialization)"
					+ "\n     5: EXIT"));
			

			switch(mainmenu){
			case 1:{ //Student Options
				sMenu = Integer.parseInt(JOptionPane.showInputDialog("Please choose an option:"
						+ "\n     1:  Add a student"
						+ "\n     2:  Remove a student"
						+ "\n     3:  Modify student last name"
						+ "\n     4:  Modify student address "
						+ "\n     5:  Modify student major"				
						+ "\n     6:  Display student information"
						+ "\n     7:  List all students' information"
						+ "\n     8:  List students of a specific major"
						+ "\n     9:  List students who have earned a certain number of credits"
						+ "\n     10: EXIT to Main Menu"));
				switch(sMenu){
				case 1:{ //1: Add a student
					studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the student you want to add"));
					firstName = JOptionPane.showInputDialog("Enter the FIRST NAME of the student you want to add");
					lastName = JOptionPane.showInputDialog("Enter the LAST NAME of the student you want to add");
					address = JOptionPane.showInputDialog("Enter the ADDRESS of the student you want to add");
					city = JOptionPane.showInputDialog("Enter the CITY of the student you want to add");
					state = JOptionPane.showInputDialog("Enter the STATE of the student you want to add");
					zipcode = JOptionPane.showInputDialog("Enter the ZIPCODE of the student you want to add");
					gender = JOptionPane.showInputDialog("Enter the GENDER of the student you want to add \n M or F").charAt(0);
					major = JOptionPane.showInputDialog("Enter the MAJOR of the student you want to add");
					numberOfCredits = Integer.parseInt(JOptionPane.showInputDialog("Enter the NUMBER OF CREDITS of the student you want to add.  \n\nIf the Student has no Credits put in 0"));
					gpa = Double.parseDouble(JOptionPane.showInputDialog("Enter the GPA of the student you want to add \n\nIf the Student has no GPA put in 0"));

					try {
						sr.addStudent(new Student(studentID, firstName, lastName, address, city, state, zipcode, gender, major, numberOfCredits, gpa));
						JOptionPane.showMessageDialog(null,"New Student has been added with ID# " + studentID);
					} 
					catch (DuplicateDataException e) {
						JOptionPane.showMessageDialog(null, e.getMessage() + " DuplicateDataException", "EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					} 
					catch (InvalidDataException e) {
						JOptionPane.showMessageDialog(null, e.getMessage() + " InvalidDataException", "EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}	
				case 2:{ //2: Remove a student
					studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the student you want to remove"));
					try {
						sr.removeStudent(studentID);
						JOptionPane.showMessageDialog(null,"The Student with ID# " + studentID + " has been removed");
					} catch (NotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage()  + " NotFoundException", "EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
				case 3:{ //3: Modify student last name
					try {
						studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the student you want Modify the LAST NAME for"));
						String newLastName = JOptionPane.showInputDialog("Enter the new last name");
						sr.modifyStudentLastName(studentID, newLastName);
						JOptionPane.showMessageDialog(null,"The Last Name of ID#" + studentID + " has been changed to " + newLastName);
					} 
					catch (NotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "NOT FOUND EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
				case 4:{ //4: Modify student address "
					try {
						studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the student you want Modify the ADDRESS for"));
						address = JOptionPane.showInputDialog("Enter street address");
						city = JOptionPane.showInputDialog("Enter city");
						state = JOptionPane.showInputDialog("Enter state");
						zipcode = JOptionPane.showInputDialog("Enter zipcode");
						sr.modifyStudentAddress(studentID, address, city, state, zipcode);
						JOptionPane.showMessageDialog(null,"The Address of ID#" + studentID + " has been modified to " + address + "" + city + ", " + state + zipcode);
					} 
					catch (NotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "NOT FOUND EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					} catch (InvalidDataException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "INVALID DATA EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
				case 5:{ //5: Modify student major
					try {
						studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the student you want Modify the MAJOR for"));
						String newMajor = JOptionPane.showInputDialog("Enter the MAJOR you would like to switch to");
						sr.modifyStudentMajor(studentID, newMajor);
						JOptionPane.showMessageDialog(null,"The Major of ID#" + studentID + " has been modified to " + newMajor);
					} 
					catch (NotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "NOT FOUND EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
				case 6:{ //8: Display student information"
					studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the student you want to find"));
					try {
						JOptionPane.showMessageDialog(null, sr.getStudentInfo(studentID));
					} catch (NotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "NOT FOUND EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
				case 7:{ //9: List all students' information"
					JOptionPane.showMessageDialog(null, sr.getAllStudentInfo());
					break;
				}
				case 8:{ //10: List students of a specific major"
					String searchMajor = JOptionPane.showInputDialog("Enter the MAJOR you would like to search for");
					JOptionPane.showMessageDialog(null, sr.getStudentsInMajor(searchMajor));
					break;
				}
				case 9:{ //11: List students who have earned a certain number of credits"
					numCredits = Integer.parseInt(JOptionPane.showInputDialog("Enter the AMOUNT OF CREDITS you would like to search for"));
					JOptionPane.showMessageDialog(null, sr.getStudentsWithCredits(numCredits));
					break;
				}
				}//switch student
				break;
			}
			case 2:{ //Course Options
				cMenu = Integer.parseInt(JOptionPane.showInputDialog("Please choose an option from the menu: "
						+ "\n     1:  Add a course "
						+ "\n     2:  Remove a course "
						+ "\n     3:  Modify Course Name"
						+ "\n     4:  Modify Course Credits"
						+ "\n     5:  Modify Course Department "
						+ "\n     6:  Display Course information "
						+ "\n     7:  List ALL courses information "
						+ "\n     8:  List courses offered by a specific department "
						+ "\n     9:  EXIT "));
				switch(cMenu){
				case 1:{ //1 - Add a course 
					try {
						courseID = JOptionPane.showInputDialog("Enter new CourseID");
						courseName = JOptionPane.showInputDialog("Enter new CourseName");
						numCredits = Integer.parseInt(JOptionPane.showInputDialog("Enter new Course Credit value"));
						deptID = JOptionPane.showInputDialog("Enter DepartmentID");
						sr.addCourse(courseID, courseName, numCredits, deptID);
						JOptionPane.showMessageDialog(null,"New Course has been added with ID# " + courseID);
					}
					catch (DuplicateDataException e){
						JOptionPane.showMessageDialog(null, e.getMessage(), "DUPLICATE DATA EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}
					catch (MissingDataException e){
						JOptionPane.showMessageDialog(null, e.getMessage(), "MISSING DATA EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
				case 2:{ //2 - Remove a course
					courseID = JOptionPane.showInputDialog("Enter courseID you want to remove");
					try {
						sr.removeCourse(courseID);
						JOptionPane.showMessageDialog(null,"The courseID " + courseID + " has been removed");
					} 
					catch (NotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "NOT FOUND EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
				case 3:{// 5. modify course name
					try {
						courseID = JOptionPane.showInputDialog("Enter courseID to modify name of");
						String newName = JOptionPane.showInputDialog("Enter the NEW courseName");
						sr.modifyCourseName(courseID, newName);
						JOptionPane.showMessageDialog(null,"courseID " + courseID + " has been modified to " + newName);
					} 
					catch (finalProject.NotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "NOT FOUND EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
				case 4:{// 5. modify course number of credits
					try {
						courseID = JOptionPane.showInputDialog("Enter courseID to modify credit value of");
						numCredits = Integer.parseInt(JOptionPane.showInputDialog("Enter the new numCredits"));
						sr.modifyCourseCredits(courseID, numCredits);
						JOptionPane.showMessageDialog(null,"courseID " + courseID + " credit value has been modified to " + numCredits);
					} 
					catch (finalProject.NotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "NOT FOUND EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
				case 5:{// 5. modify course department
					try {
						courseID = JOptionPane.showInputDialog("Enter courseID to modify department of");
						deptID = JOptionPane.showInputDialog("Enter the new departmentID");
						sr.modifyCourseDept(courseID, deptID);
						JOptionPane.showMessageDialog(null,"courseID " + courseID + " department has been changed to " + deptID);
					} 
					catch (finalProject.NotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "NOT FOUND EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
				case 6:{ //6	Display single Course information
					try {
						courseID = JOptionPane.showInputDialog("Enter courseID to view");
						JOptionPane.showMessageDialog(null, sr.getCourse(courseID).toString());
					} catch (finalProject.NotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "NOT FOUND EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
				case 7:{ //7 - List ALL courses information
					JOptionPane.showMessageDialog(null,"All Courses: " + sr.getAllCourses());
					break;
				}
				case 8:{ //8 - List courses offered by a specific department
					String searchDept = JOptionPane.showInputDialog("Enter the Department you want to view classes for");	
					JOptionPane.showMessageDialog(null, sr.getAllCoursesInDept(searchDept));
					break;
				}
				}
				break;
			}
			case 3:{ //CompletedCourse Options
				ccMenu = Integer.parseInt(JOptionPane.showInputDialog("Please choose an option:"
						+ "\n     1:  Add a CompletedCourse to a Student"
						+ "\n     2:  Remove a CompletedCourse from a Student"
						+ "\n     3:  Modify Semester of a CompletedCourse"
						+ "\n     4:  Modify Year of a CompletedCourse"
						+ "\n     5:  Modify Grade of a CompletedCourse"
						+ "\n     6:  List all of a Student's Completed Courses"
						+ "\n     7:  List a Student's Completed Courses for a Specific Semester"
						+ "\n     8:  List a Student's Completed Courses for a Specific Year"
						+ "\n     9:  List a Student's Completed Courses that have a specific grade"
						+ "\n     10: List a Student's Completed Courses that have higher than specific grade"
						+ "\n     11: EXIT to Main Menu"));
				switch(ccMenu){
				case 1:{ //1: Add a CompletedCourse to a Student"
					try {
						studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter the studentID of the student you want to add a CompletedCourse to"));
						courseID = JOptionPane.showInputDialog("Enter the courseID of the CompletedCourse. \nMake sure that you have created a new Course with this courseID previous to adding a CompletedCourse. \nIf this CourseID does not yet exist it will not allow you to enter a CompletedCourse.");
						Course co = sr.getCourse(courseID);
						semCompleted = Semester.valueOf(JOptionPane.showInputDialog("Enter the Semester of the CompletedCourse: FALL, SPRING, SUMMER1, SUMMER2").toUpperCase());
						yrCompleted = Integer.parseInt(JOptionPane.showInputDialog("Enter the YEAR of the CompletedCourse."));
						grade = Grade.valueOf(JOptionPane.showInputDialog("Enter the Grade of the CompletedCourse: A,B,C,D,F").toUpperCase());

						CompletedCourse cc;

						cc = new CompletedCourse(co,semCompleted,yrCompleted,grade,studentID);

						sr.addCompletedCourse(studentID, cc);
						JOptionPane.showMessageDialog(null,"The CompletedCourse ID#" + courseID + " has been added to Student ID#" + studentID + ".");
					} catch (MissingDataException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "MISSING DATA EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					} catch (NotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "NOT FOUND EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					} catch (DuplicateDataException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "DUPLICATE DATA EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
				case 2:{ //2: Remove a CompletedCourse from a Student"			
					try {
						studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter the studentID of the student you want to remove a CompletedCourse from"));
						courseID = JOptionPane.showInputDialog("Enter the courseID of the CompletedCourse.");
						sr.removeCompletedCourse(courseID, studentID);
						JOptionPane.showMessageDialog(null,"The CompletedCourse ID#" + courseID + " has been removed from Student ID#" + studentID + ".");
					}catch (NotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "NOT FOUND EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
				case 3:{ //3: Modify Semester of a CompletedCourse"
					try {
						studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter the studentID of the student you want to modify a CompletedCourse of"));
						courseID = JOptionPane.showInputDialog("Enter the courseID of the CompletedCourse to modify.");
						semCompleted = Semester.valueOf(JOptionPane.showInputDialog("Enter the Semester to change to: FALL, SPRING, SUMMER1, SUMMER2").toUpperCase());
						sr.editCCSemester(studentID, courseID, semCompleted);
						JOptionPane.showMessageDialog(null,"The CompletedCourse ID#" + courseID + " for Student ID#" + studentID + " was modified to be have been taken in the "+ semCompleted +" Semester.");
					}catch (NotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "NOT FOUND EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
				case 4:{ //4: Modify Year of a CompletedCourse"
					try {
						studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter the studentID of the student you want to modify a CompletedCourse of"));
						courseID = JOptionPane.showInputDialog("Enter the courseID of the CompletedCourse to modify.");
						yrCompleted = Integer.parseInt(JOptionPane.showInputDialog("Enter the year the course should have been completed in.").toUpperCase());
						sr.editCCYr(studentID, courseID, yrCompleted);
						JOptionPane.showMessageDialog(null,"The CompletedCourse ID#" + courseID + " for Student ID#" + studentID + " was modified to have been taken in "+ yrCompleted +".");
					}catch (NotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "NOT FOUND EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
				case 5:{ //5: Modify Grade of a CompletedCourse"
					try {
						studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter the studentID of the student you want to modify a CompletedCourse of"));
						courseID = JOptionPane.showInputDialog("Enter the courseID of the CompletedCourse to modify.");
						grade = Grade.valueOf(("Enter the Grade the Student should have acheived: A, B, C, D, or F").toUpperCase());
						sr.editCCGrade(studentID, courseID, grade);
						JOptionPane.showMessageDialog(null,"The CompletedCourse ID#" + courseID + " for Student ID#" + studentID + " was modified to have gotten the grade "+ grade +".");
					}catch (NotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "NOT FOUND EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
				case 6:{ //6: List all of a Student's Completed Courses"
					try {
						studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter the studentID of the student you want to modify a CompletedCourse of"));
						JOptionPane.showMessageDialog(null,"The CompletedCourses for Student ID#" + studentID + ": " + sr.getAllCCs(studentID));
					} catch (NotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "NOT FOUND EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
				case 7:{ //7: List a Student's Completed Courses for a Specific Semester, Year"
					try {
						studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter the studentID of the student you want to find CompletedCourses for"));
						semCompleted = Semester.valueOf(JOptionPane.showInputDialog("Enter the Semester of the CompletedCourse: FALL, SPRING, SUMMER1, SUMMER2").toUpperCase());
						yrCompleted = Integer.parseInt(JOptionPane.showInputDialog("Enter the YEAR of the CompletedCourse."));

						JOptionPane.showMessageDialog(null,"The CompletedCourses for Student ID#" + studentID + " in "+semCompleted+", "+yrCompleted+":" + sr.getAllCCsFromSem(studentID,semCompleted,yrCompleted));
					} catch (NotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "NOT FOUND EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}

					break;
				}
				case 8:{ //8: List a Student's Completed Courses for a Specific Year"
					try {
						studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter the studentID of the student you want to find CompletedCourses for"));
						yrCompleted = Integer.parseInt(JOptionPane.showInputDialog("Enter the YEAR of the CompletedCourse."));
						JOptionPane.showMessageDialog(null,"The CompletedCourses for Student ID#" + studentID + " for "+yrCompleted+":" + sr.getAllCCsFromYr(studentID,yrCompleted));
					} catch (NotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "NOT FOUND EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
				case 9:{ //9: List a Student's Completed Courses that have a specific grade"
					try{
						studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter the studentID of the student you want to view information for"));
						grade = Grade.valueOf(("Enter the Grade to view: A, B, C, D, or F").toUpperCase());
						JOptionPane.showMessageDialog(null,"The Student ID#" + studentID + " has accheived a(n) "+ grade +" in the following: " + sr.getGradesEqual(studentID, grade));
					}catch (NotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "NOT FOUND EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
				case 10:{ //10: List a Student's Completed Courses that have higher than specific grade"
					try{
						studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter the studentID of the student you want to view information for"));
						grade = Grade.valueOf(("Enter the Grade to view: A, B, C, D, or F").toUpperCase());
						JOptionPane.showMessageDialog(null,"The Student ID#" + studentID + " has above accheived a(n) "+ grade +" in the following: " + sr.getGradesGreater(studentID, grade));
					}catch (NotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "NOT FOUND EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}

				}
				break;
			}
			case 4:{ //Object Serialization Options
				oMenu = Integer.parseInt(JOptionPane.showInputDialog("Please choose an option:"
						+ "\n     1: Store Student Records using ObjectSerialization"
						+ "\n     2: Restore Student Records using ObjectSerialization"
						+ "\n     3: EXIT to Main Menu"));
				switch(oMenu){
				case 1:{ //1: Store Student Records using ObjectSerialization
					try {
						String filename = JOptionPane.showInputDialog("Enter the File to OUTPUT the information to");
						objOutStr = new ObjectOutputStream(new FileOutputStream(filename));
						objOutStr.writeObject(sr);
						objOutStr.close();
					} 
					catch (IOException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "IO EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
				case 2:{//2: Restore Student Records using ObjectSerialization"
					try {
						String filename = JOptionPane.showInputDialog("Enter the File to INPUT the information from");
						objInStr = new ObjectInputStream(new FileInputStream(filename));
						sr = (StudentRecords) objInStr.readObject();
						objInStr.close();
					} 
					catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "FILE NOT FOUND EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					} 
					catch (IOException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "IO EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
					}
					catch (ClassNotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "CLASS NOT FOUND EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);	
					}
					break;
				}
				}
				break;
			}//4
			}
		}while(mainmenu > 0 && mainmenu <= 4);
		
		JOptionPane.showMessageDialog(null, "Thank you for using StudentRecords with the Regstrar! \n See you again soon!", "GOODBYE!", JOptionPane.INFORMATION_MESSAGE);
		
		
		
	}
}

