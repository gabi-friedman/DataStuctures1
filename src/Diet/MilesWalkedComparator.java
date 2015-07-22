package Diet;

import java.util.Comparator;


public class MilesWalkedComparator implements Comparator<LogEntry>{

	@Override
	public int compare(LogEntry log1, LogEntry log2) {
		int result = -2;
		if(log1.getMilesWalked() > log2.getMilesWalked()){
			result = 1;
		}
		else if(log1.getMilesWalked() == log2.getMilesWalked()){
			result = 0;
		}
		if(log1.getMilesWalked() < log2.getMilesWalked()){
			result = -1;
		}
		
		return result;
	}

}
