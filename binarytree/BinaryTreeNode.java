package binarytree;

public class BinaryTreeNode<E> {
	private BinaryTreeNode<E> left, right, parent;
	private int depth;
	private E item;

	public BinaryTreeNode(E item) {
		this.item = item;
		this.depth = 0;
	}

	public BinaryTreeNode(E item, BinaryTreeNode<E> parent) {
		this.item = item;
		this.setParent(parent);
	}

	public boolean isLeaf() {
		if (this.left == null && this.right == null)
			return true;
		return false;
	}

	public String toString() {
		return this.item.toString();
	}

	public void setLeft(BinaryTreeNode<E> left) {
		if (left == null)
			this.left = null;
		else {
			this.left = left;
			this.left.setParent(this);
			this.left.setDepth(this.depth + 1);
		}
	}

	public void setRight(BinaryTreeNode<E> right) {
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

	public BinaryTreeNode<E> getLeft() {
		return this.left;
	}

	public BinaryTreeNode<E> getRight() {
		return this.right;
	}

	public BinaryTreeNode<E> getParent() {
		return this.parent;
	}

	public void setParent(BinaryTreeNode<E> parent) {
		this.parent = parent;
	}

	public int getDepth() {
		return this.depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
		if (this.left != null)
			this.left.setDepth(this.depth + 1);
		if (this.right != null)
			this.right.setDepth(this.depth + 1);
	}
}
