package Diet;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MainLog { 
	@SuppressWarnings("null")
	public static void main(String[] args){

		int ui;
		GregorianCalendar date = new GregorianCalendar();
		int month;
		int day;
		int year;
		int cals;
		double miles;
		double phone;
		LogEntry le;
		PriorityQueue<LogEntry> aQ;
		double avg;
		double total;

		DailyLog daily = new DailyLog();

		System.out.println("Welcome to Daily Log!");
		System.out.println("MENU:\n"
				+"    1. add entry\n"
				+"    2. list entries by Date\n"
				+"    3. list entries by Calories\n"
				+"    4. list entries by Miles\n"
				+"    5. list entries by Phone Time\n"
				+"    6. get average and total Calories per day\n"
				+"    7. get average and total Miles per day\n"
				+"    8. get average and total Phone Time per day\n"
				+"    9. look at a Date's entry\n"
				+"    10. EXIT");

		Scanner console = new Scanner(System.in);
		ui = Integer.parseInt(console.next());
		do{
			switch(ui){
			case 1:{ //1. add entry
				System.out.println("Enter Log Month");
				month = Integer.parseInt(console.next());
				date.set(Calendar.MONTH, month);
				System.out.println("Enter Log Day");
				day = Integer.parseInt(console.next());
				date.set(Calendar.DAY_OF_MONTH, day);
				System.out.println("Enter Log Year");
				year = Integer.parseInt(console.next());
				date.set(Calendar.YEAR, year);
				System.out.println("Enter Calories Eaten");
				cals = Integer.parseInt(console.next());
				System.out.println("Enter Miles Walked");
				miles = Double.parseDouble(console.next());
				System.out.println("Enter Phone Time");
				phone = Double.parseDouble(console.next());

				le = new LogEntry(date,cals,miles,phone);

				daily.addLog(le);
				System.out.println("Log entered");

				break;
			}
			case 2:{ //2. list entries by Date
				int num = 1;
				aQ = daily.getEntriesByDate();
				while(aQ.iterator().hasNext()){
					System.out.println(num +": "+aQ.iterator().next());
				}
				num++;
				break;
			}
			case 3:{ //3. list entries by Calories
				int num = 1;
				aQ = daily.getEntriesByCals();
				while(aQ.iterator().hasNext()){
					System.out.println(num +": "+aQ.iterator().next());
				}
				num++;
				break;
			}
			case 4:{ //4. list entries by Miles
				int num = 1;
				aQ = daily.getEntriesByMiles();
				while(aQ.iterator().hasNext()){
					System.out.println(num +": "+aQ.iterator().next());
				}
				num++;
				break;
			}
			case 5:{ //5. list entries by Phone Time
				int num = 1;
				aQ = daily.getEntriesByMins();
				while(aQ.iterator().hasNext()){
					System.out.println(num +": "+aQ.iterator().next());
				}
				num++;
				break;
			}
			case 6:{ //6. get average and total Calories per day
				avg = daily.getAvgCals();
				total = daily.getTotalCals();

				System.out.println("Average Calories:  "+avg+" calories");
				System.out.println("Total Calories:    "+total+" calories");
				break;
			}
			case 7:{ //7. get average and total Miles per day
				avg = daily.getAvgMiles();
				total = daily.getTotalMiles();

				System.out.println("Average Miles:  "+avg+" miles");
				System.out.println("Total Miles:    "+total+" miles");
				break;
			}
			case 8:{ //8. get average and total Phone Time per day
				avg = daily.getAvgPhone();
				total = daily.getTotalPhone();

				System.out.println("Average Phone Time:  "+avg+" minutes");
				System.out.println("Total Phone Time:    "+total+" minutes");
				break;
			}
			case 9:{ //9. look at a Date's entry
				System.out.println("Enter Lookup Month");
				date.set(Calendar.MONTH, Integer.parseInt(console.nextLine()));
				System.out.println("Enter Lookup Day");
				date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(console.nextLine()));
				System.out.println("Enter Lookup Year");
				date.set(Calendar.YEAR, Integer.parseInt(console.nextLine()));

				le = daily.getDateLog(date);
				System.out.println(le);

				break;
			}
			}
			System.out.println("Enter menu choice");
			ui = Integer.parseInt(console.next());
		}while(ui>0 && ui<10);

		console.close();
	}

}
