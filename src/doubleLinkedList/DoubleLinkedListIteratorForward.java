package doubleLinkedList;

import java.io.Serializable;
import java.util.Iterator;

public class DoubleLinkedListIteratorForward <T extends Serializable & Comparable<T>> implements Iterator<T>{
	private DoubleLinkNode<T> front; 
	private DoubleLinkNode<T> iter; 
	
	public DoubleLinkedListIteratorForward(DoubleLinkNode<T> head){
		front = head;
		iter = front;
	}

	public void reset(){
		iter = front;
	}
	
	public boolean hasNext(){
		if(iter != null){
			return true;
		}
		else{
			return false;
		}
	}
	
	public T next(){
		T temp = iter.getData();
		iter = iter.getNext(); 
		return temp; 
	}

}
