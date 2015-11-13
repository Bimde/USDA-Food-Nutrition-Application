package database.datastrucutres;

public class SearchableLinkedList extends LinkedList<Head> {

	public SearchableLinkedList() {
		super();
	}

	public String get(String key) {
		ListNode<Head> temp = this.head;
		while (temp != null) {
			if (temp.getItem().getHeader().equals(key))
				return temp.getItem().getValue();
			temp = temp.getNext();
		}
		return "";
	}

	public boolean set(String header, String value) {
		ListNode<Head> temp = this.head;
		while (temp != null) {
			if (temp.getItem().getHeader().equals(header)) {
				temp.getItem().setValue(value);
				return true;
			}
			temp = temp.getNext();
		}
		return false;
	}
}
