package database.datastrucutres;

/**
 * LinkedList containing only FoodPacket items for use when returning search
 * results
 * 
 * @author Bimesh De Silva
 * @version Final (November 2015)
 *
 */
public class FoodPacketList extends LinkedList<FoodPacket> {

	public FoodPacketList() {
		super();
	}

	public void add(FoodPacket item, int matches) {
		ListNode<FoodPacket> node = new ListNode<FoodPacket>(item, matches);
		if (this.head == null)
			this.add(node);
		else {
			ListNode<FoodPacket> searchNode = this.end;
			while (searchNode.getPriority() > matches) {
				searchNode = searchNode.getPrevious();
				if (searchNode == null)
					break;
			}
			if (searchNode == null) {
				this.head.setPrevious(node);
				node.setNext(this.head);
				this.head = node;
			} else {
				ListNode<FoodPacket> temp = searchNode.getNext();
				if (temp == null) {
					searchNode.setNext(node);
					node.setPrevious(searchNode);
				} else {
					searchNode.setNext(node);
					node.setNext(temp);
					node.setPrevious(searchNode);
					temp.setPrevious(node);
				}
			}
		}
		this.size++;
	}

	public FoodPacket get(int key) {
		ListNode<FoodPacket> temp = this.head;
		while (temp != null) {
			if (temp.getItem().getKey() == key)
				return temp.getItem();
			temp = temp.getNext();
		}
		return null;
	}

	public FoodPacket get(String key) {
		ListNode<FoodPacket> temp = this.head;
		while (temp != null) {
			if (temp.getItem().getValue("NDB_No").equals(key))
				return temp.getItem();
			temp = temp.getNext();
		}
		return null;
	}

	public FoodPacketList search(String header, String value) {
		return this.search(header, new String[] { value });
	}

	public FoodPacketList search(String header, String[] values) {
		FoodPacketList list = new FoodPacketList();
		ListNode<FoodPacket> temp = this.head;
		for (String value : values) {
			while (temp != null) {
				if (temp.getItem().getValue(header).equals(value))
					list.add(temp.getItem());
				temp = temp.getNext();
			}
		}
		return list;
	}

	public FoodPacketList search(String header, int value) {
		FoodPacketList list = new FoodPacketList();
		ListNode<FoodPacket> temp = this.head;
		while (temp != null) {
			if (Integer.parseInt(temp.getItem().getValue(header)) == value)
				list.add(temp.getItem());
			temp = temp.getNext();
		}
		return list;
	}

}
