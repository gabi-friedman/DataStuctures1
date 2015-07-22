package billOrganizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class BillOrganizerMain {

	public static void main(String[] args) throws Exception{
		//set up an ArrayList to keep track of all of the Bills
		Bill bill;
		Integer lastBillID = 1;
		Scanner console = new Scanner(System.in);
		String filename;
		String vendorName;
		double amtDue;
		GregorianCalendar dateDue = new GregorianCalendar();
		String type;
		int ui = 1;
		BillOrganizer<Bill> bo = new BillOrganizer<Bill>();
		ObjectOutputStream objOutStr;
		ObjectInputStream objInStr;
		DecimalFormat f = new DecimalFormat("$0.00");


		do{
			System.out.println("Welcome to the Bill Organizer");
			System.out.println(""
					+ "			1: To restore Bills from file \n"
					+ "			2: To record a Bill (user input) \n"
					+ "			3: Get total amount due for all Bills \n"
					+ "			4: View Bills sorted by Date \n"
					+ "			5: View Bills sorted by BillType \n"
					+ "			6: View Bills sorted by Amount \n"
					+ "			7: Pay Bills sorted by Date \n"
					+ "			8: Pay Bills sorted by BillType \n"
					+ "			9: Pay Bills sorted by Amount \n"
					+ "			10. Pay Bill with BillID \n"
					+ "			11. Save BillOrganizer with Object Serialization \n"
					+ "			12. Restore BillOrganizer with Object Serialization \n"
					+ "			13: EXIT");
			try{
			ui = Integer.parseInt(console.nextLine());
			}catch(NumberFormatException e){ 
				//The program kept throwing this exception. I know its because of int/String input.
				//After debugging for an hour I couldnt figure it out so I put this in
				ui = Integer.parseInt(console.nextLine());
			}
			switch (ui){
			case 1:{ //1: To record a Bill file
				System.out.println("Enter filename");
				filename = console.nextLine();
				//for testing purposes
				//filename =  "bills.txt";
				Scanner inputFile = new Scanner(new File(filename));
				while(inputFile.hasNextLine()){	
					String n = inputFile.nextLine();
					bill = new Bill(lastBillID, n);
					bo.insert(bill);
					lastBillID++;
				}

				////print all bills to see what the file has entered
				LinkedListIterator<Bill> iter = bo.sortedIter();
				while(iter.hasNext()){
					System.out.println(iter.next());
				}
				break;
			}
			case 2:{//2: To restore Bills from ui
				System.out.println("Enter Vendor Name");
				vendorName = console.next();
				System.out.println("Enter Amount Due");
				amtDue = Double.parseDouble(console.next());
				System.out.println("Enter Date Due Month");
				dateDue.set(Calendar.MONTH, Integer.parseInt(console.next()));
				System.out.println("Enter Date Due Day");
				dateDue.set(Calendar.DAY_OF_MONTH, Integer.parseInt(console.next()));
				System.out.println("Enter Date Due Year");
				dateDue.set(Calendar.YEAR, Integer.parseInt(console.next()));
				System.out.println("Enter BillType - CLOTHING,EDUCATION,FOOD,GROCERIES,PHONE,TRAVEL,UTILITIES");
				type = console.next().toUpperCase();
				console.nextLine();
				bill = new Bill(lastBillID, vendorName, amtDue, dateDue, type);
				bo.insert(bill);
				lastBillID++;
				System.out.println("Bill inserted");
				break;
			}
			case 3:{//3: Get total amount due for all Bills \n"
				double totalAmtDue = 0;
				LinkedListIterator<Bill> iter = bo.sortedIter();
				while(iter.hasNext()){
					totalAmtDue += iter.next().getAmtDue();
				}
				
				System.out.println("Total amount due: "+ f.format(totalAmtDue));
				break;
			}
			
			case 4:{//4: View Bills sorted by Date \n"
				LinkedListIterator<Bill> iter = bo.iteratorByDate();
				while(iter.hasNext()){
					System.out.println(iter.next());
				}				
				break;
			}
			
			case 5:{//5: View Bills sorted by BillType \n"
				LinkedListIterator<Bill> iter = bo.iteratorByType();
				while(iter.hasNext()){
					System.out.println(iter.next());
				}	
				break;
			}
			
			case 6:{//6: View Bills sorted by Amount \n"
				LinkedListIterator<Bill> iter = bo.iteratorByAmount();
				while(iter.hasNext()){
					System.out.println(iter.next());
				}	
				break;
			}
			
			case 7:{//7: Pay Bills sorted by Date \n"
					
				LinkedListIterator<Bill> iter = bo.iteratorByDate();
				if(iter.hasNext()){
					Bill next = iter.next();
					System.out.println(next);
					System.out.println("This bill of "+ next.getAmtDue() +" is being paid");
					bo.payNextBill(next, BillCriteria.BILLDUEDATE);
					System.out.println("This bill is paid");
				}	
				else{
					System.out.println("Woohoo!! you have paid all of your Bills!");
				}
				break;
			}
			
			case 8:{//8: Pay Bills sorted by BillType \n"
				LinkedListIterator<Bill> iter = bo.iteratorByType();
				if(iter.hasNext()){
					Bill next = iter.next();
					System.out.println(next);
					System.out.println("This bill of "+ next.getAmtDue() +" is being paid");
					bo.payNextBill(next, BillCriteria.BILLTYPE);
					System.out.println("This bill is paid");
				}	
				else{
					System.out.println("Woohoo!! you have paid all of your Bills!");
				}
				break;
			}
			
			case 9:{//9: Pay Bills sorted by Amount \n"
				LinkedListIterator<Bill> iter = bo.iteratorByAmount();
				if(iter.hasNext()){
					Bill next = iter.next();
					System.out.println(next);
					System.out.println("This bill of "+ next.getAmtDue() +" is being paid");
					bo.payNextBill(next, BillCriteria.BILLAMOUNT);
					System.out.println("This bill is paid");
				}	
				else{
					System.out.println("Woohoo!! you have paid all of your Bills!");
				}
				break;
			}
			
			case 10:{//10. Pay Bill with BillID \n"
				System.out.println("Enter billID to pay");
				int temp = Integer.parseInt(console.next());
				try{
					Bill b = bo.findByID(temp);
					System.out.println(b);
					System.out.println("This bill of "+ b.getAmtDue() +" is being paid");
					bo.payNextBill(b, BillCriteria.BILLAMOUNT);
					System.out.println("This bill is paid");
				}
				catch(DataNotFoundException e){
					System.out.println("DataNotFoundException Thrown");
				}
				break;
			}
			
			case 11:{ //11. Save BillOrganizer with Object Serialization \n"
				try {
					String filenameOutput = JOptionPane.showInputDialog("Enter the File to OUTPUT the information to");
					objOutStr = new ObjectOutputStream(new FileOutputStream(filenameOutput));
					objOutStr.writeObject(bo);
					objOutStr.close();
				} 
				catch (IOException e) {
					System.out.println("IOException Thrown");
				}	

				break;
			}

			case 12:{//12. Restore BillOrganizer with Object Serialization
				try {
					String filenameInput = JOptionPane.showInputDialog("Enter the File to INPUT the information from");
					objInStr = new ObjectInputStream(new FileInputStream(filenameInput));
					bo = (BillOrganizer<Bill>) objInStr.readObject();
					objInStr.close();
				} catch (FileNotFoundException e) {
					System.out.println("FileNotFoundException Thrown");
				} 
				catch (IOException e) {
					System.out.println("IOException Thrown");
				}
				catch (ClassNotFoundException e) {
					System.out.println("ClassNotFoundException Thrown");
				}
				break;
			}

			}
		} while (ui != 13);


		console.close();
	}


}
