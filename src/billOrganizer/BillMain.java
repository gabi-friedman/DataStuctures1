package billOrganizer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class BillMain {
	private static Integer lastBillID = 0;
	
	@SuppressWarnings("null")
	public static void main(String[] args){
		//set up an ArrayList to keep track of all of the Bills
		ArrayList<Bill> allBills = new ArrayList<Bill>();
		Bill bill;
		Scanner console = new Scanner(System.in);
		int userChoice;
		String filename;
		String vendorName;
		double amtDue;
		GregorianCalendar dateDue = new GregorianCalendar();
		String type;
		int ui = 1;
		
		System.out.println("Welcome to the Bill Organizer");
		System.out.println("1: To record a Bill \n2: EXIT");
		ui = Integer.parseInt(console.nextLine());
		
		do{
			
			System.out.println("Choose: \n  1: Input from File\n  2: User Input");
			do{
				userChoice = Integer.parseInt(console.next());
			}while(userChoice != 1 && userChoice != 2);
	
			if(userChoice == 1){
				System.out.println("Enter filename");
				filename = console.nextLine();
				//for testing purposes
				//filename =  "bill";
				Scanner inputFile = new Scanner(filename);
				while(inputFile.hasNextLine()){				
					//bill = new Bill(++lastBillID, inputFile);
				}
			}
			else if(userChoice == 2){
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
				type = console.next();
				console.nextLine();
				bill = new Bill(++lastBillID, vendorName, amtDue, dateDue, type);
				allBills.add(bill);
			}
			System.out.println("Welcome to the Bill Organizer");
			System.out.println("1: To record a Bill \n2: EXIT");
			ui = Integer.parseInt(console.nextLine());
		} while (ui == 1);
		
		System.out.println("Printing by BillID (compareTo())");
		Collections.sort(allBills);
		for(int i = 0; i < allBills.size(); i++){
			System.out.println(allBills.get(i).toString());
		}
		System.out.println("Printing by Bill Amount");
		Collections.sort(allBills, new BillAmountComparator());
		for(int i = 0; i < allBills.size(); i++){
			System.out.println(allBills.get(i).toString());
		}
		System.out.println("Printing by Bill Type");
		Collections.sort(allBills, new BillTypeComparator());
		for(int i = 0; i < allBills.size(); i++){
			System.out.println(allBills.get(i).toString());
		}
		System.out.println("Printing by Bill Date");
		Collections.sort(allBills, new BillDateComparator());
		for(int i = 0; i < allBills.size(); i++){
			System.out.println(allBills.get(i).toString());
		}
		
		console.close();
	}


}
