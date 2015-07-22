package Diet;

import java.util.GregorianCalendar;

public class LogEntry implements Comparable<LogEntry>{
	GregorianCalendar date;
	int calsEaten;
	double milesWalked;
	double phoneTime;
	
	public LogEntry(GregorianCalendar date, int calsEaten, double milesWalked, double phoneTime){
		this.date = date;
		this.calsEaten = calsEaten;
		this.milesWalked = milesWalked;
		this.phoneTime = phoneTime;		
	}
	
	public GregorianCalendar getDate(){
		return this.getDate();
	}
	public int getCalsEaten(){
		return this.calsEaten;
	}
	public double getMilesWalked(){
		return this.milesWalked;
	}
	public double getPhoneTime(){
		return this.phoneTime;			
	}

	@Override
	public int compareTo(LogEntry log) {
		return this.date.compareTo(log.getDate());
	}
}
