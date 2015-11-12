package database.datastrucutres;

public class ListNode<E> {
	
	private E item;
	private ListNode<E> next;

	public ListNode(E value) {
		this.item = value;
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
	
	public String toString()
	{
		return this.item.toString();
	}
}
