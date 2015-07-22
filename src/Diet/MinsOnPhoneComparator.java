package Diet;

import java.util.Comparator;


public class MinsOnPhoneComparator implements Comparator<LogEntry>{

	@Override
	public int compare(LogEntry log1, LogEntry log2) {
		int result = -2;
		if(log1.getPhoneTime() > log2.getPhoneTime()){
			result = 1;
		}
		else if(log1.getPhoneTime() == log2.getPhoneTime()){
			result = 0;
		}
		if(log1.getPhoneTime() < log2.getPhoneTime()){
			result = -1;
		}
		
		return result;
	}

}
