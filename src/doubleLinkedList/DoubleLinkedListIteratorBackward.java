package doubleLinkedList;

import java.io.Serializable;

public class DoubleLinkedListIteratorBackward <T extends Serializable & Comparable<T>>{
	
	private DoubleLinkNode<T> back; 
	private DoubleLinkNode<T> iter; 
	
	public DoubleLinkedListIteratorBackward(DoubleLinkNode<T> back){
		this.back = back;
		iter = back;
	}

	public void reset(){
		iter = back;
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
		iter = iter.getPrev(); 
		return temp; 
	}
	
}
