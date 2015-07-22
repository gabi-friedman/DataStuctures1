package doubleLinkedList;

import java.io.Serializable;
import java.util.Iterator;

import books2014.NotFoundException;


public class DoubleLinkedList<T extends Serializable & Comparable<T> >implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected DoubleLinkNode<T> head;
	protected DoubleLinkNode<T> back;

	public DoubleLinkedList(){
		head = null;
		back = null;
	}

	public void insert(T data)throws Exception{
		DoubleLinkNode<T> aNode = new DoubleLinkNode<T>(data);
		//if this is the 1st one in the list..
		if (null == head){
			head = aNode;
			back = aNode;
			return;
		}
		
		//if there is only a head (one element in the list)
		if(head.getData() == back.getData()){
			if(aNode.getData().compareTo(head.getData()) >= 0){
				back = aNode;
			}
			else{
				back = head;
				head = aNode;
			}

			head.setNext(back);
			back.setPrev(head);
			return;
		}
		DoubleLinkNode<T> cur = head;
		DoubleLinkNode<T> nxt = cur.getNext();
		DoubleLinkNode<T> prev = cur.getPrev();
		DoubleLinkNode<T> end = back;

		//if smaller than head
		if(aNode.getData().compareTo(cur.getData()) < 0){
			cur.setPrev(aNode);
			aNode.setNext(cur);
			head = aNode;
			return;
		}

		//if larger than back
		if(aNode.getData().compareTo(end.getData()) > 0){
			end.setNext(aNode);
			aNode.setPrev(end);
			back = aNode;
			return;
		}

		//if the data goes somewhere in the middle...
		else{
			while(cur != null){
				if(aNode.getData().compareTo(cur.getData()) < 0){
					cur.setPrev(aNode);
					prev.setNext(aNode);
					aNode.setNext(cur);
					aNode.setPrev(prev);
					return;
				}
				else if(aNode.getData().compareTo(cur.getData()) >= 0)
					prev = cur;
					cur = nxt;
					nxt = cur.getNext();
					 
			}
		}

		/*
		back.setNext(aNode);
		aNode.setPrev(back);
		back = aNode;
		*/
	}

	public void remove(T data)throws ListEmptyException,NotFoundException{
		DoubleLinkNode<T> cur;
		DoubleLinkNode<T> prev;
		if (head == null) throw new ListEmptyException();
		else {
			cur = head;
			prev = head;
			while (cur != null){
				if (cur.getData().compareTo(data) == 0) {
					if (cur == head) {
						head = head.getNext();
						head.setPrev(null);
						return;
					} else if (cur == back) {
						back = back.getPrev();
						back.setNext(null);
					} else {
						prev.setNext(cur.getNext());
						cur.getNext().setPrev(prev);
						return;
					}
				} else {
					prev = cur;
					cur = cur.getNext();
				}
			}
			throw new NotFoundException ();
		}

	}

	public void removeAll(){
		head = null;
	}

	public DoubleLinkNode<T> getFirst()throws ListEmptyException{
		if (head == null) {
			throw new ListEmptyException();
		}
		return head;
	}

	public DoubleLinkNode<T> getLast()throws ListEmptyException{
		if (back == null) {
			throw new ListEmptyException();
		}
		return back;
	}

	public void removeFirst()throws ListEmptyException{
		if (head == null) {
			throw new ListEmptyException();
		}
		head = head.getNext();
	}

	public void removeLast()throws ListEmptyException{
		if (back == null) {
			throw new ListEmptyException();
		}
		back = back.getPrev();
	}

	public boolean isEmpty(){
		//is list empty
		return (head == null);
	}

	public String toString(){
		DoubleLinkNode<T> currNode = head.getNext();
		String info = null;
		if (head == null)
			return " ";
		else info = head.getData().toString() ;
		while (currNode != null){
			info = info +  " " + currNode.getData().toString();
			currNode = currNode.getNext();
		}
		return info;
	}
	
	public DoubleLinkedListIteratorForward<T> fwdIter(){
		DoubleLinkedListIteratorForward<T> iter = new DoubleLinkedListIteratorForward<T>(head);
		return iter;
	}
	
	public DoubleLinkedListIteratorBackward<T> reverseIter(){
		DoubleLinkedListIteratorBackward<T> iter = new DoubleLinkedListIteratorBackward<T>(back); 
		return iter;
	}
	
}
