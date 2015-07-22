package student;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import finalProject.DuplicateDataException;
import finalProject.NotFoundException;
import finalProject.Student;
import finalProject.StudentList;

public class UIStudent{
	public static void main(String[] args) throws finalProject.InvalidDataException{

		Student student;
		StudentList students = new StudentList();
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

		ObjectOutputStream objOutStr;
		ObjectInputStream objInStr;

		Integer menuChoice = null;


		do{
			menuChoice = Integer.parseInt(JOptionPane.showInputDialog("Please choose an option:\n"
					+ "										1: Add a student\n"
					+ "										2: Remove a student\n"
					+ "										3: Modify student last name\n"
					+ "										4: Modify student address \n"
					+ "										5: Modify student major \n"
					+ "										6: Modify student gpa \n"
					+ "										7: Modify student credits \n"					
					+ "										8: Display student information\n"
					+ "										9: List all students' information\n"
					+ "										10: List students of a specific major\n"
					+ "										11: List students who have earned a certain number of credits\n"
					+ "										12: Store StudentList to file using Object Serialization\n"
					+ "										13: Restore StudentList from a disk file using Object Serialization\n"
					+ "										14: EXIT"));

			switch(menuChoice){
			case 1:{ //1: Add a student
				studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the student you want to add"));
				firstName = JOptionPane.showInputDialog("Enter the FIRST NAME of the student you want to add");
				lastName = JOptionPane.showInputDialog("Enter the LAST NAME of the student you want to add");
				address = JOptionPane.showInputDialog("Enter the ADDRESS of the student you want to add");
				city = JOptionPane.showInputDialog("Enter the CITY of the student you want to add");
				state = JOptionPane.showInputDialog("Enter the STATE of the student you want to add");
				zipcode = JOptionPane.showInputDialog("Enter the ZIPCODE of the student you want to add");
				gender = JOptionPane.showInputDialog("Enter the GENDER of the student you want to add").charAt(0);
				major = JOptionPane.showInputDialog("Enter the MAJOR of the student you want to add");
				numberOfCredits = Integer.parseInt(JOptionPane.showInputDialog("Enter the NUMBER OF CREDITS of the student you want to add"));
				gpa = Double.parseDouble(JOptionPane.showInputDialog("Enter the GPA of the student you want to add"));


				student = new Student(studentID, firstName, lastName, address, city, state, zipcode, gender, major, numberOfCredits, gpa);

				try {
					students.addStudent(student);
					JOptionPane.showMessageDialog(null,"New Student has been added with ID# " + studentID);
				} 
				catch (DuplicateDataException e) {
					JOptionPane.showMessageDialog(null, e.getMessage() + " DuplicateDataException", "EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}

			case 2:{ //2: Remove a student
				studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the student you want to remove"));
				try {
					students.removeStudent(studentID);
					JOptionPane.showMessageDialog(null,"The Student with ID# " + studentID + " has been removed");
				} catch (NotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage()  + " NotFoundException", "EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case 3:{ //3: Modify student last name
				try {
					studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the student you want Modify the LAST NAME for"));
					String oldLastName = JOptionPane.showInputDialog("Enter the old last name");
					String newLastName = JOptionPane.showInputDialog("Enter the new last name");
					students.modifyStudentLastName(studentID, newLastName);
					JOptionPane.showMessageDialog(null,"The Last Name of ID#" + studentID + " has been modified from " + oldLastName + " to " + newLastName);
				} 
				catch (NotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case 4:{ //4: Modify student address \n"
				try {
					studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the student you want Modify the ADDRESS for"));
					address = JOptionPane.showInputDialog("Enter street address");
					city = JOptionPane.showInputDialog("Enter city");
					state = JOptionPane.showInputDialog("Enter state");
					zipcode = JOptionPane.showInputDialog("Enter zipcode");
					students.modifyStudentAddress(studentID, address, city, state, zipcode);
					JOptionPane.showMessageDialog(null,"The Address of ID#" + studentID + " has been modified to " + address + "\n" + city + ", " + state + zipcode);
				} 
				catch (NotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case 5:{ //5: Modify student major
				try {
					studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the student you want Modify the MAJOR for"));
					String oldMajor;
					oldMajor = students.findStudent(studentID).getMajor();
					String newMajor = JOptionPane.showInputDialog("Enter the MAJOR you would like to switch to");
					students.modifyStudentMajor(studentID, newMajor);
					JOptionPane.showMessageDialog(null,"The Major of ID#" + studentID + " has been modified from " + oldMajor + " to " + newMajor);
				} 
				catch (NotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case 6:{ //6: Modify student gpa 
				try {
					studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the student you want Modify the GPA for"));
					Double oldGPA;
					oldGPA = students.findStudent(studentID).getGPA();
					Double newGPA = Double.parseDouble(JOptionPane.showInputDialog("Enter the GPA you would like to switch to"));
					students.modifyStudentGPA(studentID, newGPA);

					JOptionPane.showMessageDialog(null,"The GPA of ID#" + studentID + " has been modified from " + oldGPA + " to " + newGPA);
				} 
				catch (NotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case 7:{ //7: Modify student credits \n"					
				try {
					studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the student you want Modify the AMOUNT OF CREDITS for"));
					Integer oldNum;
					oldNum = students.findStudent(studentID).getNumberOfCredits();
					Integer newNum = Integer.parseInt(JOptionPane.showInputDialog("Enter the AMOUNT OF CREDITS you would like to switch to"));
					students.modifyStudentCredits(studentID, newNum);
					JOptionPane.showMessageDialog(null,"The Amount of Credits of ID#" + studentID + " has been modified from " + oldNum + " to " + newNum);
				}
				catch (NotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case 8:{ //8: Display student information\n"
				studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the student you want to find"));
				try {
					Student s = students.findStudent(studentID);
					JOptionPane.showMessageDialog(null, s.toString());
				} catch (NotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case 9:{ //9: List all students' information\n"
				JOptionPane.showMessageDialog(null, students.toString());
				break;
			}
			case 10:{ //10: List students of a specific major\n"
				String searchMajor = JOptionPane.showInputDialog("Enter the MAJOR you would like to search for");
				String studentsInMajor = students.getStudentsInMajor(searchMajor).toString();
				JOptionPane.showMessageDialog(null, studentsInMajor.toString());
				break;
			}
			case 11:{ //11: List students who have earned a certain number of credits\n"
				Integer numCredits = Integer.parseInt(JOptionPane.showInputDialog("Enter the AMOUNT OF CREDITS you would like to search for"));
				String studentsWithCredits = students.getStudentsWithCredits(numCredits).toString();
				JOptionPane.showMessageDialog(null, studentsWithCredits.toString());
				break;
			}
			case 12:{ //12: Store StudentList to file using Object Serialization\n"
				try {
					String filename = JOptionPane.showInputDialog("Enter the File to OUTPUT the information to");
					objOutStr = new ObjectOutputStream(new FileOutputStream(filename));
					objOutStr.writeObject(students);
					objOutStr.close();
				} 
				catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case 13:{ //13: Restore StudentList from a disk file using Object Serialization\n"
				try {
					String filename = JOptionPane.showInputDialog("Enter the File to INPUT the information from");
					objInStr = new ObjectInputStream(new FileInputStream(filename));
					students = (StudentList) objInStr.readObject();
					objInStr.close();
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
				} 
				catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);
				}
				catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "EXCEPTION THROWN", JOptionPane.ERROR_MESSAGE);	
				}
				break;
			}
			}//switch
		}while(menuChoice > 0 && menuChoice < 14); //14: EXIT
		
		JOptionPane.showMessageDialog(null, "Thank you for using the Student UI! \n Have a good day!");	
		
	}//main
}//class
