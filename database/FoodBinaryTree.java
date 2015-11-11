package database;

import database.datastrucutres.BinaryTree;
import database.datastrucutres.Node;

public class FoodBinaryTree<E extends KeyCompare<E>> extends BinaryTree<E> {
	public FoodBinaryTree() {
		super();
	}

	public E get(int key) {
		Node<E> temp = this.head;
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
