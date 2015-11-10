package database.datastrucutres;

public class Node<E> {
	private Node<E> left, right, parent;
	private int depth, height;
	private E item;

	public Node(E item) {
		this.item = item;
		this.depth = 0;
		this.height = 0;
	}

	public boolean isLeaf() {
		return this.left == null && this.right == null;
	}

	public String toString() {
		return this.item.toString();
	}

	public void setLeft(Node<E> left) {
		if (left == null)
			this.left = null;
		else {
			this.left = left;
			this.left.setParent(this);
			this.left.setDepth(this.depth + 1);
		}
	}

	public void setRight(Node<E> right) {
		if (right == null)
			this.right = null;
		else {
			this.right = right;
			this.right.setParent(this);
			this.right.setDepth(this.depth + 1);
		}
	}

	public E getItem() {
		return this.item;
	}

	public void setItem(E item) {
		this.item = item;
	}

	public Node<E> getLeftChild() {
		return this.left;
	}

	public Node<E> getRightChild() {
		return this.right;
	}

	public Node<E> getParent() {
		return this.parent;
	}

	public void setParent(Node<E> parent) {
		this.parent = parent;
	}

	public int getDepth() {
		return this.depth;
	}

	public int getHeight() {
		return this.height;
	}
	
	public void fixHeight() {
		this.height = Math.max(this.left != null ? this.left.getHeight() + 1 : 0,
				this.right != null ? this.right.getHeight() + 1 : 0);
	}

	public int getHeightBias() {
		if (this.isLeaf())
			return 0;
		if (this.right == null)
			return (1 + this.left.getHeight()) * -1;
		if (this.left == null)
			return 1 + this.right.getHeight();
		return this.right.getHeight() - this.left.getHeight();
	}

	public void updateHeight() {
		this.height = Math.max(this.left != null ? this.left.getHeight() + 1 : 0,
				this.right != null ? this.right.getHeight() + 1 : 0);
		if (this.parent != null)
			this.parent.updateHeight();
	}
	
	public void setDepth(int depth) {
		this.depth = depth;
		if (this.left != null)
			this.left.setDepth(this.depth + 1);
		if (this.right != null)
			this.right.setDepth(this.depth + 1);
	}
}
