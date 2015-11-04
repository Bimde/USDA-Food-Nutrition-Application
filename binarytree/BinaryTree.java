package binarytree;

public class BinaryTree<E extends Comparable<E>> {
	protected BinaryTreeNode<E> head;

	public BinaryTree() {
	}

	public void add(E item) {
		BinaryTreeNode<E> itemNode = new BinaryTreeNode<E>(item);
		if (head == null)
			this.head = itemNode;
		else
			this.internalAdd(itemNode, this.head);
	}

	public E get(E item) {
		BinaryTreeNode<E> temp = getNode(item);
		if (temp == null)
			return null;
		return temp.getItem();
	}

	public void print() {
		this.internalPrint(this.head);
	}

	public boolean contains(E item) {
		if (this.getNode(item) == null)
			return false;
		return true;
	}
	
	public int maxDepth()
	{
		return this.internalMaxDepth(this.head);
	}
	
	public int minDepth()
	{
		return this.internalMinDepth(this.head);
	}

	public boolean remove(E item) {
		BinaryTreeNode<E> node = getNode(item);
		if (node == null)
			return false;
		BinaryTreeNode<E> parent = node.getParent();
		if (parent == null) {
			this.removeHead();
			return true;
		}
		if (node.isLeaf()) {
			if (parent.getLeft() == node) {
				parent.setLeft(null);
			} else {
				parent.setRight(null);
			}
		} else if (node.getLeft() == null) {
			if (parent.getLeft() == node) {
				parent.setLeft(node.getRight());
			} else {
				parent.setRight(node.getRight());
			}
		} else if (node.getRight() == null) {
			if (parent.getLeft() == node) {
				parent.setLeft(node.getLeft());
			} else {
				parent.setRight(node.getLeft());
			}
		} else {
			if (parent.getLeft() == node) {
				parent.setLeft(node.getRight());
				BinaryTreeNode<E> temp = parent.getLeft();
				while (temp.getLeft() != null) {
					temp = temp.getLeft();
				}
				temp.setLeft(node.getLeft());
			} else {
				parent.setRight(node.getLeft());
				BinaryTreeNode<E> temp = parent.getRight();
				while (temp.getRight() != null) {
					temp = temp.getRight();

				}
				temp.setRight(node.getRight());
			}
		}
		return true;
	}

	protected int internalMaxDepth(BinaryTreeNode<E> node) {
		if(node == null)
			return -1;
		if (node.isLeaf())
			return node.getDepth();
		else if (node.getLeft() == null)
			return internalMaxDepth(node.getRight());
		else if (node.getRight() == null)
			return internalMaxDepth(node.getLeft());
		else {
			int depth1 = internalMaxDepth(node.getLeft());
			int depth2 = internalMaxDepth(node.getRight());
			return depth1 >= depth2 ? depth1 : depth2;
		}
	}
	
	protected int internalMinDepth(BinaryTreeNode<E> node)
	{
		if(node.getLeft() == null || node.getRight() == null)
			return node.getDepth();
		return Math.min(internalMinDepth(node.getLeft()), internalMinDepth(node.getRight()));
	}

	protected void removeHead() {
		if (this.head.isLeaf())
			this.head = null;
		else if (this.head.getLeft() == null) {
			this.head = this.head.getRight();
		} else if (this.head.getRight() == null) {
			this.head = this.head.getLeft();
		} else {
			BinaryTreeNode<E> temp = this.head.getRight();
			while (temp.getLeft() != null) {
				temp = temp.getLeft();
			}
			temp.setLeft(this.head.getLeft());
			this.setHead(this.head.getRight());
		}
	}

	protected boolean internalAdd(BinaryTreeNode<E> node,
			BinaryTreeNode<E> tempHead) {
		int dif = node.getItem().compareTo(tempHead.getItem());
		if (this.head.isLeaf()) {
			if (dif < 0)
				tempHead.setLeft(node);
			else if (dif > 0)
				tempHead.setRight(node);
			else
				return false;
		} else {
			if (dif < 0) {
				if (tempHead.getLeft() == null)
					tempHead.setLeft(node);
				else
					this.internalAdd(node, tempHead.getLeft());
			} else if (dif > 0) {
				if (tempHead.getRight() == null)
					tempHead.setRight(node);
				else
					this.internalAdd(node, tempHead.getRight());
			} else
				return false;
		}
		return true;
	}

	protected BinaryTreeNode<E> getNode(E item) {
		BinaryTreeNode<E> temp = head;
		while (true) {
			if (temp == null)
				return null;
			if (temp.getItem() == item)
				return temp;
			if (temp.isLeaf())
				return null;
			if (temp.getItem().compareTo(item) > 0)
				temp = temp.getLeft();
			else
				temp = temp.getRight();
		}
	}

	protected void internalPrint(BinaryTreeNode<E> tempHead) {
		if (tempHead.getLeft() == null) {
			if (tempHead.getRight() == null) {
				System.out.println(tempHead + "(" + tempHead.getDepth() + ")");
			} else {
				System.out.println(tempHead + "(" + tempHead.getDepth()
						+ ") R-> ");
				this.internalPrint(tempHead.getRight());
			}
		} else if (tempHead.getRight() == null) {
			System.out.println(tempHead + "(" + tempHead.getDepth() + ") L-> ");
			this.internalPrint(tempHead.getLeft());
		} else {
			System.out.println(tempHead + "[branch](" + tempHead.getDepth()
					+ ") L-> ");
			this.internalPrint(tempHead.getLeft());
			System.out.println(tempHead + "[branch](" + tempHead.getDepth()
					+ ") R-> ");
			this.internalPrint(tempHead.getRight());
		}
	}

	protected void setHead(BinaryTreeNode<E> node) {
		this.head = node;
		this.head.setDepth(0);
		this.head.setParent(null);
	}
}
