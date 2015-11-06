package database.datastrucutres;

public class BinaryTree<E extends Comparable<E>> {
	protected Node<E> head;

	public BinaryTree() {
	}

	public void add(E item) {
		Node<E> itemNode = new Node<E>(item);
		if (head == null)
			this.head = itemNode;
		else if (this.internalAdd(itemNode, this.head)) {
			this.updateHeights(this.head);
			this.findProblems(this.head);
		}
	}

	public E get(E item) {
		Node<E> temp = this.getNode(item);
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

	//TODO Optimize remove (make sure height updating works properly)
	/**
	 * OPTIMIZE
	 * @param item
	 * @return
	 */
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
			if (parent.getLeftChild() == node) {
				parent.setLeft(null);
			} else {
				parent.setRight(null);
			}
		} else if (node.getLeftChild() == null) {
			if (parent.getLeftChild() == node) {
				parent.setLeft(node.getRightChild());
			} else {
				parent.setRight(node.getRightChild());
			}
		} else if (node.getRightChild() == null) {
			if (parent.getLeftChild() == node) {
				parent.setLeft(node.getLeftChild());
			} else {
				parent.setRight(node.getLeftChild());
			}
		} else {
			if (parent.getLeftChild() == node) {
				parent.setLeft(node.getRightChild());
				Node<E> temp = parent.getLeftChild();
				while (temp.getLeftChild() != null) {
					temp = temp.getLeftChild();
				}
				temp.setLeft(node.getLeftChild());
			} else {
				parent.setRight(node.getLeftChild());
				Node<E> temp = parent.getRightChild();
				while (temp.getRightChild() != null) {
					temp = temp.getRightChild();
				}
				temp.setRight(node.getRightChild());
			}
		}
		this.updateHeights(this.head);
		return true;
	}

	protected int internalMaxDepth(Node<E> node) {
		if (node == null)
			return -1;
		if (node.isLeaf())
			return node.getDepth();
		else if (node.getLeftChild() == null)
			return internalMaxDepth(node.getRightChild());
		else if (node.getRightChild() == null)
			return internalMaxDepth(node.getLeftChild());
		else {
			int depth1 = internalMaxDepth(node.getLeftChild());
			int depth2 = internalMaxDepth(node.getRightChild());
			return depth1 >= depth2 ? depth1 : depth2;
		}
	}

	protected int internalMinDepth(Node<E> node) {
		if (node.getLeftChild() == null || node.getRightChild() == null)
			return node.getDepth();
		return Math.min(internalMinDepth(node.getLeftChild()),
				internalMinDepth(node.getRightChild()));
	}

	protected void removeHead() {
		if (this.head.isLeaf())
			this.head = null;
		else if (this.head.getLeftChild() == null) {
			this.head = this.head.getRightChild();
		} else if (this.head.getRightChild() == null) {
			this.head = this.head.getLeftChild();
		} else {
			Node<E> temp = this.head.getRightChild();
			while (temp.getLeftChild() != null) {
				temp = temp.getLeftChild();
			}
			temp.setLeft(this.head.getLeftChild());
			this.setHead(this.head.getRightChild());
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
				if (tempHead.getLeftChild() == null)
					tempHead.setLeft(node);
				else
					this.internalAdd(node, tempHead.getLeftChild());
			} else if (dif > 0) {
				if (tempHead.getRightChild() == null)
					tempHead.setRight(node);
				else
					this.internalAdd(node, tempHead.getRightChild());
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
			int dif = temp.getItem().compareTo(item);
			if (dif == 0)
				return temp;
			if (temp.isLeaf())
				return null;
			if (dif > 0)
				temp = temp.getLeftChild();
			else
				temp = temp.getRightChild();
		}
	}

	protected void internalPrint(Node<E> tempHead) {
		if (tempHead.getLeftChild() == null) {
			if (tempHead.getRightChild() == null) {
				System.out.println(tempHead + "(" + tempHead.getDepth() + ")["
						+ tempHead.getHeight() + "]");
			} else {
				System.out.println(tempHead + "(" + tempHead.getDepth() + ")["
						+ tempHead.getHeight() + "] R-> ");
				this.internalPrint(tempHead.getRightChild());
			}
		} else if (tempHead.getRightChild() == null) {
			System.out.println(tempHead + "(" + tempHead.getDepth() + ")["
					+ tempHead.getHeight() + "] L-> ");
			this.internalPrint(tempHead.getLeftChild());
		} else {
			System.out.println(tempHead + "[branch](" + tempHead.getDepth()
					+ ")[" + tempHead.getHeight() + "] L-> ");
			this.internalPrint(tempHead.getLeftChild());
			System.out.println(tempHead + "[branch](" + tempHead.getDepth()
					+ ")[" + tempHead.getHeight() + "] R-> ");
			this.internalPrint(tempHead.getRightChild());
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
		findProblems(node.getLeftChild());
		findProblems(node.getRightChild());
		if (Math.abs(node.getHeightBias()) > 1)
			balance(node);
	}

	protected void balance(Node<E> root) {
		// Left case
		if (root.getHeightBias() < 0) {
			Node<E> temp = root.getLeftChild();
			int bias = temp.getHeightBias();
			// Left-right case
			if (bias > 0)
				this.rotateLeft(temp);
			// Left left case (not in else because this is required for
			// left-right case as well)
			this.rotateRight(root);
		}
		// Right case
		else {
			Node<E> temp = root.getRightChild();
			int bias = temp.getHeightBias();
			// Right-left case
			if (bias < 0)
				this.rotateRight(temp);
			// Right right case (not in else because this is required for
			// right-left case as well)
			this.rotateLeft(root);
		}
	}

	protected void updateHeights(Node<E> node) {
		if (node.getLeftChild() == null) {
			if (node.getRightChild() == null)
				node.updateHeight();
			else
				this.updateHeights(node.getRightChild());
		} else if (node.getRightChild() == null)
			this.updateHeights(node.getLeftChild());
		else {
			this.updateHeights(node.getLeftChild());
			this.updateHeights(node.getRightChild());
		}
	}

	protected boolean rotateRight(Node<E> root) {
		Node<E> pivot = root.getLeftChild(), parent = root.getParent();
		if (this.head == root)
			this.setHead(pivot);
		root.setLeft(pivot.getRightChild());
		pivot.setRight(root);
		if (parent != null) {
			if (parent.getLeftChild() == root)
				parent.setLeft(pivot);
			else
				parent.setRight(pivot);
		}
		return true;
	}

	protected boolean rotateLeft(Node<E> root) {
		Node<E> pivot = root.getRightChild(), parent = root.getParent();
		if (this.head == root)
			this.setHead(pivot);
		root.setRight(pivot.getLeftChild());
		pivot.setLeft(root);
		if (parent != null) {
			if (parent.getLeftChild() == root)
				parent.setLeft(pivot);
			else
				parent.setRight(pivot);
		}
		return true;
	}
}
