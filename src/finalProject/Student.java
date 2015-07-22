package finalProject;

import java.io.Serializable;
import java.util.Iterator;
import java.util.PriorityQueue;


public class Student implements Serializable, Comparable<Student>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer studentID;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zipcode;
	private Character gender;
	private String major;
	private Integer numberOfCredits;
	private Double gpa;
	
	private PriorityQueue<CompletedCourse> ccs;

	public Student(Integer studentID, String firstName, String lastName, String address, String city, String state, String zipcode, Character gender, String major) throws InvalidDataException{
		this(studentID, firstName, lastName, address, city, state, zipcode, gender, major,0,0.0);
	}

	public Student(Integer studentID, String firstName, String lastName, String address, String city, String state, String zipcode, Character gender, String major, Integer numberOfCredits, Double gpa) throws InvalidDataException{
		if(studentID == null || firstName == null || lastName == null || address == null || city == null || state == null || zipcode  == null || gender == null || major  == null || numberOfCredits == null || gpa == null){
			throw new InvalidDataException("You have entered null for a value.  \n\nRemember if you have no Credits or GPA enter 0.");
		}
		this.studentID = studentID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		if(state.length() != 2){
			throw new InvalidDataException();
		}
		this.state = state.toUpperCase();
		if(zipcode.length() != 5){
			throw new InvalidDataException();
		}
		this.zipcode = zipcode;
		if(gender != ('m') && gender != ('f') && gender != ('M') && gender != ('F')){
			throw new InvalidDataException();
		}
		this.gender = gender;
		this.major = major.toUpperCase();
		if(numberOfCredits < 0){
			throw new InvalidDataException();
		}
		this.numberOfCredits = numberOfCredits;
		if(gpa >= 0.0 && gpa <= 4.0){
			this.gpa = gpa; 
		}
		else {
			throw new InvalidDataException();
		}
		
		this.ccs = new PriorityQueue<CompletedCourse>(new YearCompletedComparator());
	}

	public void setStudentID(Integer studentID){
		this.studentID = studentID;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public void setCity(String city){
		this.city = city;
	}
	public void setState(String state) throws InvalidDataException{
		if(state.length() != 2){
			throw new InvalidDataException();
		}
		else {
			this.state = state.toUpperCase();
		}			
	}
	public void setZipcode(String zipcode) throws InvalidDataException{
		if(zipcode.length() != 5){
			throw new InvalidDataException();
		}
		else {
			this.zipcode = zipcode;
		}
	}
	public void setGender(Character gender) throws InvalidDataException{
		if(gender != ('m') && gender != ('f') && gender != ('M') && gender != ('F')){
			throw new InvalidDataException();
		}
		else{
			this.gender = gender;
		}
	}
	public void setMajor(String major){
		this.major = major.toUpperCase();
	}
	public void setNumberOfCredits(Integer numberOfCredits) throws InvalidDataException{
		if(numberOfCredits < 0){
			throw new InvalidDataException();
		}
		else{
			this.numberOfCredits = numberOfCredits;
		}
	}
	public void setGPA(Double gpa) throws InvalidDataException{
		if(gpa >= 0.0 && gpa <= 4.0){
			this.gpa = gpa; 
		}
		else {
			throw new InvalidDataException();
		}
	}
	public Integer getStudentID(){
		return this.studentID;
	}
	public String getFirstName(){
		return this.firstName;
	}
	public String getLastName(){
		return this.lastName;
	}
	public String getAddress(){
		return this.address;
	}
	public String getCity(){
		return this.city;
	}
	public String getState(){
		return this.state;
	}
	public String getZipcode(){
		return this.zipcode;
	}
	public Character getGender(){
		return this.gender;
	}
	public String getMajor(){
		return this.major;
	}
	public Integer getNumberOfCredits(){
		this.numberOfCredits = getCreditsEarned();
		return this.numberOfCredits;
	}
	public Double getGPA(){
		this.gpa = calcGPA(); 
		return this.gpa;
	}
	
	public int getCreditsEarned(){
		CompletedCourse c;
		Integer totalCredits = 0;
		Iterator<CompletedCourse> iter = ccs.iterator();
		while(iter.hasNext()){
			c = iter.next();
			totalCredits += c.getNumCredits();
		}
		return totalCredits;
	}
	
	public double calcGPA(){
		CompletedCourse c;
		Integer totalCredits = getCreditsEarned();
		Integer credits = 0;
		Double grade = 0.0;
		Double allGradeVals = 0.0;
		Iterator<CompletedCourse> iter = ccs.iterator();
		while(iter.hasNext()){
			c = iter.next();
			credits = c.getNumCredits();
			grade = c.getGrade().getGpaVal();
			allGradeVals+=(grade*credits);
		}
		
		Double GPA = allGradeVals/totalCredits;
		return GPA;		
	}

	public void	addCompletedCourse(CompletedCourse c) throws DuplicateDataException{
		if(ccs.contains(c)){
			throw new DuplicateDataException("You have already completed this course.");
		}
		ccs.add(c);
	}
	
	public CompletedCourse findCC(String courseID) throws NotFoundException{
		Iterator<CompletedCourse> iter = ccs.iterator();
		CompletedCourse c;
		while(iter.hasNext()){
			c = iter.next();
			if(c.getCourseID().equals(courseID)){
				return c;
			}
		}
		throw new NotFoundException("The Student never took the CompletedCourse youre looking for.");
	
	}
	
	public void removeCompletedCourse(String courseID) throws NotFoundException{
		CompletedCourse c = findCC(courseID);
		ccs.remove(c);
	}
	
	public void changeCCSem(String courseID, Semester sem) throws NotFoundException{
		CompletedCourse c = findCC(courseID);
		c.setSemCompleted(sem);
	}
	
	public void changeCCYr(String courseID, Integer yrCompleted) throws NotFoundException{
		CompletedCourse c = findCC(courseID);
		c.setYrCompleted(yrCompleted);
	}
	
	public void changeCCGrade(String courseID, Grade grade) throws NotFoundException{
		CompletedCourse c = findCC(courseID);
		c.setGrade(grade);
	}
	
	public String listAllCCs(){
		StringBuilder b = new StringBuilder();
		Iterator<CompletedCourse> iter = ccs.iterator();
		CompletedCourse c;
		while(iter.hasNext()){
			c = iter.next();
			b.append(c);
		}
		return b.toString();
	}
	
	public String allCCsFromSem(Semester sem, Integer yr){
		StringBuilder b = new StringBuilder();
		Iterator<CompletedCourse> iter = ccs.iterator();
		CompletedCourse c;
		while(iter.hasNext()){
			c = iter.next();
			if(c.getSemCompleted().equals(sem) && c.getYrCompleted().equals(yr)){
				b.append(c);
			}
		}
		return b.toString();
	}
	
	public String allCCsFromYr(Integer yr){
		StringBuilder b = new StringBuilder();
		Iterator<CompletedCourse> iter = ccs.iterator();
		CompletedCourse c;
		while(iter.hasNext()){
			c = iter.next();
			if(c.getYrCompleted().equals(yr)){
				b.append(c);
			}
		}
		return b.toString();
	}
	
	public String allCCsGradeIs(Grade g){
		StringBuilder b = new StringBuilder();
		Iterator<CompletedCourse> iter = ccs.iterator();
		CompletedCourse c;
		while(iter.hasNext()){
			c = iter.next();
			if(c.getGrade().equals(g)){
				b.append(c);
			}
		}
		return b.toString();
	}
	
	public String allCCsGradeGreater(Grade g){
		StringBuilder b = new StringBuilder();
		Iterator<CompletedCourse> iter = ccs.iterator();
		CompletedCourse c;
		while(iter.hasNext()){
			c = iter.next();
			// TODO Auto-generated catch block
			if(c.getGrade().getGpaVal() >= (g.getGpaVal())){ 
				b.append(c);
			}
		}
		return b.toString();
	}

	public int compareTo(Student s){
		return this.studentID.compareTo(s.getStudentID());  //can use compareTo bec. id is stored in an Integer
	}

	public boolean equals(Object obj){
		if(obj instanceof Student){
			Student stud = (Student)obj;
			if(this.studentID.equals(stud.getStudentID()))
				return true;
			else 
				return false;
		}
		else
			return false;
	}

	public String toString(){
		StringBuilder studentinfo = new StringBuilder();
		studentinfo.append("Student Info for StudentID #"+studentID+"\n");
		studentinfo.append("     Student First Name:    " + firstName+"\n");
		studentinfo.append("     Student Last Name:     " + lastName+"\n");
		
		studentinfo.append("     Student Address:       " + address+"\n");
		studentinfo.append("                            " + city + ", " + state + " " + zipcode+"\n");
		studentinfo.append("     Student Gender:        " + gender+"\n");
		studentinfo.append("     Student Major:         " + major+"\n");
		if(numberOfCredits != null && gpa != null){
			studentinfo.append("     Student Total Credits: " + numberOfCredits+"\n");
			studentinfo.append("     Student GPA:           " + gpa+"\n");
		}
		return studentinfo.toString();

	}
}

