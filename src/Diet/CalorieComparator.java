package Diet;

import java.util.Comparator;


public class CalorieComparator implements Comparator<LogEntry>{

	@Override
	public int compare(LogEntry log1, LogEntry log2) {
		int result = -2;
		if(log1.getCalsEaten() > log2.getCalsEaten()){
			result = 1;
		}
		else if(log1.getCalsEaten() == log2.getCalsEaten()){
			result = 0;
		}
		if(log1.getCalsEaten() < log2.getCalsEaten()){
			result = -1;
		}
		
		return result;
	}

}
