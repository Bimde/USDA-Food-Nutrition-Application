package database.datastrucutres;

import java.util.Arrays;

/**
 * Generic linked list to store data with fast load times and o(1)-O(n) read times
 * @author Bimesh De Silva
 * @version Final (November 2015)
 *
 * @param <E> Type of item for nodes to hold
 */
public class LinkedList<E> {
	protected ListNode<E> head, end;
	protected int size;

	public LinkedList() {
		this.size = 0;
	}

	public ListNode<E> getHead() {
		return this.head;
	}

	public int getSize() {
		return this.size;
	}
	
	public boolean isEmpty()
	{
		return this.size > 0;
	}

	public void add(E item) {
		ListNode<E> add = new ListNode<E>(item);
		if (this.head == null)
			this.head = add;
		else {
			this.end.setNext(add);
		}
		this.end = add;
		this.size++;
	}

	public E[] toArray() {
		if(this.size == 0)
			return null;
		if (this.head.getItem() instanceof String)
			return (E[])toStringArray();
		E[] foods = (E[]) new Object[this.size];
		ListNode<E> temp = this.head;
		for (int i = 0; i < foods.length; i++) {
			foods[i] = temp.getItem();
			temp = temp.getNext();
		}
		return foods;
	}
	
	public String[] toStringArray() {
		if(this.size == 0)
			return null;
		String[] foods = new String[this.size];
		ListNode<E> temp = this.head;
		for (int i = 0; i < foods.length; i++) {
			foods[i] = (String)temp.getItem();
			temp = temp.getNext();
		}
		return foods;
	}

	public void merge(LinkedList<E> list) {
		this.end.setNext(list.getHead());
		this.size += list.getSize();
	}

	public void print() {
		ListNode<E> temp = this.head;
		while (temp != null) {
			System.out.println(temp.getItem());
			temp = temp.getNext();
		}
	}
	
	public void add(ListNode<E> node) {
		if (this.head == null)
			this.head = node;
		else {
			this.end.setNext(node);
		}
		this.end = node;
		this.size++;	
	}
}
