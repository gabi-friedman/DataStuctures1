package finalProject;

import java.io.Serializable;
import java.util.Comparator;

public class YearCompletedComparator implements Serializable, Comparator<CompletedCourse>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int compare(CompletedCourse c1, CompletedCourse c2) {
		int yr = c1.getYrCompleted().compareTo(c2.getYrCompleted());
		if(yr == 0){
			int ord1 = c1.getSemCompleted().ordinal(); 
			int ord2 = c2.getSemCompleted().ordinal(); 
			
			if(ord1 > ord2){
				return 1;
			}
			else if(ord1 < ord2){
				return -1;
			}
			else{
				return 0;
			}
		}
		return yr;
	}
	
}
