package database.datastrucutres;

/**
 * 'Bare-bones' node for a basic linked list implementation (with potential to
 * be used in a double-linked list or a priority queue)
 * 
 * @author Bimesh De Silva
 * @version Final (November 2015)
 *
 * @param <E>
 *            Type of item for node to hold
 */
public class ListNode<E> {

	/**
	 * The item to hold
	 */
	private E item;

	/**
	 * Reference to following node
	 */
	private ListNode<E> next;

	/**
	 * Reference to preceding node
	 */
	private ListNode<E> previous;

	/**
	 * Integer value of priority (useful if being used as priority queue)
	 */
	int priority;

	/**
	 * Creates a new node containing specified object
	 * 
	 * @param value
	 *            preceding
	 */
	public ListNode(E value) {
		this.item = value;
	}

	/**
	 * Creates a new node with specified object and priority
	 * 
	 * @param value
	 *            preceding
	 * @param priority
	 */
	public ListNode(E value, int priority) {
		this.item = value;
		this.priority = priority;
	}
	
	public ListNode<E> getNext() {
		return this.next;
	}

	public void setPrevious(ListNode<E> node) {
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

	@Override
	public String toString() {
		return this.item.toString();
	}

	public int getPriority() {
		return this.priority;
	}
}
