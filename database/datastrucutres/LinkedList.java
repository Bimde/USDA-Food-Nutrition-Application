package database.datastrucutres;

/**
 * Generic linked list to store data with fast load times and o(1)-O(n) read
 * times
 * 
 * @author Bimesh De Silva
 * @version Final (November 2015)
 *
 * @param <E>
 *            Type of item for nodes to hold
 */
public class LinkedList<E> {
	/**
	 * Reference to first node in list
	 */
	protected ListNode<E> head;

	/**
	 * Reference to last node in list
	 */
	protected ListNode<E> end;

	/**
	 * Number of nodes in list
	 */
	protected int size;

	/**
	 * Created a new Linked List of size 0
	 */
	public LinkedList() {
		this.size = 0;
	}

	/**
	 * Getter for first node in list
	 * 
	 * @return the first node in the list
	 */
	public ListNode<E> getHead() {
		return this.head;
	}

	/**
	 * Getter for size of the list
	 * 
	 * @return Number of nodes in the list
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Checks if the list is empty
	 * 
	 * @return Whether or not the list is empty
	 */
	public boolean isEmpty() {
		return this.head == null;
	}

	/**
	 * Adds a specified item to the list
	 * 
	 * @param item
	 *            The item to add to the list
	 */
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

	/**
	 * Creates a new array of all of the objects
	 * 
	 * @return array of objects or NULL IF EMPTY
	 */
	public E[] toArray() {
		if (this.isEmpty())
			return null;

		// Calls a special method to create the array for String because you
		// cannot cast an Object array to a String array
		if (this.head.getItem() instanceof String)
			return (E[]) toStringArray();

		// Creates an array of type 'E' using casting technique
		E[] foods = (E[]) new Object[this.size];

		// Add the items to the array iteratively
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

	/**
	 * FOR DEBUGGING PURPOSES: interatively prints out the values of all of the
	 * items in the list, from head onwards
	 */
	public void print() {
		ListNode<E> temp = this.head;
		while (temp != null) {
			System.out.println(temp.getItem());
			temp = temp.getNext();
		}
	}

	/**
	 * Adds a specified node to the end of the list
	 * 
	 * @param node
	 *            Node to add
	 */
	public void add(ListNode<E> node) {
		if (this.head == null)
			this.head = node;
		else {
			this.end.setNext(node);
		}
		this.end = node;
		this.size++;
	}

	/**
	 * Special method to create array of objects if the objects are strings
	 * 
	 * @return arrays of string objects
	 */
	private String[] toStringArray() {
		if (this.size == 0)
			return null;
		String[] foods = new String[this.size];
		ListNode<E> temp = this.head;
		for (int i = 0; i < foods.length; i++) {
			foods[i] = (String) temp.getItem();
			temp = temp.getNext();
		}
		return foods;
	}
}
