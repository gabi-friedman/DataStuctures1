package billOrganizer;

import java.io.Serializable;
import java.util.Comparator;

import books2014.NotFoundException;

public class PriorityQueue <T extends Serializable & Comparable<T> >implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SortedLinkedList<T> stuff;
	private Comparator<T> comp;
	private LinkedListIterator<T> iter;
	
	public PriorityQueue(Comparator<T> tempComp){
		stuff = new SortedLinkedList<T>();
		this.comp = tempComp;
		
	}
	
	public void enqueue(T data) throws DuplicateDataException{
		stuff.insert(data, comp);
	}
	
	public T dequeue() throws ListEmptyException, NotFoundException{
		T temp = stuff.getFirst().getData();
		stuff.remove(temp);
		return temp;
	}
	
	public T peek(){
		return stuff.getFirst().getData();
	}
	
	public void remove(T data) throws ListEmptyException, NotFoundException{
		stuff.remove(data);
	}
	
	public Node<T> find(T data) throws DataNotFoundException{
		return stuff.find(data);
	}
	
	public LinkedListIterator<T> getIter(){
		this.iter = stuff.iterator();
		return this.iter;
	}
}
