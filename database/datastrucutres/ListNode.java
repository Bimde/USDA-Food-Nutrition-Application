package database.datastrucutres;

public class ListNode<E> {
	
	private E item;
	private ListNode<E> next, previous;

	public ListNode(E value) {
		this.item = value;
	}

	public ListNode(E value, ListNode<E> previous) {
		this.item = value;
		this.previous = previous;
	}

	public ListNode<E> getNext() {
		return this.next;
	}

	public E getItem() {
		return this.item;
	}

	public void setNext(ListNode<E> next) {
		this.next = next;
	}

	public ListNode<E> getPrevious() {
		return this.previous;
	}

	public void setPrevious(ListNode<E> previous) {
		this.previous = previous;
	}
}
