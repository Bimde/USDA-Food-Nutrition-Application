package database.datastrucutres;

/**
 * LinkedList containing only FoodPacket items for use when returning search results
 * @author Bimesh De Silva
 * @version Final (November 2015)
 *
 */
public class FoodPacketList extends LinkedList<FoodPacket> {

	public FoodPacketList() {
		super();
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
		FoodPacketList list = new FoodPacketList();
		ListNode<FoodPacket> temp = this.head;
		while (temp != null) {
			if (temp.getItem().getValue(header).equals(value))
				list.add(temp.getItem());
			temp = temp.getNext();
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
