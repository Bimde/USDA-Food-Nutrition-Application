package binarytree;

public class BinaryTreeTest {

	public static void main(String[] args) {
		BalancedBinaryTree<Integer> tree = new BalancedBinaryTree<Integer>();
		Integer one = new Integer(7);
		tree.add(new Integer(5));
		tree.add(new Integer(6));
		tree.add(new Integer(4));
		tree.add(new Integer(4));
		tree.add(one);
		tree.print();
		System.out.println("Max Depth: "+tree.maxDepth());
		System.out.println("Min Depth: "+tree.minDepth());
		System.out.println("\n----------------------------------------\n");
		tree.add(new Integer(8));
		tree.add(new Integer(2));
		tree.add(new Integer(3));
		tree.add(new Integer(9));
		tree.add(new Integer(1));
		tree.print();
		System.out.println("Max Depth: "+tree.maxDepth());
		System.out.println("Min Depth: "+tree.minDepth());
		System.out.println("\n----------------------------------------\n");
		tree.testRotateLeft();
		tree.add(new Integer(10));
		tree.print();
		System.out.println("Max Depth: "+tree.maxDepth());
		System.out.println("Min Depth: "+tree.minDepth());
		System.out.println("\n----------------------------------------\n");
	}

}
