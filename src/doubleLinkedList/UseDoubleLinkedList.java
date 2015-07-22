package doubleLinkedList;

public class UseDoubleLinkedList {

	public static void main(String args[]) throws Exception{
		
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
		list.insert(10);
		list.insert(5);
		list.insert(15);
		list.insert(14);
		list.insert(20);
		list.insert(25);
		
		System.out.println(list.toString());
		
		
		//instantiate iterator and print out using toString method
		System.out.println("Printing Backwards:");
		DoubleLinkedListIteratorBackward<Integer> backIter = list.reverseIter();
		while(backIter.hasNext()){
			System.out.println(backIter.next());
		}
		
		System.out.println("\nPrinting Forwards:");
		DoubleLinkedListIteratorForward<Integer> fwdIter = list.fwdIter();
		while(fwdIter.hasNext()){
			System.out.println(fwdIter.next());
		}
	}
}
