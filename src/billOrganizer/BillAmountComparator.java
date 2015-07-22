package billOrganizer;

import java.util.Comparator;

public class BillAmountComparator implements Comparator<Bill>{
	

	@Override
	public int compare(Bill bill1, Bill bill2) {
		return bill1.getAmtDue().compareTo(bill2.getAmtDue());
	}
}
