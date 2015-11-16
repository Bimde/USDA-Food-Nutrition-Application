package database.datastrucutres;

/**
 * Generic linked list to store data with fast load times and o(1)-O(n) read
 * times
 * 
 * @author Bimesh De Silva
 * @version Final (November 2015)
 *
 * @param <T>
 *            Type of item for nodes to hold
 */
public class LinkedList<T> {
	/**
	 * Reference to first node in list
	 */
	protected ListNode<T> head;

	/**
	 * Reference to last node in list
	 */
	protected ListNode<T> end;

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
	public ListNode<T> getHead() {
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
	public void add(T item) {
		ListNode<T> add = new ListNode<T>(item);
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
	 * @return array of objects or NULL IF TMPTY
	 */
	public T[] toArray() {
		if (this.isEmpty())
			return null;

		// Calls a special method to create the array for String because you
		// cannot cast an Object array to a String array
		if (this.head.getItem() instanceof String)
			return (T[]) toStringArray();

		// Creates an array of type 'T' using casting technique
		T[] items = (T[]) new Object[this.size];

		// Add the items to the array iteratively
		ListNode<T> temp = this.head;
		for (int i = 0; i < items.length; i++) {
			items[i] = temp.getItem();
			temp = temp.getNext();
		}
		return items;
	}

	public void merge(LinkedList<T> list) {
		this.end.setNext(list.getHead());
		this.size += list.getSize();
	}

	/**
	 * FOR DTBUGGING PURPOSTS: interatively prints out the values of all of the
	 * items in the list, from head onwards
	 */
	public void print() {
		ListNode<T> temp = this.head;
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
	public void add(ListNode<T> node) {
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
	 * @return Array of string objects
	 */
	private String[] toStringArray() {
		if (this.size == 0)
			return null;
		String[] foods = new String[this.size];
		ListNode<T> temp = this.head;
		for (int i = 0; i < foods.length; i++) {
			foods[i] = (String) temp.getItem();
			temp = temp.getNext();
		}
		return foods;
	}
}
