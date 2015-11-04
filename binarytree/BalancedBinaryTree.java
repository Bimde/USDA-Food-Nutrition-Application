package binarytree;

public class BalancedBinaryTree<E extends Comparable<E>> extends BinaryTree<E> {

	public BalancedBinaryTree() {
		super();
	}

	@Override
	public void add(E item) {
		BinaryTreeNode<E> itemNode = new BinaryTreeNode<E>(item);
		if (head == null)
			this.setHead(itemNode);
		else {
			if (!this.internalAdd(itemNode, this.head))
				this.balance();
		}
	}

	public void testRotateLeft() {
		this.rotateLeft(this.head);
	}

	public void testRotateRight() {
		this.rotateRight(this.head);
	}

	protected void balance() {
		while (this.internalBalance(this.head))
			;
	}

	protected boolean internalBalance(BinaryTreeNode<E> tempHead) {
		int maxLeft = this.internalMaxDepth(tempHead.getLeft());
		int maxRight = this.internalMaxDepth(tempHead.getRight());
		if (maxLeft == -1)
			maxLeft = tempHead.getDepth();
		if (maxRight == -1)
			maxRight = tempHead.getDepth();
		if (Math.abs(maxRight - maxLeft) <= 1) {
			int temp = tempHead.getDepth();
			boolean one = false, two = false;
			if (maxLeft != temp)
				one = this.internalBalance(tempHead.getLeft());
			if (maxRight != temp)
				two = this.internalBalance(tempHead.getRight());
			return !(one || two);
		}
		if (maxRight > maxLeft) {
			while (this.internalBalance(tempHead.getRight()))
				;
			this.rotateLeft(tempHead);
		} else {
			while (this.internalBalance(tempHead.getLeft()))
				;
			this.rotateRight(tempHead);
		}
		return true;
	}

	@Override
	protected boolean internalAdd(BinaryTreeNode<E> node,
			BinaryTreeNode<E> tempHead) {
		return super.internalAdd(node, tempHead);
	}

	protected boolean rotateRight(BinaryTreeNode<E> root) {
		BinaryTreeNode<E> pivot = root.getLeft();
		if (this.head == root)
			this.setHead(pivot);
		root.setLeft(pivot.getRight());
		pivot.setRight(root);
		return true;
	}

	protected boolean rotateLeft(BinaryTreeNode<E> root) {
		BinaryTreeNode<E> pivot = root.getRight();
		if (this.head == root)
			this.setHead(pivot);
		root.setRight(pivot.getLeft());
		pivot.setLeft(root);
		return true;
	}
}
