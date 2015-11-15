package database.datastrucutres;

/**
 * Balanced binary tree implementation; modest loading times, but O(log(n))
 * searches
 * 
 * @author Bimesh De Silva
 * @version Final (November 2015)
 * @param <E>
 *            Object which extends Comparable class for Nodes to hold
 */
public class BinaryTree<E extends Comparable<E>> {
	protected Node<E> head;

	public BinaryTree() {
	}

	/**
	 * Adds the provided item to the BinaryTree
	 * 
	 * @param item
	 *            The item to add
	 */
	public void add(E item) {
		Node<E> itemNode = new Node<E>(item);
		if (head == null)
			this.head = itemNode;
		else
			this.internalAdd(itemNode, this.head);
	}

	/**
	 * RETURNS NULL IF LIST IS EMPTY Returns the item contained in the head of
	 * the tree
	 * 
	 * @return
	 */
	public E getHead() {
		if (this.head == null)
			return null;
		return this.head.getItem();
	}

	/**
	 * 
	 * @param item
	 * @return
	 */
	public E get(E item) {
		Node<E> temp = this.getNode(item);
		if (temp == null)
			return null;
		return temp.getItem();
	}

	/**
	 * Print out the tree's contents using format: [branch] key[depth](height)
	 * Direction --> for branches and key[depth](height) for leaves
	 */
	public void print() {
		this.internalPrint(this.head);
	}

	/**
	 * Checks whether the tree contains the specified item
	 * 
	 * @param item
	 *            The item to find
	 * @return whether the tree contains the specified item
	 */
	public boolean contains(E item) {
		if (this.getNode(item) == null)
			return false;
		return true;
	}

	/**
	 * Find the max depth of the tree (how many nodes down the tree goes)
	 * 
	 * @return the max depth of the tree
	 */
	protected int maxDepth() {
		return this.internalMaxDepth(this.head);
	}

	/**
	 * Find the min depth of the tree (how many nodes deep is the most shallow
	 * note without both a left and right child)
	 * 
	 * @return the min depth of the tree
	 */
	protected int minDepth() {
		return this.internalMinDepth(this.head);
	}

	/**
	 * Recursively find the maximum depth of the tree
	 * 
	 * @param node
	 *            The node to find the deepest node from
	 * @return the maximum depth of the tree
	 */
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

	/**
	 * Recursively find the minimum depth of the tree
	 * 
	 * @param node
	 *            The node to find the most shallow
	 * @return the maximum depth of the tree
	 */
	protected int internalMinDepth(Node<E> node) {
		if (node.getLeftChild() == null || node.getRightChild() == null)
			return node.getDepth();
		return Math.min(internalMinDepth(node.getLeftChild()), internalMinDepth(node.getRightChild()));
	}

	/**
	 * Recursively adds a node to the tree, then calls the balancing methods to
	 * fix balancing problems from the root node
	 * 
	 * @param node
	 *            The node to add to the tree
	 * @param tempHead
	 *            The node that is currently being searched
	 * @return Wether or not the node was added (if the node has an item that
	 *         already exists in the tree, it WILL NOT BE ADDED!!!
	 */
	protected boolean internalAdd(Node<E> node, Node<E> tempHead) {
		// Get the value difference between the items in the current node and
		// node trying to be added using the mandatory implemented Comparable
		// interface
		int dif = node.getItem().compareTo(tempHead.getItem());

		// Look left if the difference is lower, look right if it is greater,
		// and return false if the item already exists in the tree
		boolean added = false;
		if (dif < 0) {
			if (tempHead.getLeftChild() == null) {
				tempHead.setLeft(node);
				node.updateHeight();
				added = true;
			} else
				added = this.internalAdd(node, tempHead.getLeftChild());
		} else if (dif > 0) {
			if (tempHead.getRightChild() == null) {
				tempHead.setRight(node);
				node.updateHeight();
				added = true;
			} else
				added = this.internalAdd(node, tempHead.getRightChild());
		}

		// Find balancing problems and fix them
		this.findProblems(tempHead);
		return added;
	}

