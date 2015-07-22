package books2014;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CollegeText implements Comparable<CollegeText>{
	private String isbn;
	private String courseID;
	private Double cost;
	
	public CollegeText(String isbn, String courseID, Double cost)throws InvalidDataException{
		if(isbn==null || courseID==null || cost==null){
			throw new InvalidDataException();
		}
		else{
			if(isbn.length() != 13 || courseID.length() != 6){
				throw new InvalidDataException();
			}
			else{
				this.isbn=isbn;
				this.courseID=courseID;
				this.cost=cost;
			}
		}
	}
	
	public CollegeText(String randomFileName, Long filePosition)throws FileNotFoundException, IOException{
		RandomAccessFile random = new RandomAccessFile(new File(randomFileName), "rw");
		random.seek(filePosition);
		this.isbn =random.readUTF();
		this.courseID = random.readUTF();
		this.cost = random.readDouble();
		random.close();
		
		//read data from a RandomAccessFile
	}
	
	public Long writeToFile(String randomFileName)throws FileNotFoundException, IOException{
		RandomAccessFile random = new RandomAccessFile(new File(randomFileName), "rw");
		Long filePosition = random.length();
		random.seek(random.length());
		System.out.println("Getting ready to write a record at location " + filePosition);
		random.writeUTF(this.isbn);
		random.writeUTF(this.courseID);
		random.writeDouble(cost);
		random.close();
		return filePosition;
	}
	
	public void rewriteToFile(String randomFileName, Long fileLocation)throws FileNotFoundException, IOException{
		RandomAccessFile random = new RandomAccessFile(new File(randomFileName), "rw");
		random.seek(fileLocation);
		random.writeUTF(this.isbn);
		random.writeUTF(this.courseID);
		random.writeDouble(cost);
		random.close();
	}
	
	public Double getCost(){
		return cost;
	}
	
	public void setCost(Double cost){
		this.cost=cost;
	}
	
	public String getISBN(){
		return isbn;
	}
	
	public String getCourseID(){
		return courseID;
	}
	
	public int compareTo(CollegeText otherText){
	  	String isbn2 = otherText.getISBN();
	  	return this.isbn.compareTo(isbn2);
	}
	
	public String toString(){
		StringBuilder info = new StringBuilder();
		info.append("ISBN: ");
		info.append(isbn);
		info.append(" CourseID: ");
		info.append(courseID);
		info.append(" Cost: ");
		info.append(cost);
		return info.toString();
	}

	

}
