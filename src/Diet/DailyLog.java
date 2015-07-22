package Diet;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class DailyLog {
	LinkedList<LogEntry> entries;

	public DailyLog(){
		entries = new LinkedList<LogEntry>();
	}

	public void addLog(LogEntry le){
		entries.add(le);
	}

	public PriorityQueue<LogEntry> getEntriesByDate(){
		PriorityQueue<LogEntry> sorted = new PriorityQueue<LogEntry>();
		Iterator<LogEntry> iter = entries.iterator();

		while(iter.hasNext()){
			sorted.add(iter.next());
		}
		return sorted;
	}
	
	public PriorityQueue<LogEntry> getEntriesByMiles(){
		PriorityQueue<LogEntry> sorted = new PriorityQueue<LogEntry>(new MilesWalkedComparator());
		Iterator<LogEntry> iter = entries.iterator();

		while(iter.hasNext()){
			sorted.add(iter.next());
		}
		return sorted;
	}

	public PriorityQueue<LogEntry> getEntriesByMins(){
		PriorityQueue<LogEntry> sorted = new PriorityQueue<LogEntry>(new MinsOnPhoneComparator());
		Iterator<LogEntry> iter = entries.iterator();

		while(iter.hasNext()){
			sorted.add(iter.next());
		}
		return sorted;
	}

	public PriorityQueue<LogEntry> getEntriesByCals(){
		PriorityQueue<LogEntry> sorted = new PriorityQueue<LogEntry>(new CalorieComparator());
		Iterator<LogEntry> iter = entries.iterator();

		while(iter.hasNext()){
			sorted.add(iter.next());
		}
		return sorted;
	}

	public int getTotalCals(){
		int total = 0;
		Iterator<LogEntry> iter = entries.iterator();
		while(iter.hasNext()){
			total += iter.next().getCalsEaten();
		}
		return total;
	}
	
	public double getAvgCals(){
		int numElements = entries.size();
		int cals = getTotalCals();
		double avg = cals/numElements;

		return avg;
	}
	
	public int getTotalMiles(){
		int total = 0;
		Iterator<LogEntry> iter = entries.iterator();
		while(iter.hasNext()){
			total += iter.next().getMilesWalked();
		}
		return total;
	}
	
	public double getAvgMiles(){
		int numElements = entries.size();
		double miles = getTotalMiles();
		double avg = miles/numElements;
		
		return avg;
	}
	
	public int getTotalPhone(){
		int total = 0;
		Iterator<LogEntry> iter = entries.iterator();
		while(iter.hasNext()){
			total += iter.next().getPhoneTime();
		}
		return total;
	}
	
	public double getAvgPhone(){
		int numElements = entries.size();
		double phone = getTotalPhone();
		double avg = phone/numElements;
		
		return avg;
	}
	
	public LogEntry getDateLog(GregorianCalendar date){
		Iterator<LogEntry> iter = entries.iterator();
		while(iter.hasNext()){
			if(iter.next().getDate().compareTo(date) == 0){
				return iter.next();
			}
		}
		return null;
	}
}
