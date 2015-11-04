package binarytree;

public class BinaryTree<E extends Comparable<E>> {
	protected Node<E> head;

	public BinaryTree() {
	}

	public void add(E item) {
		Node<E> itemNode = new Node<E>(item);
		if (head == null)
			this.head = itemNode;
		else if (this.internalAdd(itemNode, this.head))
			findProblems(this.head);
	}

	public E get(E item) {
		Node<E> temp = getNode(item);
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

	public int maxDepth() {
		return this.internalMaxDepth(this.head);
	}

	public int minDepth() {
		return this.internalMinDepth(this.head);
	}

	public boolean remove(E item) {
		Node<E> node = getNode(item);
		if (node == null)
			return false;
		Node<E> parent = node.getParent();
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
				Node<E> temp = parent.getLeft();
				while (temp.getLeft() != null) {
					temp = temp.getLeft();
				}
				temp.setLeft(node.getLeft());
			} else {
				parent.setRight(node.getLeft());
				Node<E> temp = parent.getRight();
				while (temp.getRight() != null) {
					temp = temp.getRight();

				}
				temp.setRight(node.getRight());
			}
		}
		return true;
	}

	protected int internalMaxDepth(Node<E> node) {
		if (node == null)
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

	protected int internalMinDepth(Node<E> node) {
		if (node.getLeft() == null || node.getRight() == null)
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
			Node<E> temp = this.head.getRight();
			while (temp.getLeft() != null) {
				temp = temp.getLeft();
			}
			temp.setLeft(this.head.getLeft());
			this.setHead(this.head.getRight());
		}
	}

	protected boolean internalAdd(Node<E> node, Node<E> tempHead) {
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

	protected Node<E> getNode(E item) {
		Node<E> temp = head;
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

	protected void internalPrint(Node<E> tempHead) {
		if (tempHead.getLeft() == null) {
			if (tempHead.getRight() == null) {
				System.out.println(tempHead + "(" + tempHead.getDepth() + ")[" + tempHead.getHeight() + "]");
			} else {
				System.out.println(tempHead + "(" + tempHead.getDepth() + ")[" + tempHead.getHeight() + "] R-> ");
				this.internalPrint(tempHead.getRight());
			}
		} else if (tempHead.getRight() == null) {
			System.out.println(tempHead + "(" + tempHead.getDepth() + ")[" + tempHead.getHeight() + "] L-> ");
			this.internalPrint(tempHead.getLeft());
		} else {
			System.out.println(tempHead + "[branch](" + tempHead.getDepth() + ")[" + tempHead.getHeight() + "] L-> ");
			this.internalPrint(tempHead.getLeft());
			System.out.println(tempHead + "[branch](" + tempHead.getDepth() + ")[" + tempHead.getHeight() + "] R-> ");
			this.internalPrint(tempHead.getRight());
		}
	}

	protected void setHead(Node<E> node) {
		this.head = node;
		this.head.setDepth(0);
		this.head.setParent(null);
	}

	protected void findProblems(Node<E> node) {
		if (node == null)
			return;
		findProblems(node.getLeft());
		findProblems(node.getRight());
		if (Math.abs(node.getHeightBias()) > 1)
			balance(node);
	}

	protected void balance(Node<E> root) {
		// Left case
		if (root.getHeightBias() < 0) {
			Node<E> temp = root.getLeft();
			int bias = temp.getHeightBias();
			// Left-right case
			if (bias > 0)
				rotateLeft(temp);
			// Left left case (not in else because this is required for
			// left-right case as well)
			rotateRight(root);
		}
		// Right case
		else {
			Node<E> temp = root.getRight();
			int bias = temp.getHeightBias();
			// Right-left case
			if (bias < 0)
				rotateRight(temp);
			// Right right case (not in else because this is required for
			// right-left case as well)
			rotateLeft(root);
		}
	}
	
	protected void updateAroundHeights(Node<E> node)
	{
		if(node.getRight() == null)
		{
			if(node.getLeft() == null)
				node.updateHeight();
			else
				node.getLeft().updateHeight();
		}
		else if(node.getLeft() == null)
		{
			node.getRight().updateHeight();
		}
		else {
			node.getLeft().updateHeight();
			node.getRight().updateHeight();
		}
			
	}

	protected boolean rotateRight(Node<E> root) {
		Node<E> pivot = root.getLeft(), parent = root.getParent();
		if (this.head == root)
			this.setHead(pivot);
		root.setLeft(pivot.getRight());
		pivot.setRight(root);
		if (parent != null) {
			if (parent.getLeft() == root)
				parent.setLeft(pivot);
			else
				parent.setRight(pivot);
		}
		return true;
	}

	protected boolean rotateLeft(Node<E> root) {
		Node<E> pivot = root.getRight(), parent = root.getParent();
		if (this.head == root)
			this.setHead(pivot);
		root.setRight(pivot.getLeft());
		pivot.setLeft(root);
		if (parent != null) {
			if (parent.getLeft() == root)
				parent.setLeft(pivot);
			else
				parent.setRight(pivot);
		}
		return true;
	}
}
