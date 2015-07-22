package billOrganizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Scanner;

import books2014.NotFoundException;

public class BillOrganizer <T extends Serializable & Comparable<T>> extends
LinkedList<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private PriorityQueue<Bill> pq1;
	private PriorityQueue<Bill> pq2;
	private PriorityQueue<Bill> pq3;
	private SortedLinkedList<Bill> sll;

	public BillOrganizer(){
		this.pq1 = new PriorityQueue<Bill>(new BillDateComparator());
		this.pq2 = new PriorityQueue<Bill>(new BillTypeComparator());
		this.pq3 = new PriorityQueue<Bill>(new BillAmountComparator());

		this.sll = new SortedLinkedList<Bill>();
	}

	public BillOrganizer(int billID, String filename) throws Exception{
		Scanner console = new Scanner(filename);
		while(console.hasNext()){
			insert(new Bill(billID, console.nextLine()));
		}

		this.pq1 = new PriorityQueue<Bill>(new BillDateComparator());
		this.pq2 = new PriorityQueue<Bill>(new BillTypeComparator());
		this.pq3 = new PriorityQueue<Bill>(new BillAmountComparator());

		this.sll = new SortedLinkedList<Bill>();
	}

	public BillOrganizer(String filename) throws FileNotFoundException, IOException, ClassNotFoundException, DuplicateDataException{
		ObjectInputStream objInStr = new ObjectInputStream(new FileInputStream(filename));	
		sll = (SortedLinkedList<Bill>) objInStr.readObject();
		objInStr.close();	
		LinkedListIterator<Bill> iter = sll.iterator();
		while(iter.hasNext()){
			Bill n = iter.next();
			pq1.enqueue(n);
			pq2.enqueue(n);
			pq3.enqueue(n);
		}
		
			
	}

	public void insert(Bill bill) throws Exception{
		sll.insert(bill);
		pq1.enqueue(bill);
		pq2.enqueue(bill);
		pq3.enqueue(bill);
	}

	public Node<Bill> payNextBill(Bill bill, BillCriteria criteria) throws DataNotFoundException, ListEmptyException, NotFoundException   {
		Node<Bill> temp = null;
		if(criteria.equals(BillCriteria.BILLAMOUNT)){
			temp = pq3.find(bill);
		}
		else if(criteria.equals(BillCriteria.BILLDUEDATE)){
			temp = pq1.find(bill);
		}
		else if(criteria.equals(BillCriteria.BILLTYPE)){
			temp = pq2.find(bill);
		}

		sll.remove(bill);
		pq1.remove(bill);
		pq2.remove(bill);
		pq3.remove(bill);
		return temp;
	}

	public void closeOrganizer(String filename) throws FileNotFoundException, IOException{
		ObjectOutputStream objOutStr = new ObjectOutputStream(new FileOutputStream(filename));
		objOutStr.writeObject(sll);
	}

	public double totalBills(){
		double total = 0;
		LinkedListIterator<Bill> iter = sll.iterator();
		while(iter.hasNext()){
			total += iter.next().getAmtDue();
		}
		return total;
	}

	public String toString(){
		StringBuilder build = new StringBuilder();
		LinkedListIterator<Bill> iter = sll.iterator();
		while(iter.hasNext()){
			build.append(iter.next().toString());
		}
		return build.toString();
	}

	public LinkedListIterator<Bill> iteratorByDate(){
		return pq1.getIter();
	}
	
	public LinkedListIterator<Bill> iteratorByAmount(){
		return pq3.getIter();
	}
	
	public Bill getFirstOfAmt(){
		return iteratorByAmount().next();
	}
	
	public LinkedListIterator<Bill> iteratorByType(){
		return pq2.getIter();
	}
	
	public LinkedListIterator<Bill> sortedIter(){
		return sll.iterator();
	}
	
	public Bill findByID(Integer billID) throws DataNotFoundException{
		LinkedListIterator<Bill> i = sortedIter();
		Bill b;
		while(i.hasNext()){
			b = i.next();
			if(billID == b.getBillID()){
				return b;
			}
		}
		throw new DataNotFoundException();
	}
	
	
}
