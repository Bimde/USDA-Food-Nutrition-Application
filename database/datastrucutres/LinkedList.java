package database.datastrucutres;

public class LinkedList<E> {
	protected ListNode<E> head, end;
	private int size;

	public LinkedList() {
		this.size = 0;
	}

	public ListNode<E> getHead() {
		return this.head;
	}

	public int getSize() {
		return this.size;
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
		E[] foods = (E[]) (new Object[this.size]);
		ListNode<E> temp = this.head;
		for (int i = 0; i < foods.length; i++) {
			foods[i] = temp.getItem();
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
}
