package database;

import database.datastrucutres.BinaryTree;
import database.datastrucutres.Node;

public class FoodBinaryTree extends BinaryTree<FoodPacket> {
	public FoodBinaryTree() {
		super();
	}
	
	public FoodPacket get(int key){
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
}
