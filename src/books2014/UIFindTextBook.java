package books2014;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class UIFindTextBook {

	public static void main(String[] args){
		
		//step a. get the index file read into RAM
		//need the file name! use a JFileChooser
		
		JFileChooser fileChooser = new JFileChooser();
		JOptionPane.showMessageDialog(null, "Select random access file");
		fileChooser.showOpenDialog(null);
		String randomFileName = fileChooser.getSelectedFile().getPath();
		
		JOptionPane.showMessageDialog(null, "Select index file name");
		fileChooser.showOpenDialog(null);
		String indexFileName = fileChooser.getSelectedFile().getPath();
		
		System.out.println("Random File Name " + randomFileName);
		System.out.println("Index File Name " + indexFileName);
		
		try{
			ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(indexFileName));
			CollegeTextBooks index = (CollegeTextBooks)inStream.readObject();
			System.out.println("Index file was read in successfully!");
			
			System.out.println("What ISBN are you looking for?");
			Scanner keyboard = new Scanner(System.in);
			String isbn = keyboard.next();
			Long location = index.getLocation(isbn);
			CollegeText coltext = new CollegeText(randomFileName, location);
			System.out.println(coltext.toString());
			keyboard.close();
			inStream.close();

		}
		catch(FileNotFoundException notFound){
			System.out.println("file not found, exiting application");
		}
		catch(IOException io){
			System.out.println("io exception, exiting application");
		}
		catch(ClassNotFoundException ex){
			System.out.println("class inconsistency, exiting application");
		}
		catch(NotFoundException e){
			System.out.println("not found, exiting application");
		}
		
		
		
	}
	
}
