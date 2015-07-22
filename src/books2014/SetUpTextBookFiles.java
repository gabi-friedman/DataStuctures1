package books2014;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SetUpTextBookFiles {
	static public void main(String[] args){
		//String isbn, courseID; these 3 variables r never used
		//Double cost; 
		
		JFileChooser fileChooser = new JFileChooser();
		JOptionPane.showMessageDialog(null,"choose text file that has textbook data");
		fileChooser.showOpenDialog(null);
		
		String textFileName = fileChooser.getSelectedFile().getPath();
		
		JOptionPane.showMessageDialog(null,"choose random file - provide name if file doesnt exist");
		fileChooser.showOpenDialog(null);
		String randomFileName = fileChooser.getSelectedFile().getPath();
		
		JOptionPane.showMessageDialog(null, "choose index file - provide name if file doesnt exist");
		fileChooser.showOpenDialog(null);
		String indexFileName = fileChooser.getSelectedFile().getPath();
		
		
		try{
			
			CollegeTextBooks textBooks = new CollegeTextBooks(textFileName,randomFileName, 10);
			//now store the index file
			ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(indexFileName));
			objectOut.writeObject(textBooks);
			objectOut.close();
			System.out.println("system set up successfully");
		}
		
		catch(FileNotFoundException notFound){
			System.out.println("cant find file - exiting");
			System.exit(1);
		}
		catch(InvalidDataException dataProblem){
			System.out.println("invalid data - cant process - exiting");
			System.exit(1);
		}
		catch(IOException io){
			System.out.println("cant store data in file - exiting");
			System.exit(1);
		}
		catch(DuplicateDataException dup){
			System.out.println("data file contains duplicate data - contact user");
			System.exit(1);
		}
	}
}