package billOrganizer;

import java.io.Serializable;
import java.util.Comparator;
import java.util.GregorianCalendar;


public class SortedLinkedListFromP<T extends Serializable & Comparable<T>> extends
		LinkedList<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	// Node<T> head;
	Node<T> tail;

	public void insert(T data) throws DuplicateDataException {

		Node<T> currNode;
		Node<T> prevNode;
		Node<T> aNode;

		if (head == null) {
			// this data will be entered into first node
			tail = head = new Node<T>(data);

		} else {
			aNode = new Node<T>(data);
			prevNode = head;
			currNode = head.getNext();

			while (currNode != null) {

				if (prevNode.getData().compareTo(data) == 0) {
					throw new DuplicateDataException();
				}
				if (prevNode.getData().compareTo(data) > 0) {
					aNode.setNext(prevNode);
					head = aNode;

					break;
				} else if (prevNode.getData().compareTo(data) <= 0
						&& currNode.getData().compareTo(data) >= 0) {
					// data goes in between
					aNode.setNext(currNode);
					prevNode.setNext(aNode);
					break;
				} else {
					// didn't find it yet, continue on to next node
					prevNode = currNode;
					currNode = prevNode.getNext();
				}

			}
			if (currNode == null) {
				if (prevNode.getData().compareTo(data) > 0) {
					currNode = prevNode;
					prevNode = aNode;
					prevNode.setNext(currNode);
					tail = currNode;
					head = prevNode;
				} else {
					currNode = aNode;
					prevNode.setNext(currNode);
					tail = currNode;

				}

			}
		}
	}

	public void insert(T data, Comparator<T> comparator) {

		Node<T> currNode;
		Node<T> prevNode;
		Node<T> theNode;

		if (head == null) {
			// this data will be entered into first node
			tail = head = new Node<T>(data);

		} else {
			theNode = new Node<T>(data);
			prevNode = head;
			currNode = head.getNext();

			while (currNode != null) {

				if (comparator.compare(prevNode.getData(), theNode.getData()) >= 0 || comparator.compare(prevNode.getData(),
								theNode.getData()) <= 0) {
					// if (prevNode.getData().compareTo(data) > 0) {
					theNode.setNext(prevNode);
					head = theNode;

					break;
				}// else if (comparator.compare(prevNode.getData(),theNode.getData()) < 0	&& comparator.compare(currNode.getData(), theNode.getData()) > 0) {
					 else if (prevNode.getData().compareTo(data) < 0 && currNode.getData().compareTo(data) > 0) {
					// data goes in between
					theNode.setNext(currNode);
					prevNode.setNext(theNode);
					break;
				} else {
					// didn't find it yet, continue on to next node
					prevNode = currNode;
					currNode = currNode.getNext();
				}

			}
			if (currNode == null) {
				if(comparator.compare(prevNode.getData(), data)>0){
					currNode = prevNode;
					prevNode = theNode;
					prevNode.setNext(currNode);
					tail = currNode;
					head = prevNode;
				} else {
					currNode = theNode;
					prevNode.setNext(currNode);
					tail = currNode;

				}

			}
		}
	}

	public Node<T> find(T data) {

		Node<T> currNode;
		currNode = head;
		while (currNode != null) {
			if (currNode.getData().compareTo(data) == 0) {
				return currNode;
			} else {
				currNode = currNode.getNext();
			}
		}

		return null;

	}
	
	

	public String toString() {
		StringBuilder info = new StringBuilder();
		Node<T> currentNode = head;
		while (!(currentNode == null)) {
			info.append(currentNode.getData().toString());
			currentNode = currentNode.getNext();
			info.append("\n");
		}
		return info.toString();
	}
	
	public static void main(String [] args){
		Bill billOne = new Bill(  1, "Moishes",     100.00, new GregorianCalendar(),"FOOD");
		Bill billTwo = new Bill(  2, "Pomegranate", 80.00,  new GregorianCalendar(),"GROCERIES");
		Bill billThree = new Bill(4, "Freunds",     500.00, new GregorianCalendar(),"CLOTHING");
		Bill billFour= new Bill(  3, "Eichlers",    100.00, new GregorianCalendar(),"EDUCATION");
		Bill billFive = new Bill( 5, "Waldbaums",   200.00, new GregorianCalendar(),"FOOD");
		
		//instantiate the comparators
		BillAmountComparator amtCompare = new BillAmountComparator();
		BillTypeComparator typeCompare = new BillTypeComparator();
		BillDateComparator vendorCompare = new BillDateComparator();
		
		SortedLinkedList<Bill> billAmounts = new SortedLinkedList<Bill>();
		SortedLinkedList<Bill> billTypes = new SortedLinkedList<Bill>();
		SortedLinkedList<Bill> billVendors = new SortedLinkedList<Bill>();
		
		Bill[] bills = new Bill[] {billOne,billTwo,billThree,billFour,billFive};
		
		for (Bill aBill : bills){
			billAmounts.insert(aBill, amtCompare);
			billTypes.insert(aBill, typeCompare);
			billVendors.insert(aBill,vendorCompare);
		}
		
		LinkedListIterator<Bill>iterAmounts =  billAmounts.iterator();
		LinkedListIterator <Bill> iterTypes = billTypes.iterator();
		LinkedListIterator<Bill> iterVendors = billVendors.iterator();
		
		System.out.println("in $ order");
		while (iterAmounts.hasNext()){
			System.out.println(iterAmounts.next());
		}
		
		System.out.println("in type order");
		
		while (iterTypes.hasNext()){
			System.out.println(iterTypes.next());
		}
		
		System.out.println("in vendor order");
		while (iterVendors.hasNext()){
			System.out.println(iterVendors.next());
		}
	}

}