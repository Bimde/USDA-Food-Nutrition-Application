package database.datastrucutres;

public class FoodBinaryTree extends BinaryTree<FoodPacket> {
	public FoodBinaryTree() {
		super();
	}

	public FoodPacket get(int key) {
		Node<FoodPacket> temp = this.head;
		while (true) {
			if (temp == null)
				return null;
			int dif = temp.getItem().compareTo(key);
			if (dif == 0)
				return temp.getItem();
			if (temp.isLeaf())
				return null;
			if (dif > 0)
				temp = temp.getLeftChild();
			else
				temp = temp.getRightChild();
		}
	}

	public LinkedList<FoodPacket> search(String query) {
		LinkedList<FoodPacket> list = new LinkedList<FoodPacket>();
		if (this.internalSearch(this.head, query.toLowerCase(), "Long_Desc", list))
			return list;
		return null;
	}

	private boolean internalSearch(Node<FoodPacket> node, String query, String header, LinkedList<FoodPacket> list) {
		if (node != null) {
			FoodPacket food = node.getItem();
			boolean found = false;
			if (food.getValue(header).toLowerCase().contains(query)) {
				list.add(food);
				found = true;
			}
			boolean found1 = internalSearch(node.getLeftChild(), query, header, list),
					found2 = internalSearch(node.getRightChild(), query, header, list);
			return found || found1 || found2;
		}
		return false;
	}
}