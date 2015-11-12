package database.datastrucutres;

/**
 * 'Bare-bones' node for a basic linked list implementation
 * @author Bimesh De Silva
 * @version Final (November 2015)
 *
 * @param <E> Type of item for node to hold
 */
public class ListNode<E> {
	
	private E item;
	private ListNode<E> next, previous;
	int priority;

	public ListNode(E value) {
		this.item = value;
	}
	
	public ListNode(E value, int priority) {
		this.item = value;
		this.priority = priority;
	}

	public ListNode<E> getNext() {
		return this.next;
	}
	
	public void setPrevious(ListNode<E> node){
		this.previous = node;
	}
	
	public ListNode<E> getPrevious() {
		return this.previous;
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
	
	public int getPriority() {
		return this.priority;
	}
}
