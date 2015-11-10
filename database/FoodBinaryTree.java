package database;

import database.datastrucutres.BinaryTree;
import database.datastrucutres.Node;

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

	@Override
	public void add(FoodPacket item) {
		Node<FoodPacket> itemNode = new Node<FoodPacket>(item);
		if (head == null) {
			this.head = itemNode;
			return;
		} else if (this.internalAdd(itemNode, this.head)) {
			this.updateHeights(this.head);
			this.findProblems(this.head);
		}
		System.out.println(item.getKey());
	}

	@Override
	protected boolean internalAdd(Node<FoodPacket> node,
			Node<FoodPacket> tempHead) {
		int dif = node.getItem().compareTo(tempHead.getItem());
		if (tempHead.isLeaf()) {
			if (dif < 0)
				tempHead.setLeft(node);
			else if (dif > 0)
				tempHead.setRight(node);
			else
				return false;
		} else {
			if (dif < 0) {
				if (tempHead.getLeftChild() == null)
					tempHead.setLeft(node);
				else
					this.internalAdd(node, tempHead.getLeftChild());
			} else if (dif > 0) {
				if (tempHead.getRightChild() == null)
					tempHead.setRight(node);
				else
					this.internalAdd(node, tempHead.getRightChild());
			} else {
				// ADD new foodpacket's data to old FoodPacket
				// tempHead.getItem().addData(node.get)TODO
				return false;
			}
		}
		return true;
	}
}
