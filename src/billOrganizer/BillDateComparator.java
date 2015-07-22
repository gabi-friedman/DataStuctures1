package billOrganizer;

import java.util.Comparator;

public class BillDateComparator implements Comparator<Bill>{

	@Override
	public int compare(Bill bill1, Bill bill2) {
		return bill1.getDateDue().compareTo(bill2.getDateDue());
	}

}