	/**
	 * Finds the node containing the specified item
	 * 
	 * @param item
	 *            The item which the desired node contains
	 * @return If it exists, the node containing the specified item, NULL
	 *         otherwise
	 */
	protected Node<E> getNode(E item) {
		Node<E> temp = this.head;

		// Iteratively find the node using a binary search for better
		// performance / lower memory usage
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

	/**
	 * FOR DEBUGGING PURPOSES: RECURSIVELY prints out the entire tree using the
	 * format: For branches: 'Primary Key(depth)[height] Direction->' For non
	 * branches: 'Primary Key(depth)[height]'
	 * 
	 * @param tempHead
	 *            The node to print from
	 */
	protected void internalPrint(Node<E> tempHead) {
		// Prints using the appropriate format based on children
		if (tempHead.getLeftChild() == null) {
			if (tempHead.getRightChild() == null) {
				System.out.println(tempHead + "(" + tempHead.getDepth() + ")[" + tempHead.getHeight() + "]");
			} else {
				System.out.println(tempHead + "(" + tempHead.getDepth() + ")[" + tempHead.getHeight() + "] R-> ");
				this.internalPrint(tempHead.getRightChild());
			}
		} else if (tempHead.getRightChild() == null) {
			System.out.println(tempHead + "(" + tempHead.getDepth() + ")[" + tempHead.getHeight() + "] L-> ");
			this.internalPrint(tempHead.getLeftChild());
		} else {
			System.out.println(tempHead + "[branch](" + tempHead.getDepth() + ")[" + tempHead.getHeight() + "] L-> ");
			this.internalPrint(tempHead.getLeftChild());
			System.out.println(tempHead + "[branch](" + tempHead.getDepth() + ")[" + tempHead.getHeight() + "] R-> ");
			this.internalPrint(tempHead.getRightChild());
		}
	}

	/**
	 * Sets the head of the tree and updates the height of the node
	 * 
	 * @param node
	 */
	protected void setHead(Node<E> node) {
		this.head = node;
		this.head.setDepth(0);
		this.head.setParent(null);
	}

	/**
	 * Find and fix balancing issues using by calling the balance method on
	 * unbalanced nodes
	 * 
	 * @param node
	 *            The node to find and fix balancing issues
	 */
	protected void findProblems(Node<E> node) {
		if (node == null)
			return;
		// Balances the node if the height of one branch is more than one node
		// greater than the other branch (i.e if the left child is 3 nodes high,
		// but the right child is only one node high, then there is a balancing
		// issue that must be fixed)
		if (Math.abs(node.getHeightBias()) > 1)
			balance(node);
	}

	/**
	 * Fixes balancing issues of a node using the AVL self-balancing binary tree
	 * balancing principles using the four cases,(left-left, left-right,
	 * right-right, and right-left) where each case has a specific balancing
	 * technique to fix the issue
	 * 
	 * @param root
	 *            The node to balance
	 */
	protected void balance(Node<E> root) {
		int rootHeightBias = root.getHeightBias();

		// Either left-left case or left-right case (as the first balancing
		// issue spawns from the left child)
		if (rootHeightBias < 0) {
			Node<E> temp = root.getLeftChild();
			int bias = temp.getHeightBias();

			// Left-right case because the second balancing issue spawns from
			// the left child, fix by rotating the node left, moving the
			// balancing
			// issue to the right child, creating a left-left case, which is
			// easy to fix
			if (bias > 0)
				this.rotateLeft(temp);

			// Left-left case as the balancing problems stretch down the right
			// side, fix by rotating the sub-tree left from the root node,
			// creating a perfectly balanced sub-tree (even height on both
			// sides)
			this.rotateRight(root);
			root.fixHeight();

			// Update the heights of the nodes for further balancing later
			temp.updateHeight();
		}

		// Either right-right case or right-left case (as the first balancing
		// issue spawns from the right child)
		else if (rootHeightBias > 0) {
			Node<E> temp = root.getRightChild();
			int bias = temp.getHeightBias();

			// Right-left case because the second balancing issue spawns from
			// the left child, fix by rotating the node left, moving the
			// balancing
			// issue to the right child, creating a right-right case, which is
			// easy to fix
			if (bias < 0)
				this.rotateRight(temp);

			// Right-right case as the balancing problems stretch down the right
			// side, fix by rotating the sub-tree left from the root node,
			// creating a perfectly balanced sub-tree (even height on both
			// sides)
			this.rotateLeft(root);
			root.fixHeight();

			// Update the heights of the nodes for further balancing later
			temp.updateHeight();
		}
	}

	/**
	 * Recursively updates the height of specified node and all children nodes
	 * 
	 * @param node
	 *            The node to update the heights from
	 */
	protected void updateHeights(Node<E> node) {
		// Call the updateHeight() method of leaves (nodes with no children)
		// only as the method updates
		// the height of all of the nodes above it recursively
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

	/**
	 * Performs a right rotation on the specified node
	 * 
	 * @param root
	 *            the node to perform the rotation on
	 */
	protected void rotateRight(Node<E> root) {
		Node<E> pivot = root.getLeftChild(), parent = root.getParent();

		// Deal with special case of root being the head of the tree
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
	}

	/**
	 * Performs a left rotation on the specified node
	 * 
	 * @param root
	 *            the node to perform the rotation on
	 */
	protected void rotateLeft(Node<E> root) {
		Node<E> pivot = root.getRightChild(), parent = root.getParent();

		// Deal with special case of root being the head of the tree
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
	}
}
