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

	@Override
	public void add(E item) {
		Node<E> itemNode = new Node<E>(item);
		if (head == null) {
			this.head = itemNode;
			return;
		} else if (this.internalAdd(itemNode, this.head)) {
			this.updateHeights(this.head);
			this.findProblems(this.head);
		}
		System.out.println(item.getKey());
	}

}
